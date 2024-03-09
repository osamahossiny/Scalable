package com.example.demo.TrainTicket;
import jakarta.persistence.*;

@Entity
@Table
public class TrainTicket {
    @Id
    @SequenceGenerator(
            name = "TrainTicket_sequence",
            sequenceName = "TrainTicket_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TrainTicket_sequence"
    )
    private Long id;
    private Long UserId;
    private Long TrainLinePlanId;
    private int price;
    private boolean withInsurance;

    public TrainTicket() {
    }
    public TrainTicket(Long id, Long UserId, Long TrainLinePlanId, int price, boolean withInsurance) {
        this.id = id;
        this.UserId = UserId;
        this.TrainLinePlanId = TrainLinePlanId;
        this.price = price;
        this.withInsurance = withInsurance;
    }
    public TrainTicket(Long UserId, Long TrainLinePlanId, int price, boolean withInsurance) {
        this.UserId = UserId;
        this.TrainLinePlanId = TrainLinePlanId;
        this.price = price;
        this.withInsurance = withInsurance;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return UserId;
    }
    public void setUserId(Long UserId) {
        this.UserId = UserId;
    }
    public Long getTrainLinePlanId() {
        return TrainLinePlanId;
    }
    public void setTrainLinePlanId(Long TrainLinePlanId) {
        this.TrainLinePlanId = TrainLinePlanId;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isWithInsurance() {
        return withInsurance;
    }

    public void setWithInsurance(boolean withInsurance) {
        this.withInsurance = withInsurance;
    }
}
