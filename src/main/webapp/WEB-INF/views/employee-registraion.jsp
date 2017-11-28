
<%@include file="/WEB-INF/views/includes/header.jsp" %>

<!--send back to home page if person is not manager-->
<c:if test="${person.accessControl ne 'MANAGER'}">
    <c:redirect url="/"/>
</c:if>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:useBean id="address" class="com.ajax.model.Address" />
<h2 id="h">Add Employee</h2>
<div id="regform">
    <form method="POST" action="/register">
        First Name<br>
        <input type="text" name="firstName" placeholder="First Name" required>
        <span class="error"><form:errors path="employee.firstName"/></span><br>
        
        Last Name<br>
        <input type="text" name="lastName" placeholder="Last Name" required>
        <span class="error"><form:errors path="employee.lastName"/></span><br>
        
        SSN<br>
        <input type="password" name="ssn" placeholder="ssn" required>
        <span class="error"><form:errors path="employee.ssn"/></span><br>
        
        Hourly Rate<br>
        <input type="number" name="hourlyRate" placeholder="Hourly Rate" required>
        <span class="error"><form:errors path="employee.hourlyRate"/></span><br>
        
        Username<br>
        <input type="text" name="userName" placeholder="Username" required>
        <span class="error"><form:errors path="employee.userName"/></span><br>
        
        Password<br>
        <input type="password" name="password" placeholder="password" required>
        <span class="error"><form:errors path="employee.password"/></span><br>
        
        Re-enter password<br>
        <input type="password" name="repassword" placeholder="Re-enter password" required><br>
        <!--        Tel<input type="tel" name="phone" placeholder="Phone Number"><br>-->
        
        Tel<br>
        <input type="text" name="phone" placeholder="Phone Number" required>
        <span class="error"><form:errors path="employee.phone"/></span><br>
        
        Address<br>
        <input type="text" name="address.street" placeholder="Street" required>
        <input type="text" name="address.city" placeholder="City" required>
        <select name="state">
            <c:forEach var="state" items="${states}">
                <option name="${state}">${state}</option>
            </c:forEach>
        </select>
        <input type="text" name="address.zipCode" placeholder="Zip Code" required>
        <span class="error"><form:errors path="employee.address.street"/></span>
        <span class="error"><form:errors path="employee.address.city"/></span>
        <span class="error"><form:errors path="employee.address.zipCode"/></span><br>
        
        <input type="submit" name="submit" value="Add Employee">
    </form>
</div>

<%@include file="/WEB-INF/views/includes/footer.jsp" %>