<%@include file="/WEB-INF/views/includes/header.jsp" %>
<c:if test="${empty person}">
    <c:redirect url="/"/>
</c:if>
<h2>All Customer Representatives</h2>
<hr>
<div id="cusRep">
    <table>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Start Date</th>
            <th>Hourly Rate</th>
            <th>Phone Number</th>
            <th>Username</th>
            <th>Street</th>
            <th>City</th>
            <th>State</th>
            <th>Zip Code</th>
        </tr>
        <c:forEach var="cr" items="${customerRepresentatives}">
            <tr>
                <td>${cr.firstName}</td>
                <td>${cr.lastName}</td>
                <td>${cr.startDate}</td>
                <td>${cr.hourlyRate}</td>
                <td>${cr.phone}</td>
                <td>${cr.userName}</td>
                <td>${cr.address.street}</td>
                <td>${cr.address.city}</td>
                <td>${cr.address.state}</td>
                <td>${cr.address.zipCode}</td>
                <!--TODO:add edit and delete buttons-->
            </tr>
        </c:forEach>
    </table>
</div>

<%@include file="/WEB-INF/views/includes/footer.jsp" %>