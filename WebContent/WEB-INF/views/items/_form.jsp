<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="item_date">日付</label><br />
<input type="date" name="item_date" value="<fmt:formatDate value='${item.item_date}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="category">カテゴリ</label><br />
<input type="radio" name="category" value="トップス">トップス
<input type="radio" name="category" value="パンツ">パンツ
<input type="radio" name="category" value="その他">その他
<br /><br />

<label for="brand">ブランド</label><br />
<input type="text" name="brand" value="${item.brand}" />
<br /><br />

<label for="color">色</label><br />
<input type="text" name="color" >
<br /><br />



<label for="price">価格</label><br />
<input type="number" name="price" />
<br /><br />


<label for="content">内容</label><br />
<textarea name="content" rows="10" cols="50">${item.content}</textarea>
<br /><br />

<label for="image">写真</label><br />
<input type="file" name="image" size="30" accept="image/*" />
<br /><br />



<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>