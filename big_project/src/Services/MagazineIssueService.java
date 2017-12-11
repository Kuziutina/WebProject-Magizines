package Services;

import DAO.Interfaces.MagazineIssueDAOInterface;
import Models.MagazineIssue;
import Services.Interfaces.MagazineIssueServiceInterface;

import java.util.List;

public class MagazineIssueService implements MagazineIssueServiceInterface{
    private MagazineIssueDAOInterface magazineIssueDAO;

    public MagazineIssueService(MagazineIssueDAOInterface magazineIssueDAO) {
        this.magazineIssueDAO = magazineIssueDAO;
    }

    @Override
    public List<MagazineIssue> getAllMagazineIssues() {
        return magazineIssueDAO.findAll();
    }

    @Override
    public MagazineIssue getMagazineIssue(int id) {
        return magazineIssueDAO.find(id);
    }

    @Override
    public void initMagazineIssue(MagazineIssue magazineIssue) {
        magazineIssue.getReviews();
    }
}
