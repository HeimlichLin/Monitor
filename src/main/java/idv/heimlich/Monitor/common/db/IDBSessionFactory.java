package idv.heimlich.Monitor.common.db;

public interface IDBSessionFactory {

	IDBSession getXdaoSession(String conn);

}
