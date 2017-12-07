<%@include file="/WEB-INF/views/includes/header.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--prohibit unlogged in user to get to this page-->
<c:if test="${person.accessControl ne 'CUSTOMER'}">
        <c:redirect url=""/>
</c:if>
<h2>Reservation History</h2>
<hr>
<!-- body -->
<div id="reservationHistory" class="mask">
    <c:choose>
        <c:when test="${empty reservations}">
            <h3>No Reservation History</h3>
        </c:when>
        <c:otherwise>
            <table>
                <tr>
                    <th>Airline</th>
                    <th>Flight Number</th>
                    <th>Class</th>
                    <th>Seat Number</th>
                    <th>Total Fare</th>
                    <th>Date</th>
                </tr>
                <c:forEach items="${reservations}" var="reservation">
                    <tr>
                        <td>${reservation.flight.airline}</td>
                        <td>${reservation.flight.flightNo}</td>
                        <td>${reservation.flight.flightClass}</td>
                        <td>${reservation.flight.seatNum}</td>
                        <td><fmt:formatNumber type="currency" currencySymbol="$">${reservation.totalFare}</fmt:formatNumber></td>
                        <td>${reservation.reservationTime}</td>
                        <td><a class="button" href="/delete-reservation?resrNo=${reservation.reservationNo}">Delete</a></td>
                    </tr>
                </c:forEach>
                <%--<c:forEach var="auction" items="${auctions}">
                    <tr>
                        <td>${auction.airline}</td>
                        <td>${auction.flightNo}</td>
                        <td>${auction.flightClass}</td>
                        <td>${auction.NYOP}</td>
                        <td>${auction.date}</td>
                        <td>
                            <c:choose>
                                <c:when test="${auction.accepted}">
                                    Yes
                                </c:when>
                                <c:otherwise>
                                    No
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>--%>
            </table>
        </c:otherwise>
    </c:choose>


</div>
<!-- end body -->

<%@include file="/WEB-INF/views/includes/footer.jsp" %>