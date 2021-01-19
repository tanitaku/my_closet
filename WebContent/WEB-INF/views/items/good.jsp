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

        <h2>アイテム　一覧</h2>
            <table id="item_list">
            <tbody>
                <tr>
                    <th class="item_category">カテゴリ</th>
                    <th class="item_date">日付</th>
                    <th class="item_brand">ブランド</th>
                    <th class="item_action">操作</th>
                </tr>
                <c:forEach var="item" items="${items}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="item_category"><c:out value="${item.item.category}" /></td>
                        <td class="item_date"><fmt:formatDate value='${item.item.item_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="item_brand">${item.item.brand}</td>
                        <td class="item_action"><a href="<c:url value='/items/show?id=${item.item.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </c:param>
</c:import>