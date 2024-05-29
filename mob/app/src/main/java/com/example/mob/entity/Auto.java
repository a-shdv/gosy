package com.example.mob.entity;

public class Auto {
    private int id;
    private String model;
    private int clientId;

    public Auto() {
    }

    public Auto(int id, String model, int clientId) {
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

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
