package persistence.interfaces;

public interface MethodFactory {
    public DAO createDAO(DAOType type);
}
