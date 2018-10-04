package servlets;

import model.Orders;
import org.json.JSONObject;
import repository.RepositoryOrders;
import service.ReaderXML;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/ordersList")
public class JsonOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        RepositoryOrders repositoryOrders = new RepositoryOrders();

        ReaderXML readerXML = new ReaderXML();
        List<Orders> orders = readerXML.writeXML();

        try {
            List<Orders> all = repositoryOrders.findAll();
            JSONObject jsonobj = new JSONObject();
            String listStr = jsonobj.put("arr", all).toString();
            System.out.println(listStr);
            out.write(listStr);
            out.flush();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
