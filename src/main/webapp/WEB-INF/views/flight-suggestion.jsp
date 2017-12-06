<%-- 
    Document   : flight-suggestion
    Created on : Dec 5, 2017, 11:14:23 PM
    Author     : majiasheng
--%>

<%@include file="/WEB-INF/views/includes/header.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!--prohibit unlogged in user to get to this page-->
<c:choose>
    <c:when test="${person.accessControl ne 'CUSTOMER_REPRESENTATIVE' and person.accessControl ne 'MANAGER'}">
        <c:redirect url=""/>
    </c:when>
</c:choose>
<h2>Flight Suggestion</h2>
<hr>
<!-- body -->
<div id="flightSuggestion">
    <c:choose>
        <c:when test="${fn:length(flightSuggestion) <= 0}">
            <h3>No Flight Suggestion / OR SHOW ALL FLIGHTS</h3>
        </c:when>
        <c:otherwise>
            <table>
        <tr>
            <th></th>
            <th>Airline</th>
            <th>FlightNo</th>

        </tr>
        <c:forEach var="fs" items="${flightSuggestion}" varStatus="loop">
            
            <tr>
                <td>&nbsp;${loop.index+1}&nbsp;</td>
                <td>${fs.airline}</td>
                <td>${fs.flightNo}</td>
                
            </tr>
        </c:forEach>
    </table>
        </c:otherwise>
    </c:choose>
    
    
</div>
<!-- end body -->

<%@include file="/WEB-INF/views/includes/footer.jsp" %>