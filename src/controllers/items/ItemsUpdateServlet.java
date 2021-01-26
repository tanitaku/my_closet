package controllers.items;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Item;
import models.validators.ItemValidator;
import utils.DBUtil;

/**
 * Servlet implementation class ItemsUpdateServlet
 */
@WebServlet("/items/update")
public class ItemsUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemsUpdateServlet() {
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

            Item i = em.find(Item.class, (Integer)(request.getSession().getAttribute("item_id")));



            i.setItem_date(Date.valueOf(request.getParameter("item_date")));

            i.setCategory(request.getParameter("category"));
            i.setBrand(request.getParameter("brand"));
            i.setColor(request.getParameter("color"));
            i.setContent(request.getParameter("content"));

            String i2 = request.getParameter("price");
            Integer i3 = Integer.parseInt(i2);
            i.setPrice(i3);


            List<String> errors = ItemValidator.validate(i);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("item", i);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/items/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("item_id");

                response.sendRedirect(request.getContextPath() + "/items/index");
            }

        }
    }

}
