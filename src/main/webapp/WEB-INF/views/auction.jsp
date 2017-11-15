<%-- 
    Document   : auction
    Created on : Nov 12, 2017, 3:58:12 PM
    Author     : majiasheng
--%>

<%@include file="/WEB-INF/views/includes/header.jsp" %>

<c:choose>
    <!--prohibit unlogged in user to get to this page-->
    <c:when test="${empty person}">
        <c:redirect url=""/>
    </c:when>
</c:choose>

<!-- body -->
<div>
    <!--TODO:display flight info maybe?-->
    Please enter your bid for ${airline} No.${flightNo}
    <form name="" action="/bid" method="POST">
        <input type="number" name="bid" placeholder="Your bid in $"/><br>
        <input type="hidden" name="bidderId" value="${person.id}"/>
        <input type="hidden" name="hiddenFare" value="${hiddenFare}"/>
        <input type="submit" value="Bid" />
    </form>
</div>
<!-- end body -->

<%@include file="/WEB-INF/views/includes/footer.jsp" %>