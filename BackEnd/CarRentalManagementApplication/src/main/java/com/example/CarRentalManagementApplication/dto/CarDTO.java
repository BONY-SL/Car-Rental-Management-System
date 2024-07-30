package com.example.CarRentalManagementApplication.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


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

    private String  year;

    private MultipartFile image;

    private byte[] returnedImage;
}
