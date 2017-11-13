<%-- 
    Document   : flight-search-result.jsp
    Created on : Nov 9, 2017, 10:12:26 PM
    Author     : majiasheng
--%>

<%@include file="/WEB-INF/views/includes/header.jsp" %>

<!-- body -->

<!-- end body -->
<c:choose>
    <c:when test="${empty flightSearchResult}"> 
        <!-- TODO: display message to indicate no such result-->
        <p>No search results</p>
    </c:when>

    <c:otherwise>
        <!-- TODO: use/display result: 
            allow user to book, and participate in auction -->
        <c:forEach var="flight" items="${flightSearchResult}">
            <!--TODO: 
                on the left: 
                    flight info: flight, airline, dep/arr time, stops etc
                on the right:
                    - button/form for booking 
                    - button/form for auction
                    (the forms should contain hidden input type with names and 
                     value pairs, see the auctionForm below)
            
            NOTE: check if users has logged in before allowing them to book/bid
                    use js to check, onClick: 
                        check if session attribute, person, is empty,
                            if so, prompt user to log in, with a pop up window
                            otherwise, just take user to the appropriate page
            -->
            
            
            <form name="auctionForm" action="/auction">
                <input type="hidden" name="airline" value="${flight.airline}">
                <input type="hidden" name="flightNo" value="${flight.flightNo}">
                <input type="hidden" name="hiddenFare" value="${flight.hiddenFare}">

                <!--TODO: check if users has logged in before allowing them to bid-->
                
                <input type="submit" value="Bid For This Flight" />
                
            </form>
        </c:forEach>
    </c:otherwise>
</c:choose>

<%@include file="/WEB-INF/views/includes/footer.jsp" %>