package controllers.items;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Item;


/**
 * Servlet implementation class ItemsNewServlet
 */
@WebServlet("/items/new")
public class ItemsNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemsNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("_token", request.getSession().getId());

        // 新規アイテムを登録する際に、今日の日付を登録しておく。

        Item i = new Item();
        i.setItem_date(new Date(System.currentTimeMillis()));
        request.setAttribute("item", i);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/items/new.jsp");
        rd.forward(request, response);
    }

}
