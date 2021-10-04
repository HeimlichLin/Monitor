package idv.heimlich.Monitor.domain.controller.job.clean;

import java.util.ArrayList;
import java.util.List;

/**
 * 請檔服務實作
 */
public class CleanServiceImpl implements CleanService {
	
	private static CleanService INSTANCT = new CleanServiceImpl();

	private final List<CleanService> cleanLogServices = new ArrayList<CleanService>();

	private CleanServiceImpl() {
		for(CleanJobs Job:CleanJobs.values()){
			this.cleanLogServices.add(Job.getCleanService());
		}
	}
	
	public static CleanService getInstanct() {
		return INSTANCT;
	}
	
	@Override
	public void clean(CleanDTO dto) {
		for (final CleanService cleanLogService : this.cleanLogServices) {
			cleanLogService.clean(dto);
			
		}
	}

}
