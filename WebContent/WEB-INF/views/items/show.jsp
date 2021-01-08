<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${item != null}">
            <c:if test="${flush != null}">
                <div id="flush_success">
                    <c:out value="${flush}"></c:out>
                </div>
            </c:if>
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

                <c:choose>
                <c:when test="${sessionScope.login_user.id == item.user.id}">
                    <p>いいね全<c:out value="${goods}" />件</p>
                    <p><a href="<c:url value="/items/edit?id=${item.id}" />">アイテム情報を編集する</a></p>
                 <form method="POST" action="<c:url value='/items/destroy?id=${item.id}"' />">
                     <input type="hidden" name="_token" value="${_token}" />
                     <button type="submit">アイテム情報を削除する</button>
                 </form>
                </c:when>
                <c:otherwise>
                    <form method="POST" action="<c:url value='/items/good?id=${item.id}' />">
                        <button type="submit" name="good">いいね</button>
                    </form>
                    <p><a href="<c:url value="/items/edit?id=${item.id}" />">アイテム情報を編集する</a></p>
                    <form method="POST" action="<c:url value='/items/destroy?id=${item.id}"' />">
                     <input type="hidden" name="_token" value="${_token}" />
                     <button type="submit">アイテム情報を削除する</button>
                 </form>
                </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>
        <p><a href="<c:url value="/items/index" />">一覧に戻る</a></p>
    </c:param>
</c:import>

