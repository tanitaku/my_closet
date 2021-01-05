package controllers.items;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import models.Item;
import models.User;
import models.validators.ItemValidator;
import utils.DBUtil;

/**
 * Servlet implementation class ItemsCreateServlet
 */
@WebServlet("/items/create")
@MultipartConfig(maxFileSize = 2147483647, location="/tmp")
public class ItemsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemsCreateServlet() {
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

            Item i = new Item();

            i.setUser((User)request.getSession().getAttribute("login_user"));

            Date item_date = new Date(System.currentTimeMillis());
            String rd_str = request.getParameter("item_date");
            if(rd_str != null && !rd_str.equals("")) {
                item_date = Date.valueOf(request.getParameter("item_date"));
            }

            i.setItem_date(item_date);

            i.setCategory(request.getParameter("category"));
            i.setBrand(request.getParameter("brand"));
            i.setColor(request.getParameter("color"));
            i.setContent(request.getParameter("content"));

            String i2 = request.getParameter("price");
            Integer i3 = Integer.parseInt(i2);
            i.setPrice(i3);


            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            i.setCreated_at(currentTime);
            i.setUpdated_at(currentTime);

            request.setCharacterEncoding("UTF-8");


            //画像ストリームの取得
//            InputStream is= null;
//            Part filePart = request.getPart("image");
//            if (filePart != null) {
//                is = filePart.getInputStream();
//            }

            // 画像のデータ受け取り、ファイル名取得
            Part filePart1 = request.getPart("image");
            String fileName = getFileName(filePart1);

            // 画像を指定のディレクトリに保存
            String name1 = this.getFileName(filePart1);
            filePart1.write(getServletContext().getRealPath("./images/" + name1));


            i.setPath("./images/" + fileName);

            List<String> errors = ItemValidator.validate(i);

            if(errors.size() > 0) {

                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("item", i);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/items/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(i);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました");
                response.sendRedirect(request.getContextPath() + "/items/index");
            }
        }
    }

    // ファイルネーム取得
    private String getFileName(Part part) {
        String name = null;
            for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
            if (dispotion.trim().startsWith("filename")) {
                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
                name = name.substring(name.lastIndexOf("\\") + 1);
                break;
            }
        }
        return name;
    }

}
