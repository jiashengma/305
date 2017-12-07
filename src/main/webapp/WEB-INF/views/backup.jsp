<%@page contentType="text/plain"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${person.accessControl ne 'MANAGER'}">
    <c:redirect url="/"/>
</c:if>
${dump}