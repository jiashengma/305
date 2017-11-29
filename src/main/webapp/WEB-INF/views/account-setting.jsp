<%-- 
    Document   : account-setting
    Created on : Nov 28, 2017, 10:26:52 PM
    Author     : majiasheng
--%>

<%@include file="/WEB-INF/views/include/header.jsp" %>

<!--send home if user is empty-->
<c:if test="${empty user}">
    <c:redirect url="/"/>
</c:if>
<h2>User Setting</h2>
<hr>
${msg}
<form name="userInfoForm" action="/confirmEdit" method="POST">
    <input type="hidden" name="username" value="${user.username}">
    First Name: <input type="text" name="firstname" value="${person.firstName}"><br>
    Last Name: <input type="text"  name="lastname" value="${person.lastName}"><br>
    <input type="submit" value="Eidt">
</form>

<%@include file="/WEB-INF/views/include/footer.jsp" %>