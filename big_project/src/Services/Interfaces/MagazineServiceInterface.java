package Services.Interfaces;

import Models.Magazine;

import java.util.List;

public interface MagazineServiceInterface {
    List<Magazine> getNewest();
    List<Magazine> getPopular();
    List<Magazine> getAllMagazines();
    Magazine getMagazineById(Integer id);
    void addMagazine(Magazine magazine);
    int getMagazineId(Magazine magazine);
    void fillMagazine(Magazine magazine);
}
