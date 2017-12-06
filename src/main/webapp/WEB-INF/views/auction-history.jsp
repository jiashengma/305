<%-- 
    Document   : auction-history
    Created on : Nov 23, 2017, 3:12:37 PM
    Author     : majiasheng
--%>

<%@include file="/WEB-INF/views/includes/header.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <c:choose>
        <c:when test="${fn:length(auctions) <= 0}">
            <h3>No Auction History</h3>
        </c:when>
        <c:otherwise>
            <table>
        <tr>
            <th></th>
            <th>Airline</th>
            <th>FlightNo</th>
            <th>Class</th>
            <th>Bid ($)</th>
            <th>Date</th>
            <th>Accepted?</th>
        </tr>
        <c:forEach var="auction" items="${auctions}" varStatus="loop">
            
            <tr>
                <td>&nbsp;${loop.index+1}&nbsp;</td>
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
        </c:otherwise>
    </c:choose>
    
    
</div>
<!-- end body -->

<%@include file="/WEB-INF/views/includes/footer.jsp" %>