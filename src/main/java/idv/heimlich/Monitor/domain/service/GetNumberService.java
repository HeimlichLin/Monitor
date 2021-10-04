package idv.heimlich.Monitor.domain.service;

import idv.heimlich.Monitor.domain.code.GetNumberType;

public interface GetNumberService {

	/**
	 * 取號
	 */
	public String getCount(GetNumberType getNumberType, String... codes);

}
