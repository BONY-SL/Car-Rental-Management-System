package com.example.CarRentalManagementApplication.entity;

import com.example.CarRentalManagementApplication.dto.GetBookingCarDTO;
import com.example.CarRentalManagementApplication.util.BookCarStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Table(name = "bookedcars")
@Data
public class BookedCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date fromDate;

    private Date toDate;

    private Long days;

    private Long price;

    private BookCarStatus bookCarStatus;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "car_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Car car;


    public GetBookingCarDTO getBookingCarDTO(){

        GetBookingCarDTO bookingCarDTO = new GetBookingCarDTO();

        bookingCarDTO.setId(id);
        bookingCarDTO.setDays(days);
        bookingCarDTO.setBookCarStatus(bookCarStatus);
        bookingCarDTO.setPrice(price);
        bookingCarDTO.setToDate(toDate);
        bookingCarDTO.setFromDate(fromDate);
        bookingCarDTO.setEmail(user.getEmail());
        bookingCarDTO.setName(user.getName());
        bookingCarDTO.setUserId(user.getId());
        bookingCarDTO.setCarId(car.getId());

        return bookingCarDTO;
    }

}
