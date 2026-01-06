package com.ganie.Train.controller;

import com.ganie.Train.entity.Train;
import com.ganie.Train.entity.TrainSchedule;
import com.ganie.Train.service.TrainSearchService;
import com.ganie.Train.service.TrainService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/search")
@CrossOrigin(origins = "*")
public class TrainSearchController {



    private TrainSearchService trainSearchService;

    private TrainSearchController(TrainSearchService trainSearchService)
    {
        this.trainSearchService=trainSearchService;
    }


    @GetMapping("/by-code")
    public List<TrainSchedule> findTrainByStationCode(@RequestParam String sourceCode
                                                      ,@RequestParam String destinationCode)
    {
        return trainSearchService.findByStationCode(sourceCode.toUpperCase(),destinationCode.toUpperCase());
    }


    @GetMapping("/by-name")
    public List<TrainSchedule> findTrainByStationName(@RequestParam String sourceName
            ,@RequestParam String destinationName)
    {
        return trainSearchService.findByStationName(sourceName.toUpperCase(),destinationName.toUpperCase());
    }

}
