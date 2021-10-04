package idv.heimlich.Monitor.domain.controller.job.monitor;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import idv.heimlich.Monitor.common.log.LogContract;
import idv.heimlich.Monitor.common.mail.MailCreateDTO;
import idv.heimlich.Monitor.common.mail.MailService;
import idv.heimlich.Monitor.common.mail.MailServiceImpl;
import idv.heimlich.Monitor.domain.bean.SyscodeDo;
import idv.heimlich.Monitor.domain.code.GetNumberType;
import idv.heimlich.Monitor.domain.service.GetNumberService;
import idv.heimlich.Monitor.domain.service.impl.GetNumberServiceImpl;

/**
 * 檢查最舊當按超過預期未處理
 */
public class BackendMonitorJob implements Runnable, Callable<Void> {

	protected static final SimpleDateFormat SDF = new SimpleDateFormat(
			"yyyy/MM/dd hh:mm:ss");
	// private static int MAX_EXECUTE_TIME = Integer.parseInt(// 檢查次數
	// ApContext.getContext().getSetting(BackendMonitorJob.class.getName() +
	// ".max.excute.time"));
	// private static int CHEKC_WAIT_MIN = Integer.parseInt(// 檢查等待時間
	// ApContext.getContext().getSetting(BackendMonitorJob.class.getName() +
	// ".check.wait.min"));// 再一次檢查幾分鐘後
	private static int MAX_EXECUTE_TIME = 1;

	private static int CHEKC_WAIT_MIN = 1;// 再一次檢查幾分鐘後

	private final static org.slf4j.Logger LOG = org.slf4j.LoggerFactory
			.getLogger(BackendMonitorJob.class);

	private static final String FILE_TEMPLATE = "[%s]-[%s]\n";
	private static final String REST_FUL_TEMPLATE = "/rest/job/kill/%s";

	final String path;// 檔案路徑
	final String overTime;// 最舊處理時間超過
	final String regex;// 檔名規則
	final String overFiles;// 超過檔案限制數量
	final String doc;// 文件別
	private final int executeTime = 1;// 執行次數

	private final MailService mailService = MailServiceImpl.get();
	private transient GetNumberService getNumberService = GetNumberServiceImpl
			.get();

	public BackendMonitorJob(BackendMonitorDTO backendMonitorDTO) {//
		super();
		this.doc = backendMonitorDTO.doc;
		this.path = backendMonitorDTO.path;
		this.overTime = backendMonitorDTO.overTime;
		this.regex = backendMonitorDTO.regex;
		this.overFiles = backendMonitorDTO.overFiles;
	}

	@Override
	public void run() {
		this.check(this.executeTime);
	}

