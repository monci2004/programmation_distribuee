package com.example.MonsefMLSD;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CarController {

    private final List<Car> cars = new ArrayList<>();


    public CarController() {
        // Données d'exemple en mémoire
        cars.add(new Car("11AA22", "Ferrari", 100));
        cars.add(new Car("AA11BB", "Porsche", 150));
    }

    
    @GetMapping("/cars")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> allCars() {
        return cars;
    }

    // Route : GET /cars/{plateNumber}
    @GetMapping("/cars/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Car aCar(@PathVariable("plateNumber") String plateNumber) {
        return cars.stream()
                .filter(car -> car.getPlateNumber().equalsIgnoreCase(plateNumber))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Voiture introuvable"));
    }

    @PutMapping("/cars/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Car rentCar(
            @PathVariable("plateNumber") String plateNumber,
            @RequestParam(name = "rent", defaultValue = "false") boolean rent,
            @RequestBody(required = false) RentRequest rentRequest) {

        Car car = cars.stream()
                .filter(c -> c.getPlateNumber().equalsIgnoreCase(plateNumber))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Voiture introuvable"));

        if (rent) {
            if (rentRequest == null || rentRequest.getBegin() == null || rentRequest.getEnd() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Les dates begin et end sont requises");
            }
            car.setBegin(rentRequest.getBegin());
            car.setEnd(rentRequest.getEnd());
            return car;
        }

        car.setBegin(null);
        car.setEnd(null);
        return car;
    }

    public static class RentRequest {
        private String begin;
        private String end;

        public String getBegin() {
            return begin;
        }

        public void setBegin(String begin) {
            this.begin = begin;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }
    }
}