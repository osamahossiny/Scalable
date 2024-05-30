//package com.example.demo.Controller;
//
//import com.example.demo.Repository.FlightReservationRepository;
//import com.example.demo.Model.*;
//import com.stripe.Stripe;
//import com.stripe.exception.StripeException;
//import com.stripe.model.Customer;
//import com.stripe.model.checkout.Session;
//import com.stripe.param.checkout.SessionCreateParams;
//import com.stripe.param.checkout.SessionCreateParams.LineItem.PriceData;
//import org.springframework.web.bind.annotation.*;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//
//@RestController
////@CrossOrigin(origins = "http://frontend.com")
//public class PaymentController {
//    final FlightReservationRepository flightReservationRepository;
//
//    public PaymentController(FlightReservationRepository flightReservationRepository) {
//        this.flightReservationRepository = flightReservationRepository;
//    }
//    String STRIPE_API_KEY = System.getenv().get("STRIPE_API_KEY");
//
//    @PostMapping("/checkout/hosted")
//    String hostedCheckout(@RequestBody FlightReservation flightReservation) throws StripeException {
//
//        Stripe.apiKey = STRIPE_API_KEY;
//        String clientBaseURL = System.getenv().get("CLIENT_BASE_URL");
//
//        // Start by finding an existing customer record from Stripe or creating a new one if needed
//        Optional<FlightReservation> f = flightReservationRepository.findById(flightReservation.getId());
//        if (f.isEmpty()) {
//            throw new RuntimeException("Flight reservation not found");
//        }
//        flightReservation = f.get();
//        System.out.println(flightReservation.getAppUser().getStripeCustomerId());
//        System.out.println("heeeeere");
//        Customer customer = Customer.retrieve(flightReservation.getAppUser().getStripeCustomerId());
//        Map<String, String> metadata = new HashMap<>();
//        metadata.put("reservation_id", flightReservation.getId()+"");
//        metadata.put("user_id", flightReservation.getAppUser().getId()+"");
//        // Next, create a checkout session by adding the details of the checkout
//        SessionCreateParams.Builder paramsBuilder =
//                SessionCreateParams.builder()
//                        .setMode(SessionCreateParams.Mode.PAYMENT)
//                        .setCustomer(customer.getId())
//                        .setSuccessUrl("https://google.com")
//                        .setCancelUrl("https://youtube.com");
//
//        PlaneSeat seat = flightReservation.getPlaneSeat();
//        FlightPackage flightPackage = flightReservation.getFlightPackage();
//        boolean seatChargeable = flightReservation.isSeatChargeable();
//        boolean extraBaggage = flightReservation.isExtraBaggage();
//        boolean withInsurance = flightReservation.isWithInsurance();
//        String[] parts = seat.getSeatCategory().name().split("(?=[A-Z])");
//        parts[1] = "(" + parts[1] + ")";
//        String seatName = seat.getPlane().getName()+" "+flightPackage.getFlight().getDepartureLocation()+"-"+flightPackage.getFlight().getArrivalLocation()+":\n"+flightPackage.getFlight().getDepDate()+"\n Time:"+flightPackage.getFlight().getTimeOfDep()+" to "+flightPackage.getFlight().getTimeOfArrival()+"\n"+"Class: "+String.join(" ", parts)+ "\n"+"seat "+seat.getSeatNumber();
//        Product seatProduct = new Product(seatName, seat.getPrice(), 1);
//        Product flightPackageProduct = new Product(flightPackage.getMealInfo(), flightPackage.getPrice(), 1);
//        Product extraBaggageProduct = new Product("Extra Baggage", flightReservation.getExtraBaggagePrice(), 1);
//        Product insuranceProduct = new Product("Insurance", flightReservation.getWithInsurancePrice(), 1);
//        Product seatChargeableProduct = new Product("Seat Charge", flightReservation.getSeatChargeablePrice(), 1);
//        ArrayList<Product> products = new ArrayList<>();
//        products.add(seatProduct);
//        products.add(flightPackageProduct);
//        if (seatChargeable) {
//            products.add(seatChargeableProduct);
//        }
//        if (extraBaggage) {
//            products.add(extraBaggageProduct);
//        }
//        if (withInsurance) {
//            products.add(insuranceProduct);
//        }
//        for (Product product : products) {
//            System.out.println(product.getName());
//            System.out.println(product.getPrice());
//            System.out.println(BigDecimal.valueOf(product.getPrice()));
//            System.out.println("----");
//            paramsBuilder.addLineItem(
//                    SessionCreateParams.LineItem.builder()
//                            .setQuantity((long) product.getQuantity())
//                            .setPriceData(
//                                    PriceData.builder()
//                                            .setProductData(
//                                                    PriceData.ProductData.builder()
//                                                            .setName(product.getName())
//                                                            .build()
//                                            )
//                                            .setCurrency("usd")
//                                            .setUnitAmountDecimal(BigDecimal.valueOf(product.getPrice()*100))
//                                            .build())
//                            .build());
//        }
//        paramsBuilder.putMetadata("reservation_id", flightReservation.getId()+"").build();
//        paramsBuilder.putMetadata("user_id", flightReservation.getAppUser().getId()+"").build();
//        paramsBuilder.setPaymentIntentData(
//                SessionCreateParams.PaymentIntentData.builder()
//                        .putMetadata("reservation_id", flightReservation.getId()+"")
//                        .putMetadata("user_id", flightReservation.getAppUser().getId()+"")
//                        .build());
//        Session session = Session.create(paramsBuilder.build());
//
//        return session.getUrl();
//    }
//
//}