package Services;

import DAO.DAOImpl.MagazineDAO;
import Models.Magazine;
import Services.Interfaces.MagazineServiceInterface;

import java.util.List;

public class MagazineService implements MagazineServiceInterface {

    private MagazineDAO magazineDAO;

    public MagazineService(MagazineDAO magazineDAO) {
        this.magazineDAO = magazineDAO;
    }

    @Override
    public List<Magazine> getNewest() {
        return magazineDAO.getNewer(9);
    }

    @Override
    public List<Magazine> getPopular() {
        return magazineDAO.getPopular(9);
    }

    @Override
    public List<Magazine> getAllMagazines() {
        return magazineDAO.findAll();
    }

    @Override
    public Magazine getMagazineById(Integer id) {
        return magazineDAO.find(Long.valueOf(id));
    }

    @Override
    public void addMagazine(Magazine magazine) {
        magazineDAO.add(magazine);
    }

    @Override
    public int getMagazineId(Magazine magazine) {
        return magazineDAO.getMagazineId(magazine);
    }

    @Override
    public void fillMagazine(Magazine magazine) {
        magazine.getCopies();
        magazine.getReviews();
    }


}
