<%@include file="/WEB-INF/views/includes/header.jsp" %>

<!-- body -->
<p>${msg}</p>
<c:choose> 
    <%--<c:when test="${person.accessControl ne 'CUSTOMER_REPRESENTATIVE' and person.accessControl ne 'MANAGER'}">--%>
        <%--<%@include file="/WEB-INF/views/includes/flight-search-form.jsp" %>--%>
    <%--</c:when>--%>
    
    <c:when test="${person.accessControl eq 'CUSTOMER_REPRESENTATIVE'}">
        <%@include file="/WEB-INF/views/includes/employee-panel.jsp" %>
    </c:when>
    <c:when test="${person.accessControl eq 'MANAGER'}">
        <%@include file="/WEB-INF/views/includes/admin-panel.jsp" %>
    </c:when>
    
    <c:otherwise>
        <%--<%@include file="/WEB-INF/views/includes/admin.jsp" %>--%>
        <%@include file="/WEB-INF/views/includes/flight-search-form.jsp" %>
    </c:otherwise>
</c:choose>
<!-- end body -->

<%@include file="/WEB-INF/views/includes/footer.jsp" %>