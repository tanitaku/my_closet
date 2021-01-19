package controllers.follow;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Relation;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class UsersFollowerServlet
 */
@WebServlet("/follower")
public class UsersFollowerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersFollowerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        EntityManager em = DBUtil.createEntityManager();

        // 開くページ数を取得（デフォルトが1ページ目）
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) {}




        Relation test2 = new Relation();
        test2.setFollower((User)request.getSession().getAttribute("login_user"));
        User u = test2.getFollower();

        // フォロワーを取得
        List<Relation> fe = em.createNamedQuery("getMyAllFollowers", Relation.class)
                .setParameter("follower", u)
                .getResultList();



        em.close();


        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }


        // リクエストスコープに保存する
        request.setAttribute("page", page);
        request.setAttribute("user_id", u.getId());
        request.setAttribute("fes", fe);


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/follower.jsp");
        rd.forward(request, response);

    }
    }


