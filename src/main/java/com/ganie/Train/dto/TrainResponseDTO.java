package com.ganie.Train.dto;

public class TrainResponseDTO {
    private Long id;
    private String trainName;
    private String trainNumber;


    public TrainResponseDTO() {
    }



    public TrainResponseDTO(Long id, String trainName, String trainNumber) {
        this.id = id;
        this.trainName = trainName;
        this.trainNumber = trainNumber;
    }

    public Long getId() {
        return id;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getTrainNumber() {
        return trainNumber;
    }
}
