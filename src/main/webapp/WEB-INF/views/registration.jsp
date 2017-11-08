<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@include file="/WEB-INF/views/includes/header.jsp" %>
<h2 id="h">Sign Me Up</h2>
<div id="regform">
    <form method="POST" action="/register">
        First Name<input type="text" name="firstName" placeholder="First Name"><br>
        Last Name<input type="text" name="lastName" placeholder="Last Name"><br>
        Email<input type="text" name="email" placeholder="email"><br>
        Re-enter email<input type="text" name="reemail" placeholder="Re-enter email"><br>
        Username<input type="text" name="userName" placeholder="Username"><br>
        Password<input type="password" name="password" placeholder="password"><br>
        Re-enter password<input type="password" name="repassword" placeholder="Re-enter password"><br>
        Tel<input type="tel" name="phone" placeholder="Phone Number"><br>
        Address<input type="text" name="street" placeholder="Street">
        <input type="text" name="city" placeholder="City">

        <select name="state">
            <!--TODO: iterate through state enum-->

            <c:forEach var="state" items="${states}">
                <option name="${state}">${state}</option>
            </c:forEach>

        </select>
        <input type="number" name="zipcode" placeholder="Zip Code"><br>

        <input type="submit" name="submit" value="Sign Me Up">
    </form>
</div>

<%@include file="/WEB-INF/views/includes/footer.jsp" %>