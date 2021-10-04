package idv.heimlich.Monitor.domain.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idv.heimlich.Monitor.domain.controller.job.cmd.common.JobCmd;

public class JobCmdController {
	
	private final static Logger LOG = LoggerFactory.getLogger(BackendMonitorController.class);

	public static void main(String[] args) {
		try {
			LOG.info("JobCmd begin");
			new JobCmd().doCmd();
		} finally {
			LOG.info("JobCmd end");
		}
	}

}
