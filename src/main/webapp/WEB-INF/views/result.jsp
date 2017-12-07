<%@include file="/WEB-INF/views/includes/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${person.accessControl eq 'CUSTOMER_REPRESENTATIVE' or person.accessControl eq 'MANAGER'}">
    <c:redirect url=""/>
</c:if>

<div class="mask">
    <c:choose>
        <c:when test="${empty flightSearchResult}">     <!-- No such result-->
            <p>No search results</p>
        </c:when>

        <c:otherwise>   <!-- Display result: allow user to book/participate in auction -->
            <table class="searchResultTable">
                <tr>
                    <th>Airline</th>
                    <th>Flight</th>
                    <th>Class</th>
                    <th>Fare</th>
                </tr>
                <c:forEach var="flight" items="${flightSearchResult}" varStatus="loop">
                    <tr>
                        <td>${flight.airline}</td>
                        <td>${flight.flightNo}</td>
                        <td>${flight.flightClass}</td>
                        <td><fmt:formatNumber type="currency" currencySymbol="$">${flight.fare}</fmt:formatNumber></td>

                    <form class="reservationFormGroup" name="bookingForm" action="/selectRep" method="POST">
                        <!--TODO:
                                on the left:
                                        flight info: flight, airline, dep/arr time, stops etc
                                on the right:
                                        - button/form for booking
                                        - button/form for auction
                                        (the forms should contain hidden input type with names and
                                         value pairs, see the auctionForm below)
                        -->
                        <!--TODO need leg info-->
                        <input type="hidden" name="indexOfFlight" value="${loop.index}"/>
                        <input type="hidden" name="personId" value="${person.accNum}">
<!--                        <input type="hidden" name="airline" value="${flight.airline}">
                        <input type="hidden" name="flightNo" value="${flight.flightNo}">
                        <input type="hidden" name="flightClass" value="${flight.flightClass}">
                        <input type="hidden" name="fare" value="${flight.fare}">
                        -->
                        <td class="tdSubmit"><input type="submit" value="Book This Flight" /></td>
                    </form>

                    <form class="reservationFormGroup" name="auctionForm" action="/auction" method="POST">
                        <!--TODO need leg info-->
                        <input type="hidden" name="indexOfFlight" value="${loop.index}"/>
                        <input type="hidden" name="personId" value="${person.accNum}">
<!--                        <input type="hidden" name="airline" value="${flight.airline}">
                        <input type="hidden" name="flightNo" value="${flight.flightNo}">
                        <input type="hidden" name="flightClass" value="${flight.flightClass}">
                        <input type="hidden" name="hiddenFare" value="${flight.hiddenFare}">
                        -->
                        <td class="tdSubmit"><input type="submit" value="Bid For This Flight" /></td>
                    </form>
                    </tr>
                </c:forEach>
            </table>

        </c:otherwise>
    </c:choose>
</div>
<%@include file="/WEB-INF/views/includes/footer.jsp" %>