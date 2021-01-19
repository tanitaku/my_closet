<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>

        <h2><c:out value="${user}" />さんのアイテム　一覧</h2>

                <div id="imgindex">
                    <c:forEach var="item" items="${items}">
                       <a href="<c:url value='/items/show?id=${item.id}' />"><img src="../${item.path}"></a>
                    </c:forEach>
                </div>
        <p><a href="<c:url value='/follow?id=${user_id}' />">フォローする</a></p>


    </c:param>
    </c:import>