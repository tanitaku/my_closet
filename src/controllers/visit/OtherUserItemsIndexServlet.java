package controllers.visit;

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
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class OtherUserItemsIndexServlet
 */
@WebServlet("/other/user/items")
public class OtherUserItemsIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OtherUserItemsIndexServlet() {
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

        User u2 = em.find(User.class, Integer.parseInt(request.getParameter("id")));


        List<Item> items = em.createNamedQuery("getMyAllItems", Item.class)
                .setParameter("user", u2)
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();


        request.setAttribute("user", u2.getUser_name());
        request.setAttribute("user_id", u2.getId());

        em.close();

        request.setAttribute("items", items);


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/items/other.jsp");
        rd.forward(request, response);
    }
    }


