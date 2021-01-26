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
            <c:if test="${errors != null}">
                <div id="flush_error">
                    入力内容にエラーがあります。<br />
                        <c:forEach var="error" items="${errors}">
            ・               <c:out value="${error}" /><br />
                        </c:forEach>

                </div>
            </c:if>
                <h2>アイテム　詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>カテゴリ</th>
                            <td><c:out value="${item.category}" />
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

                <h1>コメント一覧</h1>

                <c:choose>
                    <c:when test="${sessionScope.login_user.id == item.user.id}">

                         <c:forEach var="comments" items="${comment}">
                            <p>ID:<c:out value="${comments.id}"/>　名前:<c:out value="${comments.user.user_name}"/>　日付:<c:out value="${comments.time}"/><br>
                            <c:out value="${comments.comment}"/></p>
                         </c:forEach>

                         <form method="POST" action="<c:url value="/question?id=${item.id}" />">
                            <p>コメント:<br>
                            <textarea name="comment" rows="5" cols="40"></textarea>
                            </p>
                            <p><input type="submit" value="送信"></p>
                         </form>

                         <p>いいね <c:out value="${goods}" />件</p>
                         <p><a href="<c:url value="/items/edit?id=${item.id}" />">アイテム情報を編集する</a></p>
                         <form method="POST" action="<c:url value='/items/destroy?id=${item.id}"' />">
                             <input type="hidden" name="_token" value="${_token}" />
                             <button type="submit">アイテム情報を削除する</button>
                         </form>
                    </c:when>
                <c:otherwise>

                    <c:forEach var="comments" items="${comment}">
                    <c:choose>
                    <c:when test="${comments.item.id == item.id}" >
                            <p>ID:<c:out value="${comments.id}"/>　名前:<c:out value="${comments.user.user_name}"/>　日付:<c:out value="${comments.time}"/><br>
                                <c:out value="${comments.comment}"/></p>
                    </c:when>
                    </c:choose>
                     </c:forEach>

                    <form method="POST" action="<c:url value="/question?id=${item.id}" />">
                        <p>コメント:<br>
                        <textarea name="comment" rows="5" cols="40" placeholder="${item.user.user_name}さんへ"></textarea>
                        </p>
                        <p><input type="submit" value="送信"></p>
                    </form>

                    <form method="POST" action="<c:url value='/items/good?id=${item.id}' />">
                        <button type="submit" name="good">いいね</button>
                    </form>

                    <c:if test="${sessionScope.login_user.id == item.user.id}">
                        <p><a href="<c:url value="/items/edit?id=${item.id}" />">アイテム情報を編集する</a></p>
                            <form method="POST" action="<c:url value='/items/destroy?id=${item.id}"' />">
                                <input type="hidden" name="_token" value="${_token}" />
                                <button type="submit">アイテム情報を削除する</button>
                            </form>
                    </c:if>
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

