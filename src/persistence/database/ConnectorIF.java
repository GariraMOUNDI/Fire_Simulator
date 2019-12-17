package persistence.database;

public interface ConnectorIF {
    public Object getAllData(Object arg);
    public Object getDataById(String key,Object value);
    public void updateData(Object arg);
    public void deleteData(Object arg);
    public void insertData(Object arg);
    public void closeConnection();
    public void connectionException(Object arg);
}
