<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${item != null}">
                <h2>アイテム　詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>カテゴリ</th>
                            <td><c:out value="${item.category}" /></td>
                        </tr>
                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${item.item_date}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                        <tr>
                            <th>ユーザー名</th>
                            <td><c:out value="${item.user.user_name}" /></td>
                        </tr>
                        <tr>
                            <th>価格</th>
                            <td><c:out value="${item.price}" /></td>
                        </tr>
                        <tr>
                            <th>内容</th>
                            <td>
                                <pre><c:out value="${item.content}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>写真</th>
                            <td>
                                <img src="${item.path}" height=100px width=100px>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_user.id == item.user.id}">
                    <p><a href="<c:url value="/items/edit?id=${item.id}" />">このページを編集する</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/items/index" />">一覧に戻る</a></p>
    </c:param>
</c:import>