package com.example.CarRentalManagementApplication.dto;

import com.example.CarRentalManagementApplication.util.BookCarStatus;
import lombok.Data;

import java.util.Date;

@Data
public class GetBookingCarDTO {

    private Integer id;

    private Date fromDate;

    private Date toDate;

    private Long days;

    private Long price;

    private BookCarStatus bookCarStatus;

    private Integer userId;

    private Integer carId;

    private String name;

    private String email;

}
