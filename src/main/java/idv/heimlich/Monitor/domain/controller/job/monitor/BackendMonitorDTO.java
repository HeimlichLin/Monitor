package idv.heimlich.Monitor.domain.controller.job.monitor;

public class BackendMonitorDTO {
	
	protected String path;// 檔案路徑
	protected String overTime;// 最舊處理時間超過
	protected String regex;// 檔名規則
	protected String overFiles;// 超過檔案限制數量
	protected String doc;// 文件別

	public String getPath() {
		return this.path;
	}

	public String getOverTime() {
		return this.overTime;
	}

	public String getRegex() {
		return this.regex;
	}

	public String getOverFiles() {
		return this.overFiles;
	}

	public String getDoc() {
		return this.doc;
	}
	
}
