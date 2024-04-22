package com.example.demo.model;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "transactions")
public class Transaction {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        @Column(name = "transactionId")
        private Long transactionId;

        @Column(name = "user_id")
        private Long userId;

        @Column(name = "booking_id")
        private Long bookingId;

        @Column(name = "transaction_date_time")
        private LocalDateTime transactionDateTime;

        @Column(name = "payment_method")
        private String paymentMethod;

        @Column(name = "transaction_amount")
        private BigDecimal transactionAmount;

        @Column(name = "status")
        private Enum<Status> status;
        public enum Status {
        ACCEPTED,
        PENDING,
        DECLINED
    }
        public Transaction(Long transactionId, Long userId, Long bookingId, LocalDateTime transactionDateTime, String paymentMethod, BigDecimal transactionAmount, Enum<Status> status) {
            this.transactionId = transactionId;
            this.userId = userId;
            this.bookingId = bookingId;
            this.transactionDateTime = transactionDateTime;
            this.paymentMethod = paymentMethod;
            this.transactionAmount = transactionAmount;
            this.status = status;
        }
    public Transaction(Long transactionId, Long userId, LocalDateTime transactionDateTime, String paymentMethod, BigDecimal transactionAmount, Enum<Status> status) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.bookingId = null;
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
                    ", bookingId=" + bookingId +
                    ", transactionDateTime=" + transactionDateTime +
                    ", paymentMethod='" + paymentMethod + '\'' +
                    ", transactionAmount=" + transactionAmount +
                    '}';
        }

        public void setTransactionId(Long transactionId) {
            this.transactionId = transactionId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public void setBookingId(Long bookingId) {
            this.bookingId = bookingId;
        }

        public void setTransactionDateTime(LocalDateTime transactionDateTime) {
            this.transactionDateTime = transactionDateTime;
        }

        public void setPaymentMethod(String paymentMethod) {
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

        public Long getBookingId() {
            return bookingId;
        }

        public LocalDateTime getTransactionDateTime() {
            return transactionDateTime;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public BigDecimal getTransactionAmount() {
            return transactionAmount;
        }
    }


