<%@include file="/WEB-INF/views/includes/header.jsp" %>
<c:if test="${person.accessControl ne 'MANAGER'}">
    <c:redirect url="" />
</c:if>
<h2>Search for customers by flight</h2>
<%@include file="/WEB-INF/views/includes/error.jsp" %>
<div class="mask">
    <form method="GET" action="/customer-flight-search">
        <div>
            Airline ID: <input type="text" name="airline" required pattern="[A-Za-z]{2}"/> 
            Flight Number: <input type="number" name="flightNo" class="number-textfield" required pattern="[0-9]+" />
        </div>
        <input type="submit" value="Search" />
    </form>
</div>
<c:choose>
    <c:when test="${empty param.airline and empty param.flightNo}">
        <div class="mask">
            Enter a 2-character Airline ID and flight number above.
        </div>
    </c:when>
    <c:when test="${not empty results}">
        <h3>Results</h3>
        <div class="mask">
            <table>
                <tr>
                    <th>Customer Name</th>
                    <th>Account Number</th>
                    <th>E-mail Address</th>
                </tr>
                <c:forEach items="${results}" var="customer">
                    <tr>
                        <td>${customer.firstName} ${customer.lastName}</td>
                        <td>${customer.accNum}</td>
                        <td>${customer.email}</td>
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