package com.example.MonsefMLSD;

public class Car {
    private String plateNumber;
    private String brand;
    private int price;
    private String begin;
    private String end;

    // Constructeur vide obligatoire pour la sérialisation Spring/Jackson
    public Car() {}

    public Car(String plateNumber, String brand, int price) {
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.price = price;
    }

    public Car(String plateNumber, String brand, int price, String begin, String end) {
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.price = price;
        this.begin = begin;
        this.end = end;
    }

    // Getters et Setters obligatoires (Spring les utilise pour générer le JSON)
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

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