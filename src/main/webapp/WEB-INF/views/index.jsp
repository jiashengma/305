<%@include file="/WEB-INF/views/includes/header.jsp" %>

<!-- body -->
<p>${msg}</p>
<c:choose> 
    <c:when test="${person.accessControl ne 'CUSTOMER_REPRESENTATIVE' and person.accessControl ne 'MANAGER'}">
        <%@include file="/WEB-INF/views/includes/flight-search-form.jsp" %>
    </c:when>
    <c:otherwise>
        <h1>TODO: Add employee/manager info</h1>
    </c:otherwise>
</c:choose>
<!-- end body -->

<%@include file="/WEB-INF/views/includes/footer.jsp" %>