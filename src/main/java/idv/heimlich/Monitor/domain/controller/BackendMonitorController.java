package idv.heimlich.Monitor.domain.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idv.heimlich.Monitor.domain.controller.job.monitor.BackendMonitor;

public class BackendMonitorController {
	
	private final static Logger LOG = LoggerFactory.getLogger(BackendMonitorController.class);

	public static void main(String[] args) {
		try {
			LOG.info("BackendMonitor begin");
			BackendMonitor.main(args);
		} finally {
			LOG.info("BackendMonitor end");
		}
	}

}
