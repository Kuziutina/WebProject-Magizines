package DAO.Interfaces;

import Models.Magazine;
import Models.MagazineIssue;

import java.util.List;

public interface MagazineIssueDAOInterface extends DAOInterface<MagazineIssue, Integer>{
    int getMagazineIssueId(MagazineIssue magazineIssue);
    List<MagazineIssue> getByMagazine(Magazine magazine);
    double getScore(MagazineIssue magazineIssue);
}
