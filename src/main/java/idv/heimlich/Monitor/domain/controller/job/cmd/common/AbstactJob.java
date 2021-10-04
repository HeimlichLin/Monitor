package idv.heimlich.Monitor.domain.controller.job.cmd.common;

public abstract class AbstactJob {
	
	protected String message = "";// 訊息
	protected String files = "";// 處理附件
	protected String transactionId = "";// 該次TRANSACTION_ID

	public final void execute() throws Exception {
		this.myExecute();
	}
	
	abstract public void myExecute() throws Exception;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

}
