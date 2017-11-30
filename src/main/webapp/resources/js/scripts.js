const SUCCESS = 1;
const FAILURE = 0;
const AUCTION_ERROR = -1;
const AUCTION_SUCCESS = 1;
const ERROR = -1;
const AUCTION_LOW_BID = 0;

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

/**
 * send ajax post when user bids
 */
//$("#biddingform").submit(function(e) {
//    // prevent form from submitting
//    e.preventDefault();
//    
//    var bid = $(this).find("input[name='bid']").val();
//    var bidderId = $(this).find("input[name='bidderId']").val();
//    var hiddenFare = $(this).find("input[name='hiddenFare']").val();
//    var airline = $(this).find("input[name='airline']").val();
//    var flightNo = $(this).find("input[name='flightNo']").val();
//    var url = $(this).attr("action");
//    
//    $.ajax({
//        url: url,
//        type: "POST",
//        contentType: "application/json",
//        data: {"bid":bid, "bidderId":bidderId, "hiddenFare":hiddenFare,"airline":airline,"flightNo":flightNo},
//        dataType: "json",
//        success: function (response, status, xhr) {
//            //TODO: redirect to a page? send get/post request to do reservation           
//        },
//        error: function (xhr, textStatus, errorThrown) {
//            //TODO: notify user of low bid
//        }
//    });
//});
//
