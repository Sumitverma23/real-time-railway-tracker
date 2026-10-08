package com.ganie.Train.service;


import com.ganie.Train.dto.TrainRequestDTO;
import com.ganie.Train.dto.TrainResponseDTO;
import com.ganie.Train.entity.Train;
import com.ganie.Train.repo.TrainRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {
    private TrainRepository trainRepository;

    public TrainService(TrainRepository trainRepository)
    {
        this.trainRepository=trainRepository;
    }



    private Train convertToEntity(TrainRequestDTO dto) {

        Train train = new Train();

        train.setTrainName(dto.getTrainName());
        train.setTrainNumber(dto.getTrainNumber());

        return train;
    }
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    public TrainResponseDTO addTrain(TrainRequestDTO dto) {

        Train train = convertToEntity(dto);

        Train savedTrain=trainRepository.save(train);
        return convertToResponseDTO(savedTrain);

    }



    private TrainResponseDTO convertToResponseDTO(Train train) {

        return new TrainResponseDTO(
                train.getId(),
                train.getTrainName(),
                train.getTrainNumber()
        );
    }
}

