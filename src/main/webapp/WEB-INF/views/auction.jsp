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
<div>
    <!--TODO:display flight info maybe?-->
    Please enter your bid for ${airline} No.${flightNo}
    <form id="biddingform" action="/bid" method="POST">
        <input type="number" name="bid" placeholder="Your bid in $"/><br>
        <input type="hidden" name="bidderAccNo" value="${person.accNum}"/>
        <input type="hidden" name="airline" value="${airline}">
        <input type="hidden" name="flightNo" value="${flightNo}">
        <input type="hidden" name="hiddenFare" value="${hiddenFare}"/>
        <input type="submit" value="Bid" />
    </form>
</div>
<!-- end body -->

<%@include file="/WEB-INF/views/includes/footer.jsp" %>