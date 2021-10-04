package idv.heimlich.Monitor.domain.controller.job.cmd;

import idv.heimlich.Monitor.common.log.LogContract;
import idv.heimlich.Monitor.domain.controller.job.cmd.common.AbstactJob;
import idv.heimlich.Monitor.domain.controller.job.cmd.common.JobContract;

/**
 * 關閉Backend
 */
public class OxAPdownJob extends AbstactJob implements Runnable {
	
	private final static org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(OxAPdownJob.class);
	private static final String CALL_SCT = OxAPdownJob.class.getSimpleName() + JobContract.SCT_SUFFIX;

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
		LOGGER.info("call CALL_SCT:{},LOG PATH:{}",CALL_SCT,log);
		ShellUtils.cmd(log, CALL_SCT);
		this.files = log;
		this.message  = JobContract.DETIAL_IN_LOG_FILE;

	}

}
