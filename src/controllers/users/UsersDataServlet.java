package controllers.users;

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
 * Servlet implementation class UsersDataServlet
 */
@WebServlet("/data")
public class UsersDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersDataServlet() {
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

     // 最大件数と開始位置を指定してメッセージを取得
        List<User> users = em.createNamedQuery("getAllUsers", User.class)
                                     .setFirstResult(15 * (page - 1)) // ページ１の場合、０から15件表示するということ
                                     .setMaxResults(15)
                                     .getResultList();

     // 全件数を取得
        try {
        long users_count = (long)em.createNamedQuery("getUsersCount", Long.class)
                                        .getSingleResult();
        request.setAttribute("users_count", users_count);
        } catch(Exception e) {
            request.setAttribute("users_count", 0);
        }

        Relation test = new Relation();
        test.setFollower((User)request.getSession().getAttribute("login_user"));
        User u = test.getFollower();


        test.setFollower((User)request.getSession().getAttribute("login_user"));

        User u1 = test.getFollower();

        List<Relation> fe = em.createNamedQuery("getMyAllFollowers", Relation.class)
                .setParameter("follower", u1)
                .getResultList();



        em.close();


        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }


        // リクエストスコープに保存する
        request.setAttribute("users", users);
        request.setAttribute("page", page);
        request.setAttribute("user_id", u.getId());
        request.setAttribute("fes", fe);


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/data.jsp");
        rd.forward(request, response);

    }
    }


