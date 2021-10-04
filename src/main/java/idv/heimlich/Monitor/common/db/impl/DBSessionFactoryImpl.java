package idv.heimlich.Monitor.common.db.impl;

import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idv.heimlich.Monitor.common.db.DBSessionManager;
import idv.heimlich.Monitor.common.db.IDBSession;
import idv.heimlich.Monitor.common.db.IDBSessionFactory;
import idv.heimlich.Monitor.common.db.code.DBConfig;
//import idv.heimlich.Monitor.common.log.LogFactory;

public class DBSessionFactoryImpl implements IDBSessionFactory {
	
	private static Logger LOGGER = LoggerFactory.getLogger(DBSessionFactoryImpl.class);

	@Override
	public IDBSession getXdaoSession(String conn) {
		final String connid = StringUtils.defaultIfEmpty(conn, DBSessionManager.PROP_DEFAULT_CONN_ID);
		Objects.requireNonNull(connid, "無此定義coonid" + conn);
		DBConfig dbConfig = DBConfig.valueOf(connid);
//		LOGGER.debug("use connid:" + connid);
		return dbConfig.getXdaoSession();
	}

}
