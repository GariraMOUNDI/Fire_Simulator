package businessLogic;

import persistence.data.Matrice;
import persistence.data.Terrain;
import persistence.factories.DAOType;
import persistence.factories.MongoDBDAOFactory;
import persistence.interfaces.DAO;
import ui.interfaces.LoginInterface;

import java.util.List;

/**
 * The type Map management facade.
 */
public class MapManagementFacade {
    private LoginInterface loginIF;
    private DAO dao;
    private Terrain currentTerrain;
    private static MapManagementFacade instance = null;
    private SessionFacade session;

    public SessionFacade getSession(){
        return session;
    }
    /**
     * Gets current terrain.
     *
     * @return the current terrain
     */
    public Terrain getCurrentTerrain() {
        return currentTerrain;
    }

    /**
     * Sets current terrain.
     *
     * @param currentTerrain the current terrain
     */
    public void setCurrentTerrain(Terrain currentTerrain) {
        this.currentTerrain = currentTerrain;
    }
    private MapManagementFacade(LoginInterface loginIF) {
        session = SessionFacade.getInstance(loginIF);
        this.loginIF = loginIF;
        dao = MongoDBDAOFactory.getInstance().createDAO(DAOType.Terrain);
    }

    /**
     * Get the instance of map management facade.
     *
     * @param loginIF the login interface
     * @return the map management facade
     */
    public static MapManagementFacade getInstance(LoginInterface loginIF){
        if (instance == null)
            instance = new MapManagementFacade(loginIF);
        return instance;
    }

    /**
     * Get map terrain.
     *
     * @param _id the id
     * @return the terrain
     */
    public Terrain getMap(Object _id){return (Terrain) dao.getDataById("_id", _id);}

    /**
     * Get user maps list.
     *
     * @param username the username
     * @return the list
     */
    public List<Terrain> getUserMaps(String username){
        return (List<Terrain>) dao.getDataById("username", username);
    }

    /**
     * Delete map.
     *
     * @param map the map
     */
    public void deleteMap(Terrain map){
        dao.deleteData(map);
    }


    /**
     * Create map.
     *
     * @param name     the name
     * @param map      the map
     * @param username the username
     */
    public void createMap(String name, Matrice map, String username) {
        // TODO
        // BE SURE NAMES ARE UNIQUE
        dao.insertData(new Terrain(name, map, username));
        this.currentTerrain = ((List<Terrain>) dao.getDataById("name", name)).get(0);

    }

    /**
     * Save map.
     *
     * @param map the map
     */
    public void saveMap(Terrain map){
        dao.updateData(map);
    }
}
