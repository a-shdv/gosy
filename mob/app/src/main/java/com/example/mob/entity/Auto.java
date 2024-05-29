package com.example.mob.entity;

public class Auto {
    private int id;
    private String model;
    private Integer clientId; // Изменили тип с int на Integer

    public Auto() {
    }

    public Auto(String model, Integer clientId) { // Изменили тип с int на Integer
        this.model = model;
        this.clientId = clientId;
    }

    public Auto(int id, String model, Integer clientId) { // Изменили тип с int на Integer
        this.id = id;
        this.model = model;
        this.clientId = clientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getClientId() { // Изменили тип с int на Integer
        return clientId;
    }

    public void setClientId(Integer clientId) { // Изменили тип с int на Integer
        this.clientId = clientId;
    }
}
