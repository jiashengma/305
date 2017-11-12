<%-- 
    Document   : flight-search-result.jsp
    Created on : Nov 9, 2017, 10:12:26 PM
    Author     : majiasheng
--%>

<%@include file="/WEB-INF/views/includes/header.jsp" %>

<!-- body -->

<!-- end body -->
<c:choose>
    <c:when test="${empty flights}"> 
        <!-- TODO: display message to indicate no such result-->
        <p>No search results</p>
    </c:when>

    <c:otherwise>
        <!-- TODO: use/display result: 
            allow user to book, and participate in auction -->
    </c:otherwise>
</c:choose>

<%@include file="/WEB-INF/views/includes/footer.jsp" %>