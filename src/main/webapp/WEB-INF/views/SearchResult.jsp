<div id="resrForm" >
    <table border="1">
        <tr>
            <th>Festival Name:</th>
            <th>Location:</th>
            <th>Start Date:</th>
            <th>End Date:</th>
            <th>URL:</th>
        </tr>
        <% for(int i = 0; i < allFestivals.size(); i+=1) { %>
            <tr>
                <td>${allFestivals.get(i).getFestivalName()}</td>
                <td>${allFestivals.get(i).getLocation()}</td>
                <td>${allFestivals.get(i).getStartDate()}</td>
                <td>${allFestivals.get(i).getEndDate()}</td>
                <td>${allFestivals.get(i).getURL()}</td>
            </tr>
        <% } %>
    </table>
</div>