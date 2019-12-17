package persistence.factories;

public class DAOFactory extends AbstractFactory {
    // Singleton Pattern
    private static DAOFactory dao = null;
    public static DAOFactory getFactory(){
        if (dao == null)
            dao = new DAOFactory();
        return dao;
    }

    private DAOFactory(){ }

}
