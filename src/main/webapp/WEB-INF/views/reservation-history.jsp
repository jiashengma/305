<%@include file="/WEB-INF/views/includes/header.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!--prohibit unlogged in user to get to this page-->
<c:choose>
    <c:when test="${person.accessControl ne 'CUSTOMER'}">
        <c:redirect url=""/>
    </c:when>
</c:choose>
<h2>Reservation History</h2>
<hr>
<!-- body -->
<div id="reservationHistory">
    <c:choose>
        <c:when test="${fn:length(reservations) <= 0}">
            <h3>No Reservation History</h3>
        </c:when>
        <c:otherwise>
            <table>
                <tr>
                    <th>Airline</th>
                    <th>FlightNo</th>
                    <th>Class</th>
                    <th>Bid ($)</th>
                    <th>Date</th>
                    <th>Accepted?</th>
                </tr>
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