package servlets;

import repository.RepositoryOrders;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/Orders")
public class OrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RepositoryOrders repo = new RepositoryOrders();
        try {
            repo.initDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("orders.html");
//        req.getRequestDispatcher("orders.html").forward(req, resp);

    }
}
