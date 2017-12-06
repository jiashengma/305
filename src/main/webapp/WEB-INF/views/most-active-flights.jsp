<%@include file="/WEB-INF/views/includes/header.jsp" %>
<c:if test="${person.accessControl ne 'MANAGER'}">
    <c:redirect url="" />
</c:if>
<h2>Most active flights</h2>
<div class="mask">
    <c:choose>
    <c:when test="${empty results}">No flights are booked.</c:when>
    <c:otherwise>
    <table>
        <tr>
            <th>Airline ID</th>
            <th>Flight Number</th>
            <th>Number of Reservations</th>
        </tr>
        <c:forEach items="${results}" var="flight">
            <tr>
                <td>${flight.airline}</td>
                <td>${flight.flightNo}</td>
                <td>${flight.numRes}</td>
            </tr>
        </c:forEach>
    </table>
    </c:otherwise>
    </c:choose>
</div>
<%@include file="/WEB-INF/views/includes/footer.jsp" %>