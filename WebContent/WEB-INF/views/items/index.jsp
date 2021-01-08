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
                    <c:forEach  var="feses" items="${fes}">
                      <c:if test="${item.user.id  == feses.id}">
                        <td class="item_category"><c:out value="${item.category}" /></td>
                        <td class="item_date"><fmt:formatDate value='${item.item_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="item_brand">${item.brand}</td>
                        <td class="item_action"><a href="<c:url value='/items/show?id=${item.id}' />">詳細を見る</a></td>
                    </c:if>
                    </c:forEach>

                    <c:if test="${login_user == item.user.id}">
                                <tr class="row${status.count % 2}">
                                    <td class="item_category"><c:out value="${item.category}" /></td>
                        <td class="item_date"><fmt:formatDate value='${item.item_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="item_brand">${item.brand}</td>
                        <td class="item_action"><a href="<c:url value='/items/show?id=${item.id}' />">詳細を見る</a></td>
                                </tr>
                            </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${items_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((items_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/items/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/items/new' />">新規アイテムの登録</a></p>

    </c:param>
</c:import>