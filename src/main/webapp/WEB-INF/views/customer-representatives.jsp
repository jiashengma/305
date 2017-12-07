<%@include file="/WEB-INF/views/includes/header.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${person.accessControl eq 'CUSTOMER'}">
    <c:redirect url="/"/>
</c:if>
<h2>All Customer Representatives</h2>
<hr>
<div id="msg"></div>
<div id="cusRep">
    <table>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Start Date</th>
            <c:if test="${person.accessControl eq 'MANAGER'}"><th>Hourly Rate</th></c:if>
                <th>Phone Number</th>
                <!--<th>Username</th>-->
                <th>Street</th>
                <th>City</th>
                <th>State</th>
                <th>Zip Code</th>
            </tr>
        <c:forEach var="cr" items="${customerRepresentatives}">
            <tr id="${cr.id}">
                <c:choose>
                    <c:when test="${person.accessControl eq 'MANAGER'}">
                    <form class="adminManageForm" method="GET" action="#">
                        <td><input class="empInfo" type="text" name="firstName" value="${cr.firstName}"/></td>
                        <td><input class="empInfo" type="text" name="lastName" value="${cr.lastName}"/></td>
                        <td><input class="empInfo" type="text" name="startDate" value="${cr.startDate}"/></td>
                        <td><input class="empInfo" type="text" name="hourlyRate" value="${cr.hourlyRate}"/></td>
                        <td><input class="empInfo" type="text" name="phone" value="${cr.phone}"/></td>
                        <td><input class="empInfo" type="text" name="street" value="${cr.address.street}"/></td>
                        <td><input class="empInfo" type="text" name="city" value="${cr.address.city}"/></td>
                        <td><input class="empInfo" type="text" name="state" value="${cr.address.state}"/></td>
                        <td><input class="empInfo" type="text" name="zipCode" value="${cr.address.zipCode}"/></td>
                        <input type="hidden" name="id" value="${cr.id}">
                        <td><input type="submit" name="operation" value="Update"></td>
                        <td><input type="submit" name="operation" value="Delete"></td>
                    </form>
                </c:when>

                <c:when test="${person.accessControl eq 'CUSTOMER_REPRESENTATIVE'}">
                    <td>${cr.firstName}</td>
                    <td>${cr.lastName}</td>
                    <td>${cr.startDate}</td>
                    <td>${cr.phone}</td>
                    <td>${cr.address.street}</td>
                    <td>${cr.address.city}</td>
                    <td>${cr.address.state}</td>
                    <td>${cr.address.zipCode}</td>
                </c:when>
            </c:choose>
            </tr>
        </c:forEach>
    </table>
</div>
<%@include file="/WEB-INF/views/includes/footer.jsp" %>