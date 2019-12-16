package persistence.dao;

import persistence.data.Tournament;
import persistence.interfaces.DAO;

import java.util.Optional;

public class TournamentDAO implements DAO<Tournament> {

    @Override
    public Optional<Tournament> get(Tournament obj) {
        return Optional.empty();
    }

    @Override
    public void save(Tournament obj) {

    }

    @Override
    public void update(Tournament obj) {

    }

    @Override
    public void getAll() {

    }

    @Override
    public void delete(Tournament obj) {

    }
}
