package idv.heimlich.Monitor.domain.dao.impl;

import java.util.List;

import idv.heimlich.Monitor.common.dao.RowMap;
import idv.heimlich.Monitor.domain.bean.JobqueDo;
import idv.heimlich.Monitor.domain.code.JobqueStatus;
import idv.heimlich.Monitor.domain.dao.JobqueDAO;

//Job隊伍
public class JobqueDAOImpl implements JobqueDAO {

	public static final JobqueDAOImpl INSTANCE = new JobqueDAOImpl();

	final static String TABLE = "JOBQUE";
	
//	public JobqueDAOImpl() {
//		super(TABLE);
//	}
	
//	@Override
//	public MapConverter<JobquePo> getConverter() {
//		return JobquePo.CONVERTER;
//	}

//	@Override
//	public SqlWhere getPkSqlWhere(JobquePo po) {
//		SqlWhere sqlWhere = new SqlWhere();
//		sqlWhere.add(JobquePo.COLUMNS.TRANACTION_ID.name(), po.getTranactionId());
//		return sqlWhere;
//	}
	
	@Override
	public List<JobqueDo> quertyByStatus(
			JobqueStatus... jobqueStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(String tranactionId, RowMap rowMap) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public List<JobquePo> quertyByStatus(JobqueStatus... jobqueStatus) {
//		DoXdaoSession doXdaoSession = XdaoSessionManager.getDoXdaoSession();
//		SqlWhere sqlWhere = new SqlWhere(SqlWhere.OR);
//
//		for (JobqueStatus status : jobqueStatus) {
//			sqlWhere.add(JobquePo.COLUMNS.STAUTS.name(), status.name());
//		}
//
//		SelectBuilder selectBuilder = new SelectBuilder();
//		selectBuilder.from(TABLE).where(sqlWhere).orderBy(JobquePo.COLUMNS.CREATE_TIME.name(), true);
//		return selectBuilder.list(JobquePo.CONVERTER, doXdaoSession);
//	}
//
//	@Override
//	public void update(String tranactionId, DataObject dataObject) {
//		SqlWhere sqlWhere = new SqlWhere();
//		sqlWhere.add(JobquePo.COLUMNS.TRANACTION_ID.name(), tranactionId);
//		DoXdaoSession doXdaoSession = XdaoSessionManager.getDoXdaoSession();
//		try {
//			doXdaoSession.update(TABLE, dataObject, sqlWhere);
//		} catch (XdaoException e) {
//			e.printStackTrace();
//		}
//	}

}
