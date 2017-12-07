<%-- 
    Document   : flight-suggestion
    Created on : Dec 5, 2017, 11:14:23 PM
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
<h2>Flight Suggestion</h2>
<hr>
<!-- body -->
<!--NOTE: "flightSuggestions" is a hash map <Integer: customerAccNo, List<Flight>>
    "customers" is a hash map of customers  <Integer: customerAccNo, Customer>
-->

<div id="flightSuggestion">
    <c:choose>

        <c:when test="${not empty requester}">
            <c:choose>
                <c:when test="${fn:length(flightSuggestions) <= 0}">
                    <h3>Get some customers :) and make them happy with your suggestions</h3>
                </c:when>
                <c:otherwise>

                    <c:forEach var="fs" items="${flightSuggestions}" varStatus="loop">
                        <table>
                            <tr>
                                <th></th>
                                <th>Customer</th>
                                <th>Airline</th>
                                <th>FlightNo</th>
                            </tr>
                            <!--for each customer-->
                            <c:forEach var="c" items="${customers}" varStatus="innerloop">
                                <tr>
                                    <td>&nbsp;${innerloop.index+1}&nbsp;</td>
                                    <td>${c.firstName}&nbsp;${c.lastName}</td>
                                    <td>${fs[c.accNum].airline}</td>
                                    <td>${fs[c.accNum].flightNo}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <hr>
                    </c:forEach>

                </c:otherwise>
            </c:choose>

        </c:when>

        <c:otherwise>
            <c:choose>
                <c:when test="${fn:length(flightSuggestions) <= 0}">
                    <h3>No Flight Suggestion / OR SHOW ALL FLIGHTS</h3>
                </c:when>
                <c:otherwise>
                    <table>
                        <tr>
                            <th></th>
                            <th>Airline</th>
                            <th>FlightNo</th>
                        </tr>
                        <c:forEach var="fs" items="${flightSuggestions}" varStatus="loop">

                            <tr>
                                <td>&nbsp;${loop.index+1}&nbsp;</td>
                                <td>${fs.airline}</td>
                                <td>${fs.flightNo}</td>

                            </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>


</div>
<!-- end body -->

<%@include file="/WEB-INF/views/includes/footer.jsp" %>