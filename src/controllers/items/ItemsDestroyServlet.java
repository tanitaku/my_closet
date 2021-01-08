package controllers.items;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Item;
import utils.DBUtil;

/**
 * Servlet implementation class ItemsDestroyServlet
 */
@WebServlet("/items/destroy")
public class ItemsDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemsDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Item i = em.find(Item.class, Integer.parseInt(request.getParameter("id")));

            String dc = null;
                try {
                        dc = em.createNamedQuery("deleteColumn", String.class)
                                .setParameter("id", i.getId())
                                .getSingleResult();
                } catch(NoResultException ex) {}

                if(dc != null) {

                    i.setUpdated_at(new Timestamp(System.currentTimeMillis()));
                    em.getTransaction().begin();
                    em.getTransaction().commit();
                    em.close();
                    request.getSession().setAttribute("flush", "フォローを解除しました。");
                } else {
                        request.getSession().setAttribute("flush", "データが存在しません。");
                }

            response.sendRedirect(request.getContextPath() + "/items/index");
        }
    }

}


