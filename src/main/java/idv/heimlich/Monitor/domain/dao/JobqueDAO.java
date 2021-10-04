package idv.heimlich.Monitor.domain.dao;

import java.util.List;

import idv.heimlich.Monitor.common.dao.RowMap;
import idv.heimlich.Monitor.domain.bean.JobqueDo;
import idv.heimlich.Monitor.domain.code.JobqueStatus;

public interface JobqueDAO {
	
	/**
	 * 取得清單透過狀態
	 * 
	 * @param jobqueStatus
	 * @return
	 */
	public List<JobqueDo> quertyByStatus(JobqueStatus... jobqueStatus);

	/**
	 * 更新狀態
	 * 
	 * @param tranactionId
	 * @param jobqueStatus
	 * @return
	 */
	public void update(String tranactionId, RowMap dataObject);

}
