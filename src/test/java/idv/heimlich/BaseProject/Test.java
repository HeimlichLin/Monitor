package idv.heimlich.BaseProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idv.heimlich.Monitor.common.db.IDBSession;
import idv.heimlich.Monitor.common.db.IDBSessionFactory;
import idv.heimlich.Monitor.common.db.impl.DBSessionFactoryImpl;
//import idv.heimlich.Monitor.common.log.LogFactory;

public class Test {
	
	private static Logger LOGGER = LoggerFactory.getLogger(Test.class);
	
	public static void main(String[] args) {
		LOGGER.debug("Test Start");
		IDBSessionFactory sessionFactory = new DBSessionFactoryImpl();
		IDBSession session = sessionFactory.getXdaoSession("");
		
	}

}
