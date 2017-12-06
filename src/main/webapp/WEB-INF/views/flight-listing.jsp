<%@include file="/WEB-INF/views/includes/header.jsp" %>
<c:if test="${person.accessControl ne 'MANAGER'}">
    <c:redirect url="" />
</c:if>
<h2>Flight Listing</h2>
<div class="mask">
    <table>
        <tr>
            <th>Airline</th>
            <th>FlightNo</th>
            <th>Number of Seats</th>
            <th>Depart Time</th>
            <th>Arrival Time</th>
            <th>Departing From</th>
            <th>Arriving At</th>
        </tr>
        <c:forEach items="${flights}" var="flight">
            <c:forEach items="${flight.legs}" var="leg">
            <tr>
                <td>${flight.airline}</td>
                <td>${flight.flightNo}</td>
                <td>${flight.numberOfSeats}</td>
                <td>${leg.depTime}</td>
                <td>${leg.arrTime}</td>
                <td>${leg.depAirport}</td>
                <td>${leg.arrAirport}</td>
            </tr>
            </c:forEach>
        </c:forEach>
    </table>
</div>
<%@include file="/WEB-INF/views/includes/footer.jsp" %>