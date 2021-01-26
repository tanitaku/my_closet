<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${hasError}">
            <div id="flush_error">
                ユーザー名かパスワードが間違っています。
            </div>
        </c:if>
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>




        <Div Align="center">

            <h1>衣類を購入する際に役立つ！</h1><br />
            <h1>過去の衣類を管理、気になるアイテムの着心地を質問出来ます</h1>
            <p><a href="<c:url value='/new/user' />">新規登録</a></p>
            <br /><br />

                <h1>ログイン</h1>
                <form method="POST" action="<c:url value='/login' />">
                    <label for="user_name">ユーザー名</label><br />
                    <input type="text" name="user_name" value="${user_name}" />
                    <br /><br />

                    <label for="password">パスワード</label><br />
                    <input type="password" name="password" />
                    <br /><br />

                    <input type="hidden" name="_token" value="${_token}" />
                    <button type="submit">ログイン</button>
                </form>
            </DIV>
    </c:param>
</c:import>


