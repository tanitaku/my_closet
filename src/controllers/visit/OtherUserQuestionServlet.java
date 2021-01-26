package controllers.visit;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Comment;
import models.Item;
import models.User;
import models.validators.CommentValidator;
import utils.DBUtil;

/**
 * Servlet implementation class OtherUserQuestionServlet
 */
@WebServlet("/question")
public class OtherUserQuestionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OtherUserQuestionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();

        // Questionをインスタンス化し、各情報を保存
        Comment q = new Comment();

        //質問した人の情報
        q.setUser((User)request.getSession().getAttribute("login_user"));


        //質問された人の情報を保存
        Item i = em.find(Item.class, Integer.parseInt(request.getParameter("id")));
        User answer = i.getUser();

        q.setItem(i);

        q.setAnswer(answer);

        q.setComment(request.getParameter("comment"));

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        q.setTime(currentTime);


        List<String> errors = CommentValidator.validate(q);

        if(errors.size() > 0) {

            em.close();

            request.setAttribute("comment", q);
            request.getSession().setAttribute("errors", errors);
            request.setAttribute("_token", request.getSession().getId());

            response.sendRedirect(request.getContextPath() + "/items/show?id=" + i.getId());
        } else {
            em.getTransaction().begin();
            em.persist(q);
            em.getTransaction().commit();
            em.close();

            request.setAttribute("comment", q);
            request.getSession().setAttribute("flush", "コメントを投稿しました");
            response.sendRedirect(request.getContextPath() + "/items/show?id=" + i.getId());
        }
    }



    }


