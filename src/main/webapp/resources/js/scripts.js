
/**
 * asks users to log in before they can proceed to book or bid flights
 */
$(".reservationFormGroup").submit(function(e) {
    var id = $("input[name=personId]").val();
    if (id==null) {
        alert("Please log in to proceed");
        e.preventDefault();
    }
});

