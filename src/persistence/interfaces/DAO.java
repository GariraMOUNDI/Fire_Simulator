package persistence.interfaces;

import persistence.data.User;

import java.util.List;

public interface DAO<T> {
    public List<T> getAllData();
    public T getDataById(String key, Object value);
    public void updateData(T arg);
    public void deleteData(T arg);
    public void insertData(T arg);
}
