package com.example.demo.Controller;

import com.example.demo.Model.FlightReservation;
import com.example.demo.Service.TransactionService;
import com.example.demo.Model.Transaction;
import com.stripe.exception.EventDataObjectDeserializationException;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.*;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
public class StripeWebhookController {

    private TransactionService transactionService;

    public StripeWebhookController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @Value("${stripe.webhook.secret}")
    private String endpointSecret;
    @PostMapping("/api/transaction/stripe/events")
    public void handleStripeEvent(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) throws EventDataObjectDeserializationException {
        if(sigHeader == null){
            return;
        }
        Event event;
        try {
            event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
        } catch (SignatureVerificationException e) {
            System.out.println("⚠️  Webhook error while validating signature.");
            return;
        }
        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = null;
        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        } else {
            System.out.println("🔔  No data to deserialize.");
        }
        switch (event.getType()){
            case "checkout.session.completed":
                System.out.println("check out completed");
                break;

            case "payment_intent.succeeded":
                System.out.println("Payment intent succeeded");
                PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
                if(paymentIntent != null) {
                    System.out.println("Payment for " + paymentIntent.getAmount() + "  " + paymentIntent.getStatus());
                    //TODO: Update the transaction status in the database
                    Long totalAmount = paymentIntent.getAmount();
                    String status = paymentIntent.getStatus();
                    System.out.println("HIIiIIIIIIIIII"+ status);
                    System.out.println(paymentIntent);
                    BigDecimal amount = BigDecimal.valueOf(totalAmount);
                    Transaction.Status transactionStatus = Transaction.Status.stringToEnum(status);
                    Long reservationId = Long.valueOf(paymentIntent.getMetadata().get("reservation_id"));
                    Long userId = Long.valueOf(paymentIntent.getMetadata().get("user_id"));
                    long unixTimestamp = paymentIntent.getCreated(); // Assuming paymentIntent.getCreated() returns Unix timestamp
                    Instant instant = Instant.ofEpochSecond(unixTimestamp);
                    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                    Transaction transaction = new Transaction(userId, reservationId, localDateTime, FlightReservation.PaymentMethod.VISA, amount, transactionStatus);
                    System.out.println(transaction);
                    transactionService.addNewTransaction(transaction);
                }
                break;
            case "payment_intent.created":
                System.out.println("Payment intent created");

                break;
            case "charge.succeeded":
                System.out.println("Charge succeeded");
                break;
            default:
                System.out.println("Unhandled event type: " + event.getType());
        }
    }
}
