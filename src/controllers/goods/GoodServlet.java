package controllers.goods;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Item;
import models.Like;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class GoodServlet
 */
@WebServlet("/items/good")
public class GoodServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param r2
     * @param e
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

        EntityManager em = DBUtil.createEntityManager();

     //　LIKEをインスタンス化し、外部キーでIDを入れる,セット完了
        Like l2 = new Like();

        l2.setUser((User)request.getSession().getAttribute("login_user"));
        l2.setItem(em.find(Item.class, Integer.parseInt(request.getParameter("id"))));


         User e1 = l2.getUser();
         Item r21 = l2.getItem();


         // パラメータで渡すアイテムIDを取得
         Integer ig = r21.getId();
         Item i = em.find(Item.class, ig);

         Like l = null;

            // 社員番号とパスワードが正しいかチェックする
            try {
               l = em.createNamedQuery("checkId", Like.class)
                      .setParameter("user", e1)
                      .setParameter("item", r21)
                      .getSingleResult();
                } catch(NoResultException ex) {}


              if(l != null) {
                  request.getSession().setAttribute("flush", "いいね済みです。");
          }else {


              String goods = request.getParameter("good");
              if(goods != null) {
                   if(l2.getGoods() == null) {
                       l2.setGoods(1);
                   }else {
                  Integer count = l2.getGoods();
                  count++;
                  l2.setGoods(count);
                   }
              }

                  em.getTransaction().begin();
                  em.persist(l2);
                  em.getTransaction().commit();
                  em.close();

                  request.getSession().setAttribute("goods", l2.getGoods());
                  request.getSession().setAttribute("flush", "いいねを投稿しました。");
   }
               response.sendRedirect(request.getContextPath() + "/items/show?id=" + i.getId());

     }




}




