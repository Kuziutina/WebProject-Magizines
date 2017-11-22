package DAO.Interfaces;

import Models.Review;

import java.util.List;

public interface ReviewDAOInterface<M, R> extends DAOInterface<R, Integer>{
    List<R> getReview(M model);
}
