package controllers.items;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class ItemsIndexServlet
 */
@WebServlet("/items/index")
public class ItemsIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemsIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();

        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }

        List<Item> items = em.createNamedQuery("getAllItems", Item.class)
                            .setFirstResult(15 * (page - 1))
                            .setMaxResults(15)
                            .getResultList();

        long items_count = (long)em.createNamedQuery("getItemsCount", Long.class)
                                    .getSingleResult();

        em.close();

        request.setAttribute("items", items);
        request.setAttribute("items_count", items_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/items/index.jsp");
        rd.forward(request, response);
    }

}
