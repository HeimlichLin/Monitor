package idv.heimlich.Monitor.domain.dao;

import java.math.BigDecimal;

public interface GetNumberDAO {
	
	public BigDecimal getSerialNo(String pgmId, String... syscode);

}
