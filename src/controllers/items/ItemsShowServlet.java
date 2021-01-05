package controllers.items;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Item;
import utils.DBUtil;

/**
 * Servlet implementation class ItemsShowServlet
 */
@WebServlet("/items/show")
public class ItemsShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemsShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        EntityManager em = DBUtil.createEntityManager();
        Item i = em.find(Item.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("item", i);
        request.setAttribute("_token", request.getSession().getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/items/show.jsp");
        rd.forward(request, response);


    }

}
