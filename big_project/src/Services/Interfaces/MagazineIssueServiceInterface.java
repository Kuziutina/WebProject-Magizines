package Services.Interfaces;

import Models.MagazineIssue;

import java.util.List;

public interface MagazineIssueServiceInterface {
    List<MagazineIssue> getAllMagazineIssues();
    MagazineIssue getMagazineIssue(int id);
    void initMagazineIssue(MagazineIssue magazineIssue);
}
