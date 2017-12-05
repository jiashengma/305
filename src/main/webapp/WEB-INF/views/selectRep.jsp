<%-- 
    Document   : selectRep
    Created on : Nov 30, 2017, 10:14:08 PM
    Author     : majiasheng
--%>
<%@include file="/WEB-INF/views/includes/header.jsp" %>
<c:if test="${person.accessControl ne 'CUSTOMER'}">
    <c:redirect url=""/>
</c:if>

<h2>Select Your Reservation Representative</h2>
<hr>
${msg}
<div class="mask">
    <!--TODO: check if this is from buy now or auction-->
    <c:choose>
        <c:when test="${empty auction}">
            <form action="/bookflight">
                <!--hidden inputs from buy now-->
                
            </c:when>
            <c:otherwise>
                <form action="/bookflightViaAuction">        
                    <!--hidden inputs from auction-->
                <input type="number" name="NYOP" value="${auction.NYOP}" required/><br>
                <input type="hidden" name="personAccNo" value="${auction.accNum}"/>
                <input type="hidden" name="airline" value="${auction.airline}"/>
                <input type="hidden" name="flightNo" value="${auction.flightNo}"/>
                <input type="hidden" name="flightClass" value="${auction.flightClass}"/>
                </c:otherwise>
            </c:choose>

            <select>
                <c:forEach var="rep" items="${customerRepresentatives}">
                    <option name="${rep.ssn}">${rep.firstName} ${rep.lastName}</option>
                </c:forEach>
            </select>

            <input type="submit" value="Select"/>
        </form>
</div>
<%@include file="/WEB-INF/views/includes/footer.jsp" %>