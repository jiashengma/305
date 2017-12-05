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
    <form>
        <select>
            <c:forEach var="rep" items="${customerRepresentatives}">
                <option name="${rep.ssn}">${rep.firstName} ${rep.lastName}</option>
            </c:forEach>
        </select>
    </form>
</div>
<%@include file="/WEB-INF/views/includes/footer.jsp" %>