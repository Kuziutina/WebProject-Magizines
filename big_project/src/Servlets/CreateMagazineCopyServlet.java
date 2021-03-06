package Servlets;

import Helper.GenerateString;
import Models.MagazineIssue;
import DAO.DAOImpl.MagazineIssueDAO;

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
import java.util.Date;

@WebServlet(name = "CreateMagazineCopyServlet")
@MultipartConfig
public class CreateMagazineCopyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("copy_title");
        String description = request.getParameter("copy_description");
        Part imagePart = request.getPart("cover");
        String magazineId = request.getPathInfo().replaceFirst("/", "");
        String cover[] = imagePart.getContentType().split("/");
        Part magazinePart = request.getPart("magazine-file");
        String extension = getExtension(getFileName(magazinePart));

        MagazineIssue magazineIssue = new MagazineIssue();
        magazineIssue.setName(title);
        magazineIssue.setDescription(description);
        magazineIssue.setDate(new Date(System.currentTimeMillis()));
        magazineIssue.setMagazine_id(Integer.parseInt(magazineId));
        if (!cover[0].equals("image")) {
            magazineIssue.setPicture_path("project_images/default.png");
        }
        else {

            String fileName = "project_images/" + GenerateString.generate() + "." + cover[1];
            String path = "D:/";
            File file = new File(new File(path), fileName);

            try (InputStream input = imagePart.getInputStream()) {
                Files.copy(input, file.toPath());
            }
//            imagePart.write(path);
            imagePart.getInputStream().close();
            imagePart.delete();
            magazineIssue.setPicture_path(fileName);
        }


        String fileMagazineName = "project_documents/" + GenerateString.generate() + "." + extension;
        String path = "D:/";
        File file = new File(new File(path), fileMagazineName);

        try (InputStream input = magazinePart.getInputStream()) {
            Files.copy(input, file.toPath());
        }
//            imagePart.write(path);
        magazinePart.getInputStream().close();
        magazinePart.delete();
        magazineIssue.setPath(fileMagazineName);

        MagazineIssueDAO magazineIssueDAO = new MagazineIssueDAO();
        magazineIssueDAO.add(magazineIssue);
        int id = magazineIssueDAO.getMagazineIssueId(magazineIssue);

        response.sendRedirect("/magazine/" + magazineId + "/" + id);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }

    private String getExtension(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
