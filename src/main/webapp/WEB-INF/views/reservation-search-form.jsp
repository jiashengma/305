<%@include file="/WEB-INF/views/includes/header.jsp" %>
<%-- <c:if test="${person.accessControl ne 'MANAGER'}">
    <c:redirect url="" />
</c:if> --%>
<script src="/resources/js/jquery.min.js"></script>
<script>
    document.body.onload = function(){
        document.getElementById("flightNoRadio").checked="checked";
        function toggleFields(){
            if (this.value === "flightNo"){
                document.getElementById("flightFields").hidden = false;
                document.getElementById("customerNameFields").hidden = true;
                document.getElementById("customerName").removeAttribute("required");
            }
            else {
                document.getElementById("flightFields").hidden = true;
                document.getElementById("customerNameFields").hidden = false;
                document.getElementById("flightNo").removeAttribute("required");
                document.getElementById("airlineId").removeAttribute("required");
            }
        }
        document.getElementsByName("mode").forEach((x) => x.onchange = toggleFields);
    }
</script>    
<h2>Search Reservations</h2>
<c:if test="${not empty error}">
    <div class="mask error">
        ${error}
    </div>
</c:if>
<div class="mask">
    <form method="POST" action="/reservation-search">
        <strong>Search by:</strong>
        <input type="radio" name="mode" id="flightNoRadio" value="flightNo" />Airline Id & Flight Number 
        <input type="radio" name="mode" value="customerName" />Customer Id<br/>
        <div id="flightFields">
            Airline Id: <input type="text" name="airlineId" id="airlineId" required />
            Flight No: <input type="number" name="flightNo" id="flightNo" required pattern="[0-9]+"/><br/>
        </div>
        <div id="customerNameFields" hidden="true">
            Customer Name: <input type="text" name="customerName" id="customerName"/><br/>
        </div>
        <input type="submit" value="Search"/>
    </form>
</div>
<c:if test="${not empty results}">
    <h3>Results</h3>
    <div class="mask">
        <table>
            <tr>
                <th>Reservation Number</th>
                <th>Date Created</th>
                <th>Total Fare</th>
            </tr>
            <c:forEach items="${results}" var="reservation">
                <tr>
                    <td>${reservation.reservationNo}</td>
                    <td>${reservation.date}</td>
                    <td>${reservation.totalFare}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
<%@include file="/WEB-INF/views/includes/footer.jsp" %>