<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>アイテム　新規登録ページ</h2>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type">
    </head>

            <Div Align="center">
        <form method="POST" action="<c:url value='/items/create' />" enctype="multipart/form-data">
            <c:import url="/WEB-INF/views/items/_form.jsp" />
        </form>
            </Div>

        <p><a href="<c:url value='/items/index' />">一覧に戻る</a></p>

</html>
    </c:param>
</c:import>



