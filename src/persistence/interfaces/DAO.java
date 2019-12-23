package persistence.interfaces;

public interface DAO<T> {
    public void connectionToDatabase();
    public Object getAllData();
    public Object getDataById(String key, Object value);
    public void updateData(T arg);
    public void deleteData(T arg);
    public void insertData(T arg);
    public void closeConnection();
    public void connectionException(Object arg);
    public void setCollectionName(String arg);
}
