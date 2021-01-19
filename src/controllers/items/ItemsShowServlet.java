package controllers.items;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Comment;
import models.Item;
import models.User;
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

        User i2 = i.getUser();





        // 質問者と回答者のデータがあれば、取得
        try {
           List<Comment> q = em.createNamedQuery("checkAnswer", Comment.class)
                  .setParameter("answer", i2)
                  .getResultList();
           request.setAttribute("comment", q);

            } catch(NoResultException ex) {}

        em.close();

        request.setAttribute("item", i);
        request.setAttribute("_token", request.getSession().getId());
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }



        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/items/show.jsp");
        rd.forward(request, response);


    }

}

