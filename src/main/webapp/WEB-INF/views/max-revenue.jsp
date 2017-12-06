<%@include file="/WEB-INF/views/includes/header.jsp" %>
<c:if test="${person.accessControl ne 'MANAGER'}">
    <c:redirect url="" />
</c:if>
<h2>Max Revenue</h2>
<div class="mask">
    <h3>Customer</h3>
    Username: ${empty customer.userName ? "N/A" : customer.userName}<br/>
    Email: ${customer.email}<br/>
    Revenue generated: &#36;${maxCustomerRevenue}
    <h3>Customer Representative</h3>
    Id: ${representative.id}<br/>
    First Name: ${representative.firstName}<br/>
    Last Name: ${representative.lastName}<br/>
    Revenue generated: &#36;${maxRepresentativeRevenue}
</div>
<%@include file="/WEB-INF/views/includes/footer.jsp" %>
