package idv.heimlich.Monitor.domain.controller.job.cmd.common;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idv.heimlich.Monitor.common.exception.ApBusinessException;
import idv.heimlich.Monitor.common.mail.MailService;
import idv.heimlich.Monitor.common.mail.MailServiceImpl;
import idv.heimlich.Monitor.domain.bean.JobqueDo;
import idv.heimlich.Monitor.domain.bean.SyscodeDo;
import idv.heimlich.Monitor.domain.code.JobqueStatus;
import idv.heimlich.Monitor.domain.controller.job.cmd.JobComs;
import idv.heimlich.Monitor.domain.dao.JobqueDAO;
import idv.heimlich.Monitor.domain.dao.impl.JobqueDAOImpl;

/**
 * JOB CMD 服務
 */
public class JobCmdServiceImpl implements JobCmdService {
	
	private final static Logger LOG = LoggerFactory.getLogger(JobCmdServiceImpl.class);
	
	private final static JobCmdService INSTANCT = new JobCmdServiceImpl();

	private final JobqueDAO jobqueDAO = JobqueDAOImpl.INSTANCE;
	private final Map<String, Class<? extends AbstactJob>> jobMap = new HashMap<String, Class<? extends AbstactJob>>();
	private MailService mailService = MailServiceImpl.get();

	public JobCmdServiceImpl() {

		final EnumSet<JobComs> JobComs = EnumSet.allOf(JobComs.class);
		for (final JobComs cmd : JobComs) {
			this.jobMap.put(cmd.name(), cmd.getJobClass());
		}
	}
	
	public static JobCmdService get() {
		return INSTANCT;
	}
	
	@Override
	public void cmd() {
		// 取得待處理JOB
		final List<JobqueDo> jobquePos = this.jobqueDAO.quertyByStatus(JobqueStatus.W);
		LOG.info("wait to do job num:" + jobquePos.size());
		for (final JobqueDo jobquePo : jobquePos) {
			this.execute(jobquePo);
		}

	}
	
	private void execute(JobqueDo jobquePo) {
//		AbstactJob job = null;
//
//		try {
//			job = this.newJob(jobquePo.getJobId());
//			job.transactionId = jobquePo.getTranactionId();
//			jobquePo.setExecuteTime(YYYYMMDDHHMMSSUtils.getText());
//
//			job.execute();
//
//			jobquePo.setStauts(JobqueStatus.S.name());
//			jobquePo.setMessage(job.message);
//		} catch (final Exception e) {
//			LOG.error("execute error:{}", jobquePo, e);
//			jobquePo.setStauts(JobqueStatus.E.name());
//		} finally {
//			final DataObject dataObject = new DataObject();
//			dataObject.setValue(JobqueDo.COLUMNS.STAUTS.name(), jobquePo.getStauts());
//			dataObject.setValue(JobqueDo.COLUMNS.EXECUTE_TIME.name(), jobquePo.getExecuteTime());
//			this.jobqueDAO.update(jobquePo.getTranactionId(), dataObject);
//
//			final MailCreateDTO mailCreateDTO = new MailCreateDTO();
//			mailCreateDTO.setFilePath(job.getFiles());
//			mailCreateDTO.setMailBcc(null);
//			mailCreateDTO.setMailCc(null);
//			mailCreateDTO.setMailTo(to());
//			mailCreateDTO.setSubject("訊息通知 (" + jobquePo.getJobId() + ") ");
//			mailCreateDTO.setMessage(this.getMailMessage(jobquePo));
//			mailCreateDTO.setPgmId(JobCmd.class.getSimpleName());
//			mailCreateDTO.setFilePath(job.getFiles());
//			mailService.create(mailCreateDTO);
//		}

	}

	private String getMailMessage(final JobqueDo jobquePo) {
		StringBuffer mailContext = new StringBuffer();
		mailContext.append("交易序號：{TRANACTION_ID}\n");
		mailContext.append("程式：{JOB_ID}\n");
		mailContext.append("建立日期：{CREATE_TIME}\n");
		mailContext.append("執行時間：{EXECUTE_TIME}\n");
		mailContext.append("狀態：{STAUTS}\n");
		mailContext.append("訊息：{MESSAGE}\n");
		return mailContext.toString().//
				replaceFirst("\\{TRANACTION_ID\\}", jobquePo.getTranactionId()).//
				replaceFirst("\\{JOB_ID\\}", jobquePo.getJobId()).//
				replaceFirst("\\{CREATE_TIME\\}", jobquePo.getCreateTime()).//
				replaceFirst("\\{EXECUTE_TIME\\}", jobquePo.getExecuteTime()).//
				replaceFirst("\\{STAUTS\\}", jobquePo.getStauts()).//
				replaceFirst("\\{MESSAGE\\}", jobquePo.getMessage())//
		;
	}


	public String to() {
//		final DoSqlWhere<SyscodeDo.COLUMNS> sqlWhere = new DoSqlWhere<SyscodeDo.COLUMNS>();
//		sqlWhere.add(SyscodeDo.COLUMNS.TYPE_ID, "MAIL");
//		sqlWhere.add(SyscodeDo.COLUMNS.CODE_ID, "A010");
//
//		final DoXdaoSession doXdaoSession = XdaoSessionManager.getDoXdaoSession();
//		final List<SyscodeDo> syscodeDos = doXdaoSession.selectPo(SyscodeDo.class, sqlWhere);
//		return this.syscodes2String(syscodeDos);
		return null;
	}

	private String syscodes2String(final List<SyscodeDo> syscodeDos) {
//		final List<String> list = new ArrayList<String>();
//		for (final SyscodeDo syscodeDo : syscodeDos) {
//			list.add(syscodeDo.getCodeData2());
//			LOG.info("syscodeDos :{}", ToStringBuilder.reflectionToString(syscodeDo, ToStringStyle.MULTI_LINE_STYLE));
//		}
//		final String mailsString = StringUtils.join(list, ",");
//		return mailsString;
		return null;
	}

	public AbstactJob newJob(String jobid) {
		if (this.jobMap.containsKey(jobid)) {
			try {
				final AbstactJob job = this.jobMap.get(jobid).newInstance();
				return job;
			} catch (final Exception e) {
				LOG.error("newJob error:{}", jobid, e);
			}

		}
		throw new ApBusinessException("new object error jobid:" + jobid);
	}
	
}
