package persistence.dao;

import persistence.data.User;
import persistence.database.ConnectorIF;
import persistence.interfaces.DAO;

import java.util.ArrayList;
import java.util.Optional;

public class UserDAO implements DAO<User> {

    private ArrayList<User> users;
    private ConnectorIF connector;

    public User getByUsername(String username) {
        User temp = new User(username,"password");
        return temp;
    }

    @Override
    public Optional<User> get(User obj) {
        return Optional.empty();
    }

    @Override
    public void save(User obj) {

    }

    @Override
    public void update(User obj) {

    }

    @Override
    public void getAll() {

    }

    @Override
    public void delete(User obj) {

    }
}
