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
        <c:when test="${empty auction}">
            <form id="bookingForm" action="/bookflight" method="POST">
                <!--hidden inputs from buy now-->

            </c:when>
            <c:otherwise>
                <form action="/bookflightViaAuction" method="POST">        
                    <!--hidden inputs from auction-->
<%--                <input type="hidden" name="NYOP" value="${auction.NYOP}"/><br>
                <input type="hidden" name="personAccNo" value="${auction.personAccNo}"/>
                <input type="hidden" name="airline" value="${auction.airline}"/>
                <input type="hidden" name="flightNo" value="${auction.flightNo}"/>
                <input type="hidden" name="flightClass" value="${auction.flightClass}"/>--%>
                </c:otherwise>
            </c:choose>

            <!--<select name="rep">-->
            <select id="reps">
                <c:forEach var="rep" items="${customerRepresentative}" varStatus="loop">
                    <!--TODO hide ssn.....-->
                    <option value="${loop.index}">${rep.firstName} ${rep.lastName}</option>
                    <option value="${rep.ssn}">${rep.firstName} ${rep.lastName}</option>
                </c:forEach>
            </select>
            <%--<c:forEach var="rep" items="${customerRepresentative}" varStatus="loop">--%>
                <!--<input type="hidden" name="rep_${loop.index}" value="${rep.ssn}"/>-->
            <%--</c:forEach>--%>
            <!--<input type="hidden" name="rep" value=""/>-->


            <input type="submit" value="Select"/>
        </form>
</div>
<%@include file="/WEB-INF/views/includes/footer.jsp" %>