package persistence.interfaces;

import persistence.factories.DAOType;

import java.util.Optional;

public interface DAO<T> {
    public Optional<T> get(T obj);
    public void save(T obj);
    public void update(T obj);
    public Object getAll();
    public void delete(T obj);
}
