package persistence.interfaces;

import java.util.Optional;

public interface DAO<T> {
    public Optional<T> get(T obj);
    public void save(T obj);
    public void update(T obj);
    public void getAll();
    public void delete(T obj);
}
