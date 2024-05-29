package com.exam.myapplication.data.model;


public class SickUserModel {
    private final Long id;
    private final String fio;
    private final String doctorFio;
    private final Boolean isInHospital;

    public SickUserModel(Long id, String fio, String doctorFio, Boolean isInHospital) {
        this.id = id;
        this.fio = fio;
        this.doctorFio = doctorFio;
        this.isInHospital = isInHospital;
    }

    public SickUserModel(String fio, String doctorFio, Boolean isInHospital) {
        id = -1L;
        this.fio = fio;
        this.doctorFio = doctorFio;
        this.isInHospital = isInHospital;
    }

    public Long getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public String getDoctorFio() {
        return doctorFio;
    }

    public Boolean getInHospital() {
        return isInHospital;
    }

    public SickUserModel copy(Long id, String fio, String doctorFio, Boolean isInHospital) {
        return new SickUserModel(
                id == null ? this.id : id,
                fio == null ? this.fio : fio,
                doctorFio == null ? this.doctorFio : doctorFio,
                isInHospital == null ? this.isInHospital : isInHospital
        );
    }
}
