package idv.heimlich.Monitor.domain.bean;

//Job隊伍
public class JobqueDo {
	
//	public static MapConverter<JobquePo> CONVERTER = new MapConverter<JobquePo>() {
//
//		@Override
//		public JobquePo convert(DataObject dataObject) {
//			final JobquePo vo = new JobquePo();
//			vo.setTranactionId(dataObject.getString("TRANACTION_ID"));
//			vo.setJobId(dataObject.getString("JOB_ID"));
//			vo.setCreateTime(dataObject.getString("CREATE_TIME"));
//			vo.setExecuteTime(dataObject.getString("EXECUTE_TIME"));
//			vo.setMessage(dataObject.getString("MESSAGE"));
//			vo.setStauts(dataObject.getString("STAUTS"));
//			return vo;
//		}
//
//		@Override
//		public DataObject toDataObject(JobquePo po) {
//			DataObject dataObject = new DataObject();
//			dataObject.setValue(JobquePo.COLUMNS.TRANACTION_ID.name(), po.getTranactionId());
//			dataObject.setValue(JobquePo.COLUMNS.JOB_ID.name(), po.getJobId());
//			dataObject.setValue(JobquePo.COLUMNS.CREATE_TIME.name(), po.getCreateTime());
//			dataObject.setValue(JobquePo.COLUMNS.EXECUTE_TIME.name(), po.getExecuteTime());
//			dataObject.setValue(JobquePo.COLUMNS.MESSAGE.name(), po.getMessage());
//			dataObject.setValue(JobquePo.COLUMNS.STAUTS.name(), po.getStauts());
//			return dataObject;
//		}
//	};

	public enum COLUMNS {
		TRANACTION_ID("交易序號", true), //
		JOB_ID("產生程式", false), //
		CREATE_TIME("建立日期", false), //
		EXECUTE_TIME("寄件時間", false), //
		MESSAGE("訊息", false), //
		STAUTS("狀態(W:等待執行 E:異常 S:成功)", false), //
		;//
		final String chineseName;
		final boolean isPK;

		private COLUMNS(String chineseName, boolean isPK) {
			this.chineseName = chineseName;
			this.isPK = isPK;
		}

		public String getChineseName() {
			return chineseName;
		}

		public boolean isPK() {
			return isPK;
		}
	}

	private String tranactionId;// 交易序號
	private String jobId;// 產生程式
	private String createTime;// 建立日期
	private String executeTime;// 寄件時間
	private String message;// 訊息
	private String stauts;// 狀態(w:等待執行 e:異常 s:成功)

	public String getTranactionId() {
		return this.tranactionId;
	}

	public void setTranactionId(String value) {
		this.tranactionId = value;
	}

	public String getJobId() {
		return this.jobId;
	}

	public void setJobId(String value) {
		this.jobId = value;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String value) {
		this.createTime = value;
	}

	public String getExecuteTime() {
		return this.executeTime;
	}

	public void setExecuteTime(String value) {
		this.executeTime = value;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String value) {
		this.message = value;
	}

	public String getStauts() {
		return this.stauts;
	}

	public void setStauts(String value) {
		this.stauts = value;
	}

}
