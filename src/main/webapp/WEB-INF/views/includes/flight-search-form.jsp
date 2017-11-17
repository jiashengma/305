<div class="searchform">
    <form action="/search" method="GET">
        <div class="row">
            <div class="col-md-3">Flying From</div>
            <div class="col-md-3">Flying To</div>
        </div>
        <div class="row">
            <input list="airportNames" name="flyingFrom">
            <datalist id="airportNames">
                <c: forEach var="airport" items="${airports}">
                    <option name="${airport.name}" value="${airport.shortName}">
                </c: forEach>
            </datalist>
            <div class="col-md-3"><input type="text" name="flyingTo" placeholder="City or airport"></div>
        </div>

        <div class="row">
            <div class="col-md-3">Departing</div>
            <div class="col-md-3">Returning</div>
            <div class="col-md-3">Number of Passenger</div>
        </div>
        <div class="row">
            <div class="col-md-3"><input type="date" name="depDate"></div>
            <div class="col-md-3"><input type="date" name="retDate"></div>
            <div class="col-md-3">
                <select name="Passenger">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                </select>
            </div>
        </div>

        <div class="row">
            <div class="col-md-3">
                Preferences
            </div>
        </div>
        <div class="row">
            <div class="col-md-1">
                <select name="prefMeal">
                    <option value="meal1">Meal 1</option>
                    <option value="meal2">Meal 2</option>
                    <option value="meal3">Meal 3</option>
                </select>
            </div>

            <div class="col-md-1">
                <select name="prefClass">
                    <option value="firstClass">First Class</option>
                    <option value="economy">Economy</option>
                    <option value="business">Business</option>
                </select>
            </div>
        </div>

        <div class="row">
            <div class="col-md-3">
                <input type="submit" name="Search">
            </div>
        </div>
    </form>
</div>