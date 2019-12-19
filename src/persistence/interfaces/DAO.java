package persistence.interfaces;

import java.util.Optional;

public interface DAO<T> {
    public Optional<T> get(T obj);
    public T getDataById(String key, Object value);
    public void save(T obj);
    public void update(T obj);
    public T getAll();
    public void delete(T obj);
}
