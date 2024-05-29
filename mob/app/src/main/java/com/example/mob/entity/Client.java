package com.example.mob.entity;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private int id;
    private String name;
    private final List<Auto> autos = new ArrayList<>();

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Auto> getAutos() {
        return autos;
    }
}
