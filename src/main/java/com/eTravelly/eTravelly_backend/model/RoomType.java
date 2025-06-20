package com.eTravelly.eTravelly_backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room_type")
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomTypeName;

    private Double pricePerPersonPerNight;
    private Integer totalRooms;
    private Integer maxAdults;

    @ManyToOne
    private Contract contract;
}
