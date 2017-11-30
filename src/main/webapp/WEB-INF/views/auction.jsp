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
        <c:when test="${empty flight}"> <p class="error">Error, flight got los</p> </c:when>
        <c:otherwise>
            Please enter your bid for ${flight.airline} No.${flight.flightNo}
            <form id="biddingform" action="/bid" method="POST">
                <input type="number" name="NYOP" placeholder="Your bid in $" required=""/><br>
                <input type="hidden" name="personAccNo" value="${person.accNum}"/>
                <input type="hidden" name="airline" value="${flight.airline}"/>
                <input type="hidden" name="flightNo" value="${flight.flightNo}"/>
                <input type="hidden" name="flightClass" value="${flight.flightClass}"/>
                <input type="hidden" name="hiddenFare" value="${flight.hiddenFare}"/>
                <input type="submit" value="Bid" />
            </form>
        </c:otherwise>
    </c:choose>
</div>
<!-- end body -->

<%@include file="/WEB-INF/views/includes/footer.jsp" %>