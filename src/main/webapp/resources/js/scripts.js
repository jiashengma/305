//const SUCCESS = 1;
//const FAILURE = 0;
//const AUCTION_ERROR = - 1;
//const AUCTION_SUCCESS = 1;
//const ERROR = - 1;
//const AUCTION_LOW_BID = 0;
/**
* asks users to log in before they can proceed to book or bid flights
*/
$(".reservationFormGroup").submit(function (e) {
    var id = $("input[name=personId]").val();
    if (id === null || id === "") {
        alert("Please log in to proceed");
        e.preventDefault();
    }
});
        /**
         * send ajax post when user bids
         */
//$("#biddingform").submit(function (e) {
//    // prevent form from submitting
//    e.preventDefault();
//    
//    var auction = {NYOP:this.NYOP.value,
//            "personAccNo": this.personAccNo.value,
//            "airline": this.airline.value,
//            "flightNo": this.flightNo.value,
//            "flightClass": this.flightClass.value,
//            "hiddenFare":this.hiddenFare.value};
//        console.log(auction);
//
//    $.ajax({
//        url: $(this).attr('action'),
//        type: "POST",
//        contentType: "application/json",
//        data: auction,
//        dataType: "json",
//        success: function (response, status, xhr) {
//            //TODO: redirect to a page? send get/post request to do reservation           
//            
//            if(response) {
//                alert("the seat is yours!");
//            } else {
//                alert("low bid, try agagin");
//            }
//            
//        },
//        error: function (xhr, textStatus, errorThrown) {
//            //TODO: notify user of low bid
//            alert("hmm.. something went wrong while trying to bid");
//        }
//    });
//});

