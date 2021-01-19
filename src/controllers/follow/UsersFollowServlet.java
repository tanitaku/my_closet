package controllers.follow;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Relation;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class UsersFollowServlet
 */
@WebServlet("/follow")
public class UsersFollowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersFollowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        EntityManager em = DBUtil.createEntityManager();

        //　LIKEをインスタンス化し、外部キーでIDを入れる,セット完了
           Relation f = new Relation();

           f.setFollower((User)request.getSession().getAttribute("login_user"));
           User u = em.find(User.class, Integer.parseInt(request.getParameter("id")));
           f.setFollowered(u);

           f.setFollow_flag(1);


            User u1 = f.getFollower();
            User u2 = f.getFollowered();


            Relation l = null;

               // 社員番号とパスワードが正しいかチェックする
               try {
                  l = em.createNamedQuery("checkFollow", Relation.class)
                         .setParameter("follower", u1)
                         .setParameter("followered", u2)
                         .getSingleResult();
                   } catch(NoResultException ex) {}


                 if(l != null) {
                     request.getSession().setAttribute("flush", "フォロー済みです。");
             }else {

                     em.getTransaction().begin();
                     em.persist(f);
                     em.getTransaction().commit();
                     request.getSession().setAttribute("flush", "フォローしました");
             }

                 em.close();

                 response.sendRedirect(request.getContextPath() + "/data");
    }

    }


