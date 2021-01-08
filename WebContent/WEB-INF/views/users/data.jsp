<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
         </c:if>

         <h2>ユーザー　一覧</h2>
         <table id="user_list">
            <tbody>
                <tr>
                    <th>ユーザー名</th>
                    <th>身長</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="user" items="${users}">
                    <tr class="row${status.count % 2}">
                        <td>
                           <c:out value="${user.user_name}" />
                        </td>
                        <td>
                           <c:out value="${user.height}" />
                        </td>
                        <td>
                          <c:if test="${user.id != user_id}">
                             <c:forEach var="feses" items="${fes}">
                                <c:if test="${feses.followered.id == user.id}">
                                    <c:out value="フォロー済み"></c:out>
                                </c:if>
                             </c:forEach>
                                 <c:if test="${feses.followered_id != user.id}">
                                    <a href="<c:url value='/follow?id=${user.id}' />" class="btn btn--orange btn--radius">フォローする</a>
                                 </c:if>
                          </c:if>
                       </td>
                    </tr>
                </c:forEach>
            </tbody>
          </table>

          <div id="pagination">
            （全 ${users_count} 人) <br />
            <c:forEach var="i" begin="1" end="${((users_count - 1) / 15) + 1}" step="1">
            <c:choose>
                <c:when test="${i == page}">
                    <c:out value="${i}" />&nbsp;
                </c:when>
                <c:otherwise>
                    <a href="<c:url value='/users/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                </c:otherwise>
            </c:choose>
         </c:forEach>
         </div>
          </c:param>
       </c:import>