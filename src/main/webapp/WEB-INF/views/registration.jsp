<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@include file="/WEB-INF/views/includes/header.jsp" %>
<jsp:useBean id="address" class="com.ajax.model.Address" />
<h2 id="h">Sign Me Up</h2>
<div id="regform">
    <form method="POST" action="/register">
        First Name<br><input type="text" name="firstName" placeholder="First Name"><br>
        Last Name<br><input type="text" name="lastName" placeholder="Last Name"><br>
        Email<br><input type="text" name="email" placeholder="email"><br>
        Re-enter email<br><input type="text" name="reemail" placeholder="Re-enter email"><br>
        Username<br><input type="text" name="userName" placeholder="Username"><br>
        Password<br><input type="password" name="password" placeholder="password"><br>
        Re-enter password<br><input type="password" name="repassword" placeholder="Re-enter password"><br>
        <!--        Tel<input type="tel" name="phone" placeholder="Phone Number"><br>-->
        Tel<br><input type="text" name="phone" placeholder="Phone Number"><br>
        Address<br><input type="text" name="address.street" placeholder="Street">
        <input type="text" name="address.city" placeholder="City">
        <select name="state">
            <c:forEach var="state" items="${states}">
                <option name="${state}">${state}</option>
            </c:forEach>
        </select>
        <input type="text" name="address.zipCode" placeholder="Zip Code"><br>
        Credit Card Number<br><input type="text" name="creditCard" placeholder="Credit Card No."><br>

        <input type="submit" name="submit" value="Sign Me Up">
    </form>
</div>

<%@include file="/WEB-INF/views/includes/footer.jsp" %>