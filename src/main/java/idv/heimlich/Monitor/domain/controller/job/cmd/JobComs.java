package idv.heimlich.Monitor.domain.controller.job.cmd;

import idv.heimlich.Monitor.domain.controller.job.cmd.common.AbstactJob;

public enum JobComs {
	
	oxAPstatus(OxAPstatusJob.class), // 檢視狀態
	oxAPdown(OxAPdownJob.class), // 關閉BACKEND
	oxAPup(OxAPupJob.class), // 啟動BACKEND

	// --KILL
	killL4(KillL4Job.class), // 刪除 L4 process
	killL6(KillL6Job.class), // 刪除 L6 process
	;
	
	final Class<? extends AbstactJob> jobClass;
	
	private JobComs(Class<? extends AbstactJob> jobClass) {
		this.jobClass = jobClass;
	}
	
	public Class<? extends AbstactJob> getJobClass() {
		return jobClass;
	}

}
