package Servlets;

import Helper.GenerateString;
import Models.Magazine;
import DAO.DAOImpl.MagazineDAO;
import Services.Interfaces.MagazineServiceInterface;
import Services.MagazineService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@WebServlet(name = "CreateMagazineServlet")
@MultipartConfig
public class CreateMagazineServlet extends HttpServlet {
    private MagazineServiceInterface magazineService;

    @Override
    public void init() throws ServletException {
        magazineService = new MagazineService(new MagazineDAO());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Part imagePart = request.getPart("cover");
        String cover[] = imagePart.getContentType().split("/");
        Magazine magazine = new Magazine().newBuilder().setName(title).setDescription(description).build();
        if (!cover[0].equals("image")) {
            magazine.setPicture_path("project_images/default.png");
        }
        else {
            loadImage(magazine, imagePart, cover[1]);
        }

        magazineService.addMagazine(magazine);

        int id = magazineService.getMagazineId(magazine);

        response.sendRedirect("/magazine/" + id);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void loadImage(Magazine magazine, Part imagePart, String exp) {
        String fileName = "project_images/" + GenerateString.generate() + "." + exp;
        String path = "D:/";
        File uploads = new File(path);
        File file = new File(uploads, fileName);

        try (InputStream input = imagePart.getInputStream()) {
            Files.copy(input, file.toPath());
            imagePart.getInputStream().close();
            imagePart.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
//            imagePart.write(path);

        magazine.setPicture_path(fileName);
    }
}
