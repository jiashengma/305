<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@include file="/WEB-INF/views/includes/header.jsp" %>
	<h2 id="h">Sign Me Up</h2>
	
		<form method="POST" action="/register">
			<input type="text" name="userName" placeholder="Username"><br>
			<input type="text" name="firstName" placeholder="First Name"><br>
			<input type="text" name="lastName" placeholder="Last Name"><br>
			<input type="text" name="email" placeholder="email"><br>
			<input type="text" name="reemail" placeholder="Re-enter email"><br>
			<input type="password" name="password" placeholder="password"><br>
			<input type="password" name="repassword" placeholder="Re-enter password"><br>
			
			Birthday:
			<select name="month">
				<option value="month">Month</option>
				<option value="Jan">January</option>
				<option value="Feb">February</option>
				<option value="Mar">March</option>
				<option value="Apr">April</option>
				<option value="May">May</option>
				<option value="June">June</option>
				<option value="July">July</option>
				<option value="Aug">August</option>
				<option value="Sept">September</option>
				<option value="Oct">October</option>
				<option value="Nov">November</option>
				<option value="Dec">December</option>
			</select>
			<select name="day">
				<option value="day">Day</option>
				
			</select>
		
			<select name="year">
				<option value="year">Year</option>
				
			</select>
			<br/>
			Gender: 
			<input type="radio" name="gender" value="F">Female 
			<input type="radio" name="gender" value="M">Male 
			<br>
			<input type="submit" name="submit" value="Sign Me Up">
		</form>
	
<%@include file="/WEB-INF/views/includes/footer.jsp" %>