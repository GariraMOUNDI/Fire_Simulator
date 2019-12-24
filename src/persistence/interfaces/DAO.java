package persistence.interfaces;

public interface DAO<T> {
    public Object getAllData();
    public Object getDataById(String key, Object value);
    public void updateData(T arg);
    public void deleteData(T arg);
    public void insertData(T arg);
}
