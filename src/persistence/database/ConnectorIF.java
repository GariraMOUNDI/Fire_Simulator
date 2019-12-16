package persistence.database;

public interface ConnectorIF {
    public void connectionToDatabase();
    public Object getData(Object arg);
    public void updateData(Object arg);
    public void deleteData(Object arg);
    public void insertData(Object arg);
    public void closeConnection();
    public void connectionException(Object arg);
}
