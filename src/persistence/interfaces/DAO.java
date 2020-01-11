package persistence.interfaces;

/**
 * The interface Dao.
 *
 * @param <T> the type parameter, It can represent User, Tournaments, etc ...
 */
public interface DAO<T> {
    /**
     * Gets all data on the setting type.
     *
     * @return the all data
     */
    public Object getAllData();

    /**
     * Gets data on the setting type by id.
     *
     * @param key   the key
     * @param value the value
     * @return the data by id
     */
    public Object getDataById(String key, Object value);

    /**
     * Update data concerning the setting type.
     *
     * @param arg the instance of the setting type.
     */
    public void updateData(T arg);

    /**
     * Delete data concerning the setting type.
     *
     * @param arg the instance of the setting type.
     */
    public void deleteData(T arg);

    /**
     * Insert the arg in database.
     *
     * @param arg the instance of the setting type.
     */
    public void insertData(T arg);
}
