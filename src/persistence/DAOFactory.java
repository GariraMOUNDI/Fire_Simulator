package persistence;

public class DAOFactory extends AbstractFactory {
    // Singleton Pattern
    private static DAOFactory dao = null;
    public static DAOFactory getFactory(DAOType type){
        if (dao == null)
            dao = new DAOFactory();
        return dao;
    }

    private DAOFactory(){ }

}
