<%@include file="/WEB-INF/views/includes/header.jsp" %>
<c:if test="${person.accessControl ne 'MANAGER'}">
    <c:redirect url="" />
</c:if>
<h2>Sales Report</h2>
<div class="mask">
    <c:forEach items="${errors}" var="error">
        <div class="error">${error}</div>
    </c:forEach>
    <form method="GET" action="/sales">
        Month: 
        <select name="month" required>
            <option value="1">January</option>
            <option value="2">February</option>
            <option value="3">March</option>
            <option value="4">April</option>
            <option value="5">May</option>
            <option value="6">June</option>
            <option value="7">July</option>
            <option value="8">August</option>
            <option value="9">September</option>
            <option value="10">October</option>
            <option value="11">November</option>
            <option value="12">December</option>
        </select>
        Year:<input type="number" style="-moz-appearance: textfield" name="year" required pattern="[0-9]{4}" /><br/>
        <input type="submit"  value="Get Sales Report"/>
    </form>
</div>
<%@include file="/WEB-INF/views/includes/footer.jsp" %>