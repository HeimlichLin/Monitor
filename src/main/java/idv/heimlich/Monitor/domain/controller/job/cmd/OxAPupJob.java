package idv.heimlich.Monitor.domain.controller.job.cmd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idv.heimlich.Monitor.common.log.LogContract;
import idv.heimlich.Monitor.domain.controller.job.cmd.common.AbstactJob;
import idv.heimlich.Monitor.domain.controller.job.cmd.common.JobContract;

/**
 * 啟動Backend
 */
public class OxAPupJob extends AbstactJob implements Runnable {

	private final static Logger LOGGER = LoggerFactory.getLogger(OxAPupJob.class);

	private static final String CALL_SCT = OxAPupJob.class.getSimpleName() + JobContract.SCT_SUFFIX;

	@Override
	public void myExecute() throws Exception {
		Thread commandThread = new Thread(this);
		LOGGER.info("Create Command Thread -> " + commandThread.getName());
		commandThread.start();
		commandThread.join();
	}
	
	@Override
	public void run() {
		final String log = String.format(LogContract.getLogPath() + "/%s.log", transactionId);
		LOGGER.info("call CALL_SCT:{},LOG PATH:{}", CALL_SCT, log);
		ShellUtils.cmd(log, CALL_SCT);
		this.files = log;
		this.message = JobContract.DETIAL_IN_LOG_FILE;

	}	
	
}
