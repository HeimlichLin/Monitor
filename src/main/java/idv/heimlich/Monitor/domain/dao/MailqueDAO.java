package idv.heimlich.Monitor.domain.dao;

import java.util.List;

import idv.heimlich.Monitor.domain.bean.MailqueDo;
import idv.heimlich.Monitor.domain.code.MailqueStatus;

public interface MailqueDAO {
	
	/**
	 * 取得待寄信信件資訊
	 */
	public List<MailqueDo> queryMails();

	/**
	 * 更新信件狀態
	 */
	public void update(String id, MailqueStatus status);

}
