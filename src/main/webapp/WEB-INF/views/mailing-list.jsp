<%-- 
    Document   : mailing-list
    Created on : Dec 6, 2017, 1:22:34 PM
    Author     : majiasheng
--%>
<%@include file="/WEB-INF/views/includes/header.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${empty person or person.accessControl ne'CUSTOMER_REPRESENTATIVE'}">
    <c:redirect url="/"/>
</c:if>
<h2>Mailing list</h2>
<hr>
<c:choose>
    <c:when test="${fn:length(customers) < 1}">
        <h3>No customers in your list yet, go talk to them</h3>
    </c:when>
    <c:otherwise>

        <div id="customer">
            <table>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                </tr>
                <c:forEach var="customer" items="${customers}">
                    <tr>
                        <td><input type="text" name="firstName" value="${customer.firstName}"/></td>
                        <td><input type="text" name="lastName" value="${customer.lastName}"/></td>
                        <td><input type="text" name="email" value="${customer.email}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:otherwise>
</c:choose>


<%@include file="/WEB-INF/views/includes/footer.jsp" %>