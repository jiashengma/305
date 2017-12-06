<%@include file="/WEB-INF/views/includes/header.jsp" %>
<c:if test="${person.accessControl ne 'MANAGER'}">
    <c:redirect url="" />
</c:if>
<h2>Search for flights by airport</h2>
<c:if test="${not empty error}">
    <div class="mask error">
        ${error}
    </div>
</c:if>
<form method="GET" action="/airport-search">
    Airport ID: <input type="text" name="airportId" required pattern="[A-Za-z]{3}"/><br/>
    <input type="submit" value="Search" />
</form>
<c:choose>
    <c:when test="${empty param.airportId}">
        <div class="mask">
            Enter a 3-character Airport ID above!
        </div>
    </c:when>
    <c:when test="${not empty results}">
        <h3>Results</h3>
        <div class="mask">
            <table>
                <tr>
                    <th>Airline ID</th>
                    <th>Flight Number</th>
                </tr>
                <c:forEach items="${results}" var="flight">
                    <tr>
                        <td>${flight.airline}</td>
                        <td>${flight.flightNo}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:when>
    <c:otherwise>
        <div class="mask">
            No results found.
        </div>
    </c:otherwise>
</c:choose>

<%@include file="/WEB-INF/views/includes/footer.jsp" %>
