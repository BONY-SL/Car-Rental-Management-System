package com.example.CarRentalManagementApplication.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class CarDTO {

    private Integer id;

    private String brand;

    private String color;

    private String name;

    private String type;

    private String transmission;

    private String description;

    private Long price;

    private Date year;

    private MultipartFile image;

    private byte[] returnedImage;
}
