<%@include file="/WEB-INF/views/includes/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:useBean id="address" class="com.ajax.model.Address" />
<h2 id="h">Sign Me Up</h2>
<div id="regform">
    <form id="registrationForm" method="POST" action="/register">
        First Name<br>
        <input type="text" name="firstName" placeholder="First Name" required>
        <span class="error"><form:errors path="customer.firstName"/></span><br>
        
        Last Name<br>
        <input type="text" name="lastName" placeholder="Last Name" required>
        <span class="error"><form:errors path="customer.lastName"/></span><br>
        
        Email<br>
        <input type="text" name="email" placeholder="email" required>
        <span class="error"><form:errors path="customer.email"/></span><br>
        
        Re-enter email<br>
        <input type="text" name="reemail" placeholder="Re-enter email" required><br>
        <div id="emailMisMatch"></div>
        
        Username<br>
        <input type="text" name="userName" placeholder="Username" required>
        <span class="error"><form:errors path="customer.userName"/></span><br>
        
        Password<br>
        <input type="password" name="password" placeholder="password" required>
        <span class="error"><form:errors path="customer.password"/></span><br>
        
        Re-enter password<br>
        <input type="password" name="repassword" placeholder="Re-enter password" required><br>
        <div id="pwMisMatch"></div>
        
        Tel<br>
        <input type="text" name="phone" placeholder="Phone Number" required>
        <span class="error"><form:errors path="customer.phone"/></span><br>
        
        Address<br>
        <input type="text" name="address.street" placeholder="Street" required>
        <input type="text" name="address.city" placeholder="City" required>
        <select name="state">
            <c:forEach var="state" items="${states}">
                <option name="${state}">${state}</option>
            </c:forEach>
        </select>
        <input type="text" name="address.zipCode" placeholder="Zip Code" required>
        <span class="error"><form:errors path="customer.address.street"/></span>
        <span class="error"><form:errors path="customer.address.city"/></span>
        <span class="error"><form:errors path="customer.address.zipCode"/></span><br>
        
        Credit Card Number<br><input type="text" name="creditCard" placeholder="Credit Card No."><br>
        <span class="error"><form:errors path="customer.creditCard"/></span><br>
        
        <select name="prefMeal">
            <option value="meal1">Meal 1</option>
            <option value="meal2">Meal 2</option>
            <option value="meal3">Meal 3</option>
        </select>


        <input type="submit" name="submit" value="Sign Me Up">
    </form>
</div>

<%@include file="/WEB-INF/views/includes/footer.jsp" %>