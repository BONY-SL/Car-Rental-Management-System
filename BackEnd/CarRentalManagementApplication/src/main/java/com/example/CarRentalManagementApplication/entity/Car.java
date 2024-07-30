package com.example.CarRentalManagementApplication.entity;

import com.example.CarRentalManagementApplication.dto.CarDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cars")
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String brand;

    private String color;

    private String name;

    private String type;

    private String transmission;

    private String description;

    private Long price;

    private Date year;

    @Column(columnDefinition ="LONGBLOB")
    private byte[] image;

    @OneToMany(mappedBy = "car")
    private List<BookedCar> bookedCars;


    public CarDTO getCarDTO(){

        CarDTO carDTO = new CarDTO();
        carDTO.setId(id);
        carDTO.setName(name);
        carDTO.setBrand(brand);
        carDTO.setColor(color);
        carDTO.setPrice(price);
        carDTO.setDescription(description);
        carDTO.setType(type);
        carDTO.setTransmission(transmission);
        carDTO.setYear(year.toString());
        carDTO.setReturnedImage(image);
        return carDTO;
    }

}
