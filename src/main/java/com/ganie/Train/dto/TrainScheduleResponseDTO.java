package com.ganie.Train.dto;

public class TrainScheduleResponseDTO {


    private String trainName;
    private String trainNumber;

    private String source;
    private String sourceCode;

    private String destination;
    private String destinationCode;

    private String departureTime;
    private String arrivalTime;

    public String getTrainName() {
        return trainName;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getSource() {
        return source;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public String getDestination() {
        return destination;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public TrainScheduleResponseDTO(String trainName,
                                    String trainNumber,
                                    String source,
                                    String sourceCode,
                                    String destination,
                                    String destinationCode,
                                    String departureTime,
                                    String arrivalTime) {
        this.trainName = trainName;
        this.trainNumber = trainNumber;
        this.source = source;
        this.sourceCode = sourceCode;
        this.destination = destination;
        this.destinationCode = destinationCode;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
}
