<%@include file="/WEB-INF/views/includes/header.jsp" %>
<c:if test="${person.accessControl ne 'CUSTOMER'}">
    <c:redirect url="" />
</c:if>
<h2>Delete Reservation</h2>
<div class="mask">
    <c:choose>
        <c:when test="${not empty success}">
            Reservation #${param.resrNo} successfully deleted.
        </c:when>
        <c:otherwise>
            Failed to delete reservation.
        </c:otherwise>
    </c:choose>
</div>
<%@include file="/WEB-INF/views/includes/footer.jsp" %>