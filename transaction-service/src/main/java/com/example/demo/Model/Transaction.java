package com.example.demo.Model;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "transactions")
public class Transaction {
        @Id
        @SequenceGenerator(
                name = "transaction_sequence",
                sequenceName = "transaction_sequence",
                allocationSize = 1
        )
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "transaction_sequence"
        )
//        @GeneratedValue(strategy = GenerationType.IDENTITY)

        @Column(name = "transactionId")
        private Long transactionId;

        @Column(name = "user_id")
        private Long userId;

        @Column(name = "reservation_id")
        private Long reservationId;

        @Column(name = "transaction_date_time")
        private LocalDateTime transactionDateTime;

        @Column(name = "payment_method")
        private FlightReservation.PaymentMethod paymentMethod;

        @Column(name = "transaction_amount")
        private BigDecimal transactionAmount;

        @Column(name = "status")
        private Status status;
        public enum Status {
        SUCCESSFUL,
        PENDING,
        DECLINED;
            public static Status stringToEnum(String status) {
                switch (status) {
                    case "succeeded":
                        return Status.SUCCESSFUL;
                    case "requires_payment_method":
                        return Status.PENDING;
                    case "requires_confirmation":
                        return Status.PENDING;
                    case "requires_action":
                        return Status.PENDING;
                    case "processing":
                        return Status.PENDING;
                    case "failed":
                        return Status.DECLINED;
                    default:
                        return null;
                }
            }

    }
        public Transaction(Long transactionId, Long userId, Long reservationId, LocalDateTime transactionDateTime, FlightReservation.PaymentMethod  paymentMethod, BigDecimal transactionAmount, Status status) {
            this.transactionId = transactionId;
            this.userId = userId;
            this.reservationId = reservationId;
            this.transactionDateTime = transactionDateTime;
            this.paymentMethod = paymentMethod;
            this.transactionAmount = transactionAmount;
            this.status = status;
        }

        public Transaction(Long userId, Long reservationId, LocalDateTime transactionDateTime, FlightReservation.PaymentMethod paymentMethod, BigDecimal transactionAmount, Status status) {
            this.userId = userId;
            this.reservationId = reservationId;
            this.transactionDateTime = transactionDateTime;
            this.paymentMethod = paymentMethod;
            this.transactionAmount = transactionAmount;
            this.status = status;
        }


        public Transaction() {

        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "transactionId=" + transactionId +
                    ", userId=" + userId +
                    ", reservationId=" + reservationId +
                    ", transactionDateTime=" + transactionDateTime +
                    ", paymentMethod='" + paymentMethod + '\'' +
                    ", transactionAmount=" + transactionAmount +
                    ", status=" + status +
                    '}';
        }

        public void setTransactionId(Long transactionId) {
            this.transactionId = transactionId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public void setReservationId(Long bookingId) {
            this.reservationId = bookingId;
        }

        public void setTransactionDateTime(LocalDateTime transactionDateTime) {
            this.transactionDateTime = transactionDateTime;
        }

        public void setPaymentMethod(FlightReservation.PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public void setTransactionAmount(BigDecimal transactionAmount) {
            this.transactionAmount = transactionAmount;
        }

        public Long getTransactionId() {
            return transactionId;
        }

        public Long getUserId() {
            return userId;
        }

        public Long getReservationId() {
            return reservationId;
        }

        public LocalDateTime getTransactionDateTime() {
            return transactionDateTime;
        }

        public FlightReservation.PaymentMethod getPaymentMethod() {
            return paymentMethod;
        }

        public BigDecimal getTransactionAmount() {
            return transactionAmount;
        }

        public Transaction.Status getStatus() {
            return status;
        }
        public void setStatus( Status status) {
            this.status = status;
        }
}


