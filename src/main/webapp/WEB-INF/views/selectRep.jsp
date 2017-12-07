<%-- 
    Document   : selectRep
    Created on : Nov 30, 2017, 10:14:08 PM
    Author     : majiasheng
--%>
<%@include file="/WEB-INF/views/includes/header.jsp" %>
<c:if test="${person.accessControl ne 'CUSTOMER' }">
    <c:redirect url=""/>
</c:if>

<h2>Select Your Reservation Representative</h2>
<hr>
${msg}
<div class="mask">
    <!--TODO: check if this is from buy now or auction-->
    <c:choose>
        <c:when test="${empty auction}"><form id="bookingForm" action="/bookflight" method="POST"></c:when>
        <c:otherwise><form action="/bookflightViaAuction" method="POST"></c:otherwise>
    </c:choose>
        <select name="rep">
            <c:forEach var="rep" items="${customerRepresentatives}" varStatus="loop">
                <!--TODO hide ssn.....-->
                <option value="${rep.ssn}">${rep.firstName} ${rep.lastName}</option>
            </c:forEach>
        </select>

        <input type="submit" value="Select"/>
    </form>
</div>
<%@include file="/WEB-INF/views/includes/footer.jsp" %>