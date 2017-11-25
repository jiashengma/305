<%-- 
    Document   : auction-history
    Created on : Nov 23, 2017, 3:12:37 PM
    Author     : majiasheng
--%>

<%@include file="/WEB-INF/views/includes/header.jsp" %>

<!--prohibit unlogged in user to get to this page-->
<c:choose>
    <c:when test="${empty person}">
        <c:redirect url=""/>
    </c:when>
</c:choose>
<h2>Auction History</h2>
<hr>
<!-- body -->
<div id="auctionHistory">
    <table>
        <tr>
            <th>Airline</th>
            <th>FlightNo</th>
            <th>Class</th>
            <th>Bid ($)</th>
            <th>Date</th>
            <th>Accepted?</th>
        </tr>
        <c:forEach var="auction" items="${auctions}">
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
        </c:forEach>
    </table>
</div>
<!-- end body -->

<%@include file="/WEB-INF/views/includes/footer.jsp" %>