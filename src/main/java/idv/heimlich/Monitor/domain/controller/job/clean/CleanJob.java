package idv.heimlich.Monitor.domain.controller.job.clean;

/**
 * 每天晚上執行清檔案job
 */
public class CleanJob {
	
	private CleanDTO dto = new CleanDTO();
	private final CleanService service = CleanServiceImpl.getInstanct();
	
	public CleanDTO getDto() {
		return this.dto;
	}

	public void setDto(CleanDTO dto) {
		this.dto = dto;
	}
	
	/**
	 * 執行清檔
	 */
	public void doClean() {
		this.service.clean(this.dto);
	}

}
