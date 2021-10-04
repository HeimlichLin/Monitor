package idv.heimlich.Monitor.domain.controller.job.monitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Backend監控程式
 */
public class BackendMonitor {
	
	private final ExecutorService service = Executors.newFixedThreadPool(3);
	private final static Logger LOG = LoggerFactory.getLogger(BackendMonitor.class);
	
	private String config = "";

	public BackendMonitor(String config) {
		super();
		this.config = config;
	}
	
	public static void main(String[] args) {
		LOG.debug("BackendMonitor execute.. ");
		final String pathString = BackendMonitor.class.getPackage().getName().replaceAll("\\.", "\\/");
		String path = BackendMonitor.class.getClassLoader().getResource(pathString).getFile();
		LOG.info("load config path:{}", path);
		if (args.length >= 1) {
			path = args[0];
		}
		new BackendMonitor(path).doCheck();
		LOG.debug("BackendMonitor done.. ");
	}
	
	public void doCheck() {
		final File folder = new File(this.config);
		final File[] fs = folder.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".properties");
			}
		});
		// 讀取設定檔案
		final List<BackendMonitorJob> jobs = new ArrayList<BackendMonitorJob>();
		LOG.debug("load properties beging.. ");
		for (final File f : fs) {
			LOG.info("load properties:{}", f);
			final Properties prop = new Properties();
			try {
				prop.load(new FileInputStream(f));
				final String path = prop.getProperty("path");
				final String overTime = prop.getProperty("overTime");
				final String regex = prop.getProperty("regex");
				final String overFiles = prop.getProperty("overFiles");
				final String doc = prop.getProperty("doc");

				final BackendMonitorDTO backendMonitorDTO = new BackendMonitorDTO();
				backendMonitorDTO.path = path;
				backendMonitorDTO.overTime = overTime;
				backendMonitorDTO.regex = regex;
				backendMonitorDTO.overFiles = overFiles;
				backendMonitorDTO.doc = doc;
				jobs.add(new BackendMonitorJob(backendMonitorDTO));
			} catch (final Exception e) {
				LOG.error("load properties error file:{}", f, e);
			}
		}
		try {
			service.invokeAll(jobs);
		} catch (InterruptedException e) {
			LOG.error("InterruptedException ", e);
		}
		service.shutdown();
	}

}
