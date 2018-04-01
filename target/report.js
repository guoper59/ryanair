$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/booking/bookingPayment.feature");
formatter.feature({
  "line": 2,
  "name": "Booking Payment",
  "description": "",
  "id": "booking-payment",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@booking"
    },
    {
      "line": 1,
      "name": "@payment"
    },
    {
      "line": 1,
      "name": "@smoke"
    },
    {
      "line": 1,
      "name": "@regression"
    },
    {
      "line": 1,
      "name": "@end2end"
    }
  ]
});
formatter.scenario({
  "line": 4,
  "name": "Booking up to a Decline payment on",
  "description": "",
  "id": "booking-payment;booking-up-to-a-decline-payment-on",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 3,
      "name": "@declined"
    },
    {
      "line": 3,
      "name": "@negative"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "I make a booking from DUB to MAD on 14/09/2018 for 2 adults and 1 child",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I pay for booking with card details 5555 5555 5555 5557, 10/2018 and 265",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "I should get payment declined message",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "DUB",
      "offset": 22
    },
    {
      "val": "MAD",
      "offset": 29
    },
    {
      "val": "14/09/2018",
      "offset": 36
    },
    {
      "val": "2",
      "offset": 51
    },
    {
      "val": "1",
      "offset": 64
    }
  ],
  "location": "bookingPaymentSteps.booking(String,String,String,int,int)"
});
formatter.result({
  "duration": 94999364514,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "5555 5555 5555 5557",
      "offset": 36
    },
    {
      "val": "10/2018",
      "offset": 57
    },
    {
      "val": "265",
      "offset": 69
    }
  ],
  "location": "bookingPaymentSteps.payWithCreditCard(String,String,String)"
});
formatter.result({
  "duration": 6773144017,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "declined",
      "offset": 21
    }
  ],
  "location": "bookingPaymentSteps.verifyDeclinedPaymentMessage(String)"
});
formatter.result({
  "duration": 10405615623,
  "status": "passed"
});
});