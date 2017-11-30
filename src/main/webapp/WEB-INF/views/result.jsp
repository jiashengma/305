<%@include file="/WEB-INF/views/includes/header.jsp" %>
<div class="mask">
<c:choose>
    <c:when test="${empty flightSearchResult}"> 
        <!-- TODO: display message to indicate no such result-->
        <p>No search results</p>
    </c:when>

    <c:otherwise>
        <!-- TODO: use/display result: 
            allow user to book, and participate in auction -->
        <table class="searchResultTable">
            <tr>
                <th>Airline</th>
                <th>Flight</th>
                <th>Class</th>
                <th>Fare</th>
            </tr>
            <c:forEach var="flight" items="${flightSearchResult}">
                <tr>
                    <td>${flight.airline}</td>
                    <td>${flight.flightNo}</td>
                    <td>${flight.flightClass}</td>
                    <td>${flight.fare}</td>

                    <form class="reservationFormGroup" name="bookingForm" action="/bookflight" method="POST">
                        <!--TODO: 
                            on the left: 
                                flight info: flight, airline, dep/arr time, stops etc
                            on the right:
                                - button/form for booking 
                                - button/form for auction
                                (the forms should contain hidden input type with names and 
                                 value pairs, see the auctionForm below)
                        -->
                        <input type="hidden" name="personId" value="${person.id}"/>
                        <td class="tdSubmit"><input type="submit" value="Book This Flight" /></td>
                    </form>


                    <form class="reservationFormGroup" name="auctionForm" action="/auction" method="POST">

                        <input type="hidden" name="airline" value="${flight.airline}">
                        <input type="hidden" name="flightNo" value="${flight.flightNo}">
                        <input type="hidden" name="flightNo" value="${flight.flightClass}">
                        <input type="hidden" name="hiddenFare" value="${flight.hiddenFare}">
                        <input type="hidden" name="personId" value="${person.id}">
                        <td class="tdSubmit"><input type="submit" value="Bid For This Flight" /></td>
                    </form>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
</div>
<%@include file="/WEB-INF/views/includes/footer.jsp" %>