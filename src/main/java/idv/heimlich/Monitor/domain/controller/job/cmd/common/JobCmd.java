package idv.heimlich.Monitor.domain.controller.job.cmd.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobCmd {
	
	private final static Logger LOG = LoggerFactory.getLogger(JobCmd.class);

	private final transient JobCmdService jmdService = JobCmdServiceImpl.get();

	public void doCmd() {
		LOG.debug("JobCmd rum start!");
		try {
			this.jmdService.cmd();
		} finally {
			LOG.debug("JobCmd rum end!");
		}

	}
	
}
