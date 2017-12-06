<%@include file="/WEB-INF/views/includes/header.jsp" %>
<c:if test="${person.accessControl ne 'MANAGER'}">
    <c:redirect url="" />
</c:if>

<h2>Delays</h2>
<div class="mask">
    <c:choose>
        <c:when test="${empty delays}">
            There are no delays!
        </c:when>
        <c:otherwise>
            <p>The following flights are delayed:</p>
            <table>
                <tr>
                    <th>Airline</th>
                    <th>Flight Number</th>
                </tr>
                <c:forEach items="${delays}" var="flight">
                    <tr>
                        <td>${flight.airline}</td>
                        <td>${flight.flightNo}</td>
                </c:forEach>
                    
            </table>
        </c:otherwise>
    </c:choose>
</div>
<h2>On-time</h2>
<div class="mask">
    <c:choose>
        <c:when test="${empty ontime}">
            None of the flights are on time!
        </c:when>
        <c:otherwise>
            <p>The following flights are on time:</p>
            <table>
                <tr>
                    <th>Airline</th>
                    <th>Flight Number</th>
                </tr>
                <c:forEach items="${ontime}" var="flight">
                    <tr>
                        <td>${flight.airline}</td>
                        <td>${flight.flightNo}</td>
                </c:forEach>
                    
            </table>
        </c:otherwise>
    </c:choose>
</div>

<%@include file="/WEB-INF/views/includes/footer.jsp" %>

