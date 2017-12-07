<%@include file="/WEB-INF/views/includes/header.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${empty person or person.accessControl ne'CUSTOMER_REPRESENTATIVE'}">
    <c:redirect url="/"/>
</c:if>
<h2>Customers</h2>
<hr>
<div id="msg"></div>
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
                    <th>Credit Card</th>
                    <th>Account</th>
                    <th>Phone Number</th>
                    <th>Street</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Zip Code</th>
                </tr>
                <c:forEach var="customer" items="${customers}">
                    <tr id="${customer.id}">
                    <form class="empManageForm" method="GET" action="#">
                        <td><input class="cusInfo" type="text" name="firstName" value="${customer.firstName}"/></td>
                        <td><input class="cusInfo" type="text" name="lastName" value="${customer.lastName}"/></td>
                        <td><input class="cusInfo" type="text" name="email" value="${customer.email}"/></td>
                        <td>${customer.rating}</td>
                        <td><input class="cusInfo" type="text" name="creditCard" value="${customer.creditCard}"/></td>
                        <td><input class="cusInfo" type="text" name="accNum" value="${customer.accNum}"/></td>
                        <td><input class="cusInfo" type="text" name="phone" value="${customer.phone}"/></td>
                        <td><input class="cusInfo" type="text" name="street" value="${customer.address.street}"/></td>
                        <td><input class="cusInfo" type="text" name="city" value="${customer.address.city}"/></td>
                        <td><input class="cusInfo" type="text" name="state" value="${customer.address.state}"/></td>
                        <td><input class="cusInfo" type="text" name="zipCode" value="${customer.address.zipCode}"/></td>
                        <input type="hidden" name="id" value="${customer.id}">
                        <td><input type="submit" name="empOperation" value="Update"></td>
                        <!--<td><input type="submit" name="empOperation" value="Delete"></td>-->
                    </form>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:otherwise>
</c:choose>


<%@include file="/WEB-INF/views/includes/footer.jsp" %>