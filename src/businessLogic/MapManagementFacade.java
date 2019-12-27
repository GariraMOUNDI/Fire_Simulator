package businessLogic;

import com.google.gson.Gson;
import persistence.data.Matrice;
import persistence.data.Terrain;
import persistence.factories.DAOType;
import persistence.factories.MongoDBDAOFactory;
import persistence.interfaces.DAO;
import ui.interfaces.LoginInterface;

import java.util.List;

public class MapManagementFacade {
    private LoginInterface loginIF;
    private DAO dao;
    private Terrain CurrentTerrain;
    private Gson gson = new Gson();
    private static MapManagementFacade instance = null;


    private MapManagementFacade(LoginInterface loginIF) {
        this.loginIF = loginIF;
        dao = MongoDBDAOFactory.getInstance().createDAO(DAOType.Terrain);
    }
    public static MapManagementFacade getInstance(LoginInterface loginIF){
        if (instance == null)
            instance = new MapManagementFacade(loginIF);
        return instance;
    }

    public List<Terrain> getUserMaps(String username){
        return (List<Terrain>) dao.getDataById("username", username);
    }

    public void createMap(String name, Matrice map) {
        dao.insertData(new Terrain(name, map));
    }

}
