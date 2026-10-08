package com.ganie.Train.service;


import com.ganie.Train.dto.TrainScheduleResponseDTO;
import com.ganie.Train.entity.TrainSchedule;
import com.ganie.Train.repo.TrainScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainSearchService {

    private TrainScheduleRepository trainScheduleRepository;

    public TrainSearchService(TrainScheduleRepository trainScheduleRepository)
    {
        this.trainScheduleRepository=trainScheduleRepository;
    }



    public List<TrainScheduleResponseDTO> findByStationCode(
            String sourceCode,
            String destinationCode) {

        return trainScheduleRepository
                .findBySource_StationCodeAndDestination_StationCode(
                        sourceCode,
                        destinationCode
                )
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    private TrainScheduleResponseDTO convertToDTO(TrainSchedule schedule) {

        return new TrainScheduleResponseDTO(
                schedule.getTrain().getTrainName(),
                schedule.getTrain().getTrainNumber(),

                schedule.getSource().getStationName(),
                schedule.getSource().getStationCode(),

                schedule.getDestination().getStationName(),
                schedule.getDestination().getStationCode(),

                schedule.getDepartureTime(),
                schedule.getArrivalTime()
        );
    }


    public List<TrainScheduleResponseDTO> findByStationName(
            String sourceName,
            String destinationName) {

        return trainScheduleRepository
                .findBySource_StationNameAndDestination_StationName(
                        sourceName,
                        destinationName
                )
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

}
