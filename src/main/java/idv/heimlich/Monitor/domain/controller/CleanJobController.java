package idv.heimlich.Monitor.domain.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idv.heimlich.Monitor.domain.controller.job.clean.CleanJob;

public class CleanJobController {
	
	private final static Logger LOG = LoggerFactory.getLogger(BackendMonitorController.class);

	public static void main(String[] args) {
		try {
			LOG.info("CleanJob begin");
			new CleanJob().doClean();
		} finally {
			LOG.info("CleanJob end");
		}
	}
	
}
