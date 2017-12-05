<%-- 
    Document   : auctionFail
    Created on : Dec 4, 2017, 11:54:24 AM
    Author     : majiasheng
--%>
<%@include file="/WEB-INF/views/includes/header.jsp" %>

<!--prohibit unlogged in user to get to this page-->
<c:choose>
    <c:when test="${empty person}">
        <c:redirect url=""/>
    </c:when>
</c:choose>
<h3>${msg}</h3>

<%@include file="/WEB-INF/views/includes/footer.jsp" %>