	public void check(int time) {
		LOG.info("{}：(" + //
				"path:{}," + // 檔案路徑
				"overtime:{}," + // 逾期處理時間
				"regex:{}," + // 檔名規則
				"overfiles:{}," + // 超過檔案限制
				"execute_time:{}" + // 檢查次數
				")", //
				this.doc, this.path, // /
				this.overTime, //
				this.regex, //
				this.overFiles, //
				time);//

		final File folder = new File(this.path);
		final File[] fs = folder.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return pathname.getName().matches(BackendMonitorJob.this.regex);
			}
		});
		// /根據最後異動時間
		Arrays.sort(fs, new Comparator<File>() {
			@Override
			public int compare(File f1, File f2) {
				final long diff = f1.lastModified() - f2.lastModified();
				if (diff > 0) {
					return 1;
				} else if (diff == 0) {
					return 0;
				} else {
					return -1;//
				}
			}

			@Override
			public boolean equals(Object obj) {
				return true;
			}

		});
		// 有一筆檔案
		if (fs.length > 0) {
			final File firstFile = fs[0];
			final boolean isOverTime = this.getIsOverTime(firstFile);
			final boolean isOverFiles = this.getIsOverTime(fs, this.overFiles);

			if (isOverTime || isOverFiles) {
				if (time >= MAX_EXECUTE_TIME) {
					String message = "";
					message += "通知原因：\n";
					if (isOverTime) {
						message += String.format("最舊檔案：%s已超過預計完成時間%s分以上\n",
								firstFile.getName(), this.overTime);
					}
					if (isOverFiles) {
						message += String.format("待處理檔案 總共為：%d筆已經超過限制數量：%s筆\n",
								fs.length, this.overFiles);
					}

					// final String callkillUrl =
					// ApContext.getContext().getSetting("web-url")
					// + String.format(REST_FUL_TEMPLATE, this.doc);
					final String callkillUrl = "PATH";
					message += "訊息資料：\n";
					message += String.format("資料路徑：%s\n", this.path);
					message += String.format("檔名規則：%s\n", this.regex);
					message += String.format("待處理筆數：%d\n", fs.length);
					message += String.format("呼叫WebService：%s\n", callkillUrl);
					message += "檔案資料(完整清單於附件內)：\n";
					for (int i = 0; i < fs.length && i <= 3; i++) {
						final File f = fs[i];
						final String date = SDF
								.format(new Date(f.lastModified()));
						message += String.format(FILE_TEMPLATE, //
								date, f.getName());
					}

					if (fs.length > 3) {
						message += String.format("超出顯示筆數以下省略.....\n");
					}

					final File file = this.writeFileByFiles(fs);

					final MailCreateDTO mailCreateDTO = new MailCreateDTO();
					mailCreateDTO.setFilePath("");
					mailCreateDTO.setMailBcc("");
					mailCreateDTO.setMailCc("");
					mailCreateDTO.setMailFrom(from());
					mailCreateDTO.setMailTo(to());
					mailCreateDTO.setMessage(message);
					mailCreateDTO
							.setPgmId(BackendMonitor.class.getSimpleName());
					mailCreateDTO.setRemarks("");
					mailCreateDTO.setSubject(subject());
					mailCreateDTO.setFilePath(file.getPath());
					this.mailService.create(mailCreateDTO);
				} else {
					LOG.info("[{}-{}/{}]:wait  {} min  to check again", doc,
							time, MAX_EXECUTE_TIME, CHEKC_WAIT_MIN);
					try {
						TimeUnit.MINUTES.sleep(CHEKC_WAIT_MIN);
					} catch (final InterruptedException e) {
						LOG.error("InterruptedException ", e);
						e.printStackTrace();
					}

					this.check(time + 1);
				}
			} else {
				LOG.info("[{}-{}/{}]:pass  ", doc, time, MAX_EXECUTE_TIME);
			}

		}
	}

	private File writeFileByFiles(File[] fs) {
		final String id = this.getNumberService
				.getCount(GetNumberType.YYYYMMDD12N);
		final String log = String.format(LogContract.getLogPath() + "/%s.log", id);
		final File file = new File(log);
		final List<String> messageList = new ArrayList<String>();
		for (File f : fs) {
			final String date = SDF.format(new Date(f.lastModified()));
			messageList.add(String.format(FILE_TEMPLATE, date, f.getName()));
		}
		try {
			file.delete();
			file.createNewFile();
			FileUtils.writeLines(file, "UTF-8", messageList);
		} catch (IOException e) {
			LOG.error("file IOException {}", file, e);
		}

		return file;
	}

	private boolean getIsOverTime(File[] fs, String overFiles) {
		return fs.length > Integer.parseInt(overFiles);
	}

	/**
	 * 是否逾時未處理
	 * 
	 * @param firstFile
	 * @return
	 */
	private boolean getIsOverTime(File firstFile) {
		final Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, -Integer.parseInt(this.overTime));
		// 超過逾期處理時間
		if (firstFile.lastModified() < calendar.getTimeInMillis()) {// 最舊一筆資料超過10分鐘未執行
			LOG.info("File name:{} is expired to execute....",
					firstFile.getName());
			return true;
		} else {
			LOG.info("File name:{} wait to doing", firstFile.getName());
		}
		return false;
	}

	public String to() {
		// final DoSqlWhere<SyscodeDo.COLUMNS> sqlWhere = new
		// DoSqlWhere<SyscodeDo.COLUMNS>();
		// sqlWhere.add(SyscodeDo.COLUMNS.TYPE_ID, "MAIL");
		// sqlWhere.add(SyscodeDo.COLUMNS.CODE_ID, "A010");
		//
		// final DoXdaoSession doXdaoSession =
		// XdaoSessionManager.getDoXdaoSession();
		// final List<SyscodeDo> syscodeDos =
		// doXdaoSession.selectPo(SyscodeDo.class, sqlWhere);
		// return this.syscodes2String(syscodeDos);
		return null;
	}

	private String syscodes2String(final List<SyscodeDo> syscodeDos) {
		// final List<String> list = new ArrayList<String>();
		// for (final SyscodeDo syscodeDo : syscodeDos) {
		// list.add(syscodeDo.getCodeData2());
		// }
		// final String mailsString = StringUtils.join(list, ",");
		// LOG.debug("收信人員:" + mailsString);
		// return mailsString;
		return null;
	}

	public String from() {
		// final String from = ApContext.getContext().getSetting("owner_mail");
		// LOG.info("form:{}", from);
		// return from;
		return null;
	}

	public String subject() {
		return this.doc + "逾期未處理";
	}

	public String msg(String message) {
		return "because " + message;
	}

	@Override
	public Void call() throws Exception {
		this.run();
		return null;
	}

}
