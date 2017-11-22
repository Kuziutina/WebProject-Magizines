package DAO.Interfaces;

import Models.Magazine;
import Models.MagazineIssue;

import java.util.List;

public interface MagazineDAOInterface extends DAOInterface<Magazine, Long> {
    int getMagazineId(Magazine magazine);
    double getScore(Magazine magazine);
    List<Magazine> getNewer(int n);
    List<Magazine> getPopular(int n);
    List<Magazine> getByNamePattern(String name_part);

}
