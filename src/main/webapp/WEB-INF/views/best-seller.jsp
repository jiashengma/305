<%-- 
    Document   : best-seller
    Created on : Dec 5, 2017, 11:21:19 PM
    Author     : majiasheng
--%>

<%@include file="/WEB-INF/views/includes/header.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!--prohibit unlogged in user to get to this page-->
<%--<c:choose>
    <c:when test="${empty person}">
        <c:redirect url=""/>
    </c:when>
</c:choose>--%>
<h2>Best Sellers</h2>
<hr>
<!-- body -->
<div id="flightSuggestion">
    <c:choose>
        <c:when test="${fn:length(bestSeller) <= 0}">
            <h3>Grant Opening...Help Us Pick Your Best Seller :)</h3>
        </c:when>
        <c:otherwise>
            <table>
        <tr>
            <th></th>
            <th>Airline</th>
            <th>FlightNo</th>

        </tr>
        <c:forEach var="bs" items="${bestSeller}" varStatus="loop">
            
            <tr>
                <td>&nbsp;${loop.index+1}&nbsp;</td>
                <td>${bs.airline}</td>
                <td>${bs.flightNo}</td>
                
            </tr>
        </c:forEach>
    </table>
        </c:otherwise>
    </c:choose>
    
    
</div>
<!-- end body -->

<%@include file="/WEB-INF/views/includes/footer.jsp" %>