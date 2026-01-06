package com.ganie.Train.service;


import com.ganie.Train.controller.TrainSearchController;
import com.ganie.Train.entity.Train;
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

    public List<TrainSchedule> findByStationCode(String sourceCode, String destinationCode) {
        return  trainScheduleRepository.
                findBySource_StationCodeAndDestination_StationCode(sourceCode,destinationCode);

    }

    public List<TrainSchedule> findByStationName(String sourceName, String destinationName) {
return trainScheduleRepository.
        findBySource_StationNameAndDestination_StationName(sourceName,destinationName);
    }
}
