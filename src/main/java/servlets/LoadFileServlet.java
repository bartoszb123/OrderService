package servlets;

import model.Orders;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import repository.RepositoryOrders;
import service.ReaderXML;
import service.StringToXML;

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
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

@WebServlet("/loadFile")
@MultipartConfig
public class LoadFileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String fileXML = req.getParameter("fileXML");

        resp.setContentType("text/xml");
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();

        boolean isMultipartContent = ServletFileUpload.isMultipartContent(req);
        if (!isMultipartContent) {
            out.println("You are not trying to upload");
            return;
        }

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> fields = upload.parseRequest(req);
            Iterator<FileItem> it = fields.iterator();
            if (!it.hasNext()) {
                out.println("No fields found");
                return;
            }
            while (it.hasNext()) {

                FileItem fileItem = it.next();

//                buildOrderInDB(fileItem);


//                boolean isFormField = fileItem.isFormField();
//                    out.println("<td>file form field</td><td>FIELD NAME: " + fileItem.getFieldName() +
//                            "<br/>STRING: " + fileItem.getString() +
//                            "<br/>NAME: " + fileItem.getName() +
//                            "<br/>CONTENT TYPE: " + fileItem.getContentType() +
//                            "<br/>SIZE (BYTES): " + fileItem.getSize() +
//                            "<br/>TO STRING: " + fileItem.toString()
//                    );

                StringToXML saveFile = new StringToXML();
                if (!isInOrders(fileItem.getName())) {
                    saveFile.convertToXml(fileItem.getString("UTF-8"), fileItem.getName());
                    ReaderXML readerXML = new ReaderXML();
                    List<Orders> orders = readerXML.buildOrderfromXML(fileItem.getName());
                    RepositoryOrders repo = new RepositoryOrders();
                    for(Orders order:orders) {
                        repo.addToDB(order);
                    }
                } else {
                    System.out.println("Zamowienie: " + fileItem.getName() + " -jest juz w bazie danych!!!!");
                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    private void buildOrderInDB(FileItem fileItem) {
//
//        new Orders.OrderBuilder().
//
//
//
//    }


    public boolean isInOrders(String nameFile) {

        final File folder = new File("Orders");

        for (final File fileEntry : folder.listFiles()) {

            if (nameFile.equals(fileEntry.getName())) {
                System.out.println("jest na liscie");
                return true;
            }

        }
        return false;
    }


//    listFilesForFolder(folder);


}

