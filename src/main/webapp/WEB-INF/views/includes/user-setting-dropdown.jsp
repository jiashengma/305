
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="#" class="dropdown-toggle" data-toggle="dropdown">${person.firstName} ${person.lastName}<strong class="caret"></strong></a>


<ul class="dropdown-menu">
    <li><a href="/account-setting">Account</a></li>
    <c:choose> 
        <c:when test="${person.accessControl ne 'CUSTOMER_REPRESENTATIVE' and person.accessControl ne 'MANAGER'}">
            <li><a href="/reservation-history">Reservation History</a></li>
            <li><a href="/auction-history">Auction History</a></li>
        </c:when>
        <c:otherwise>
            <!--TODO-->
        </c:otherwise>
    </c:choose>
    <li class="divider">
    </li>
    <li>
        <a href="/logout">Log out</a>
    </li>
</ul>

