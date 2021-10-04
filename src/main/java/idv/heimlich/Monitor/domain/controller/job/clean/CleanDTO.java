package idv.heimlich.Monitor.domain.controller.job.clean;

import java.io.Serializable;
import java.util.Date;

/**
 * 清檔dto
 */
public class CleanDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date noeDate = new Date();// 今天
	
	public Date getNoeDate() {
		return this.noeDate;
	}

	public void setNoeDate(Date noeDate) {
		this.noeDate = noeDate;
	}
	
}
