package DAO.Interfaces;

import java.util.List;

public interface DAOInterface<M, L> {
    boolean add(M model);
    M find(L id);
    void delete(M model);
    void update(M model);
    List<M> findAll();
}
