<%-- 
    Document   : account-setting
    Created on : Nov 28, 2017, 10:26:52 PM
    Author     : majiasheng
--%>

<%@include file="/WEB-INF/views/includes/header.jsp" %>

<!--send home if user is empty-->
<c:if test="${empty person}">
    <c:redirect url="/"/>
</c:if>
<h2>Account Setting</h2>
<hr>
${msg}
<div id="mask">
    <form name="userInfoForm" action="/confirmEdit" method="POST">
        <input type="hidden" name="username" value="${person.userName}">
        <table class="accSettingTable">

            <tr>
                <td>First Name</td>
                <td><input type="text" name="firstname" value="${person.firstName}"></td>
            </tr>

            <tr>
                <td>Last Name</td>
                <td><input type="text"  name="lastname" value="${person.lastName}"></td>
            </tr>

            <tr>
                <td>Tel</td>
                <td><input type="text" name="phone" value="${person.phone}"></td>
            </tr>

            <tr>
                <td>Address</td>
                <td>
                    <input type="text" name="address.street" value="${person.address.street}" required>
                    <input type="text" name="address.city" value="${person.address.city}" required>
                    <select name="state">
                        <option name="${person.address.state}" selected="selected">"${person.address.state}"</option>>
                        <c:forEach var="state" items="${states}">
                            <option name="${state}">${state}</option>
                        </c:forEach>
                    </select>
                    <input type="text" name="address.zipCode" value="${person.address.zipCode}" required>
                <td>
            </tr>
            <c:if test="${person.accessControl eq 'CUSTOMER'}">
                <tr>
                    <td>Credit Card Number</td>
                    <td><input type="text" name="creditCard" value="${person.creditCard}"></td>
                </tr>
            </c:if>

        </table>



        <input type="submit" value="Update">
    </form>
</div>

<%@include file="/WEB-INF/views/includes/footer.jsp" %>