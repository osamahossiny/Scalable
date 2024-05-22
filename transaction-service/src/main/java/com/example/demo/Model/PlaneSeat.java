package com.example.demo.Model;
import jakarta.persistence.*;

@Entity
@Table
public class PlaneSeat {
    @Id
    @SequenceGenerator(
            name = "plane_seat_id_sequence",
            sequenceName = "plane_seat_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "plane_seat_id_sequence"
    )
    private Long id;
    @Column
    private int seatNumber;
    @Enumerated(EnumType.STRING)
    private SeatCategory seatCategory;
    @ManyToOne
    @JoinColumn(
            name = "plane_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "plane_id_fk"
            )
    )
    private Plane plane;
    @Column
    private int price;

    public PlaneSeat(Long id, int seatNumber, SeatCategory seatCategory, Plane plane, int price) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.seatCategory = seatCategory;
        this.plane = plane;
        this.price = price;
    }

    public PlaneSeat(int seatNumber, SeatCategory seatCategory, Plane plane, int price) {
        this.seatNumber = seatNumber;
        this.seatCategory = seatCategory;
        this.plane = plane;
        this.price = price;
    }

    public PlaneSeat() {

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public SeatCategory getSeatCategory() {
        return seatCategory;
    }

    public void setSeatCategory(SeatCategory seatCategory) {
        this.seatCategory = seatCategory;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
