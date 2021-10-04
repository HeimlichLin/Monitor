package idv.heimlich.Monitor.domain.controller.job.clean;

/**
 * 清檔Jobs
 */
public enum CleanJobs {

	C1(new CleanUserActionServiceImpl()), // 清除使用者紀錄
	C2(new CleanCheckCodeLogServiceImpl()), // 清除Backend執行log 
	;//

	final CleanService cleanService;

	private CleanJobs(CleanService cleanService) {
		this.cleanService = cleanService;
	}

	public CleanService getCleanService() {
		return cleanService;
	}
	
}
