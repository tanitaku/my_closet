<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>ユーザー　新規登録ページ</h2>

        <form method="POST" action="<c:url value='/new/create' />" >
            <label for="user_name">ユーザー名</label><br />
            <input type="text" name="user_name" />
            <br /><br />


            <label for="email">メールアドレス</label><br />
            <input type="text" name="email" />
            <br /><br />


            <label for="height">身長(cm)</label><br />
            <input type="text" name="height" />
            <br /><br />

            <label for="password">パスワード</label><br />
            <input type="password" name="password" />
            <br /><br />

            <label for="admin_flag">権限</label><br />
            <select name="admin_flag">
                <option value="0"<c:if test="${user.admin_flag == 0}"> selected</c:if>>一般</option>
                <option value="1"<c:if test="${user.admin_flag == 1}"> selected</c:if>>管理者</option>
            </select>
            <br /><br />

            <input type="hidden" name="_token" value="${_token}" />
            <button type="submit">登録</button>
        </form>

        <p><a href="<c:url value='/login' />">ログイン画面に戻る</a></p>
    </c:param>
</c:import>