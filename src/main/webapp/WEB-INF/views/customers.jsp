<%@include file="/WEB-INF/views/includes/header.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${empty person or person.accessControl ne'CUSTOMER_REPRESENTATIVE'}">
    <c:redirect url="/"/>
</c:if>
<h2>Customers</h2>
<hr>
TODO: show only customers that are in this particular customer rep's list, 
and can manager/admin see and edit all customers?

<c:choose>
    <c:when test="${fn:length(customers) <= 0}">
        <h3>No customers in your list yet, go talk to them</h3>
    </c:when>
    <c:otherwise>

        <div id="customer">
            <table>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Rating</th>
                    <th>Account</th>
                    <th>Credit Card</th>
                    <th>Phone Number</th>
                    <th>Street</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Zip Code</th>
                </tr>
                <c:forEach var="customer" items="${customers}">
                    <tr>
                        <td><input type="text" name="firstName" value="${customer.firstName}"/></td>
                        <td><input type="text" name="lastName" value="${customer.lastName}"/></td>
                        <td><input type="text" name="email" value="${customer.email}"/></td>
                        <td><input type="text" name="rating" value="${customer.rating}"/></td>
                        <td><input type="text" name="creditCard" value="${customer.creditCard}"/></td>
                        <td><input type="text" name="accNum" value="${customer.accNum}"/></td>
                        <td><input type="text" name="phone" value="${customer.phone}"/></td>
                        <td><input type="text" name="address.street" value="${customer.address.street}"/></td>
                        <td><input type="text" name="address.city" value="${customer.address.city}"/></td>
                        <td><input type="text" name="address.state" value="${customer.address.state}"/></td>
                        <td><input type="text" name="address.zipCode" value="${customer.address.zipCode}"/></td>
                        TODO:add edit and delete buttons
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:otherwise>
</c:choose>


<%@include file="/WEB-INF/views/includes/footer.jsp" %>