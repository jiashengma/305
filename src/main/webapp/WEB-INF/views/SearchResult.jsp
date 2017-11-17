<div id="resrForm" >
    <table border="1">
        <tr>
            <th>Origin Airport</th>
            <th>Destination Airport</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Class</th>
        </tr>
        <c:forEach var="airport" items="${airports}">
            <tr>
                <td>Some Airport</td>
                <td>Another Airport</td>
                <td>Start Date</td>
                <td>Another Date</td>
                <td>Class</td>
            </tr>
        </c:forEach>
    </table>
</div>