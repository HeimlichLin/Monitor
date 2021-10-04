package idv.heimlich.Monitor.domain.controller.job.clean;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idv.heimlich.Monitor.common.db.sql.SqlWhere;
import idv.heimlich.Monitor.common.utils.YYYYMMDDUtils;

/**
 * 清除Clean User ACtion 紀錄
 */
public class CleanUserActionServiceImpl extends AbstactCleanServiceImpl {
	
	private final static Logger LOG = LoggerFactory.getLogger(CleanUserActionServiceImpl.class);

	@Override
	void myClean(CleanDTO dto) throws Exception {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(dto.getNoeDate());
		calendar.add(Calendar.DAY_OF_MONTH, this.getDayOfAgo());
//		final DoXdaoSession doXdaoSession = XdaoSessionManager.getDoXdaoSession();
//		final SqlWhere sqlWhere = new SqlWhere();
//		final String yyyymmdd = YYYYMMDDUtils.getText(calendar.getTime());
//		sqlWhere.add(new DoSqlPredicate("STARTDATE", SqlOp.LE, yyyymmdd));
//		final int deleteCnt = doXdaoSession.delete("USER_ACTION", sqlWhere);
//		LOG.info("delete USER_ACTION data  YYYYMMDD<={}  delete num:{}", yyyymmdd, deleteCnt);
	}

	/**
	 * 幾天前
	 * 
	 * @return
	 */
	public int getDayOfAgo() {
//		final int day = Integer.parseInt(ApContext.getContext().getSetting(
//				CleanUserActionServiceImpl.class.getName() + ".dayOfAgo"));
//		return day;
		return 1;
	}
	
}
