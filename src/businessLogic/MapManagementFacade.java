package businessLogic;

import com.google.gson.Gson;
import org.bson.types.ObjectId;
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
    private Terrain currentTerrain;
    private static MapManagementFacade instance = null;


    public Terrain getCurrentTerrain() {
        return currentTerrain;
    }

    public void setCurrentTerrain(Terrain currentTerrain) {
        this.currentTerrain = currentTerrain;
    }
    private MapManagementFacade(LoginInterface loginIF) {
        this.loginIF = loginIF;
        dao = MongoDBDAOFactory.getInstance().createDAO(DAOType.Terrain);
    }
    public static MapManagementFacade getInstance(LoginInterface loginIF){
        if (instance == null)
            instance = new MapManagementFacade(loginIF);
        return instance;
    }

    public Terrain getMap(Object _id){return (Terrain) dao.getDataById("_id", _id);}

    public List<Terrain> getUserMaps(String username){
        return (List<Terrain>) dao.getDataById("username", username);
    }

    public void deleteMap(Terrain map){
        dao.deleteData(map);
    }


    public void createMap(String name, Matrice map, String username) {
        // TODO
        // BE SURE NAMES ARE UNIQUE
        dao.insertData(new Terrain(name, map, username));
        this.currentTerrain = ((List<Terrain>) dao.getDataById("name", name)).get(0);

    }

    public void saveMap(Terrain map){
        dao.updateData(map);
    }
}
