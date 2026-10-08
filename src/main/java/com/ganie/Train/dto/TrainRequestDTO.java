package com.ganie.Train.dto;

public class TrainRequestDTO {
    private String trainName;
    private String trainNumber;


    public TrainRequestDTO() {
    }

    public TrainRequestDTO(String trainName, String trainNumber) {
        this.trainName = trainName;
        this.trainNumber = trainNumber;
    }



    public String getTrainName() {
        return trainName;
    }

    public String getTrainNumber() {
        return trainNumber;
    }
}
