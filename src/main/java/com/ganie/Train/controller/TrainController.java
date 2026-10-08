package com.ganie.Train.controller;

import com.ganie.Train.dto.TrainRequestDTO;
import com.ganie.Train.dto.TrainResponseDTO;
import com.ganie.Train.entity.Train;
import com.ganie.Train.entity.TrainSchedule;
import com.ganie.Train.service.TrainService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trains")
public class TrainController {


    private TrainService trainService;
    public  TrainController(TrainService trainService)
    {
       this.trainService=trainService;
    }


@GetMapping
    public List<Train> getAllTrains()
    {
        return trainService.getAllTrains();
    }

    @PostMapping
    public TrainResponseDTO addTrain(@RequestBody TrainRequestDTO dto)
    {
         return trainService.addTrain(dto);
    }

}
