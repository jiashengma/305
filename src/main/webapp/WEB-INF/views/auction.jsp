<%-- 
    Document   : auction
    Created on : Nov 12, 2017, 3:58:12 PM
    Author     : majiasheng
--%>

<%@include file="/WEB-INF/views/includes/header.jsp" %>

<!--prohibit unlogged in user to get to this page-->
<c:choose>
    <c:when test="${empty person}">
        <c:redirect url=""/>
    </c:when>
</c:choose>
<h2>Auction</h2>
<hr>
<!-- body -->
<div class="mask">
    <!--TODO:display flight info maybe?-->
    <c:choose> 
        <c:when test="${empty flightSearchResult.get(indexOfFlight)}"> <p class="error">uh oh, flight got lost</p> </c:when>
        
        <c:otherwise>
            Please enter your bid for ${flightSearchResult.get(indexOfFlight).airline} No.${flightSearchResult.get(indexOfFlight).flightNo}
            
            <form id="biddingform" action="/bid" method="POST">
                <input type="number" name="NYOP" placeholder="Your bid in $" required/><br>
                <!--<input type="hidden" name="indexOfFlight" value="${indexOfFlight}"/><br>-->
                <input type="hidden" name="personAccNo" value="${person.accNum}"/>
                <input type="hidden" name="airline" value="${flightSearchResult.get(indexOfFlight).airline}"/>
                <input type="hidden" name="flightNo" value="${flightSearchResult.get(indexOfFlight).flightNo}"/>
                <input type="hidden" name="flightClass" value="${flightSearchResult.get(indexOfFlight).flightClass}"/>
                <input type="hidden" name="hiddenFare" value="${flightSearchResult.get(indexOfFlight).hiddenFare}"/>
                <input type="submit" value="Bid" />
            </form>
        </c:otherwise>
    </c:choose>
</div>
<!-- end body -->

<%@include file="/WEB-INF/views/includes/footer.jsp" %>