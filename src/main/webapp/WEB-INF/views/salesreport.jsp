<%@include file="/WEB-INF/views/includes/header.jsp" %>
<c:if test="${person.accessControl ne 'MANAGER'}">
    <c:redirect url="" />
</c:if>
<h2>Sales Report</h2>
<div class="mask">
    <c:choose>
        <c:when test="${empty sales}">    
            No sales found for that month!
        </c:when>
        <c:otherwise>
            <table>
                <tr>
                    <th>Reservation Number</th>
                    <th>Total Fare</th>
                </tr>
                <c:set var="totalSales" value="${0}"/>
                <c:forEach items="${sales}" var="reservation">
                    <tr>
                        <td>${reservation.reservationNo}</td>
                        <td>&#36;${reservation.totalFare}</td>
                    </tr>
                    <c:set var="totalSales" value="${totalSales + reservation.totalFare}"/>
                </c:forEach>
                    <tr>
                        <td>Total</td>
                        <td>&#36;${totalSales}</td>
                    </tr>
            </table>
        </c:otherwise>

    </c:choose>

</div>
<%@include file="/WEB-INF/views/includes/footer.jsp" %>