<%@include file="/WEB-INF/views/includes/header.jsp" %>
<c:choose>
    <c:when test="${empty flightSearchResult}"> 
        <!-- TODO: display message to indicate no such result-->
        <p>No search results</p>
    </c:when>

    <c:otherwise>
        <!-- TODO: use/display result: 
            allow user to book, and participate in auction -->
        <c:forEach var="flight" items="${flightSearchResult}">
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
                <input type="hidden" name="personId" value="${person.id}"
                <input type="submit" value="Book This Flight" />
            </form>
            
            
            <form class="reservationFormGroup" name="auctionForm" action="/prepareAuction" method="POST">
                <input type="hidden" name="airline" value="${flight.airline}">
                <input type="hidden" name="flightNo" value="${flight.flightNo}">
                <input type="hidden" name="hiddenFare" value="${flight.hiddenFare}">
                <input type="hidden" name="personId" value="${person.id}"
                <!--TODO: check if users has logged in before allowing them to bid-->
                <input type="submit" value="Bid For This Flight" />
            </form>
        </c:forEach>
    </c:otherwise>
</c:choose>

<%@include file="/WEB-INF/views/includes/footer.jsp" %>