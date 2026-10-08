package com.ganie.Train.config;

import com.ganie.Train.entity.Station;
import com.ganie.Train.entity.Train;
import com.ganie.Train.entity.TrainSchedule;
import com.ganie.Train.repo.StationRepository;
import com.ganie.Train.repo.TrainRepository;
import com.ganie.Train.repo.TrainScheduleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner  {



    private final StationRepository stationRepository;
    private final TrainRepository trainRepository;
    private final TrainScheduleRepository trainScheduleRepository;


    public DataInitializer(
            StationRepository stationRepository,
            TrainRepository trainRepository,
            TrainScheduleRepository trainScheduleRepository) {

        this.stationRepository = stationRepository;
        this.trainRepository = trainRepository;
        this.trainScheduleRepository = trainScheduleRepository;
    }

    @Override
    public void run(String... args) {
        if (trainRepository.count() > 0) {
            System.out.println("Sample data already exists. Skipping initialization.");
            return;
        }

        Station delhi = new Station(null, "New Delhi", "NDLS");
        Station mumbai = new Station(null, "Mumbai Central", "CST");
        Station kolkata = new Station(null, "Kolkata", "KOAA");
        Station chennai = new Station(null, "Chennai Central", "MAS");

        stationRepository.saveAll(
                List.of(delhi, mumbai, kolkata, chennai)
        );

        Train rajdhani = new Train(null, "Rajdhani Express", "12306", null);
        Train durunto = new Train(null, "Durunto", "12206", null);
        Train shatabdi = new Train(null, "Shatabdi Express", "12043", null);

        trainRepository.saveAll(
                List.of(rajdhani, durunto, shatabdi)
        );

        TrainSchedule sc1 =
                new TrainSchedule(null, rajdhani, delhi, mumbai, "06:00", "14:00");

        TrainSchedule sc2 =
                new TrainSchedule(null, durunto, mumbai, kolkata, "08:00", "21:00");

        TrainSchedule sc3 =
                new TrainSchedule(null, shatabdi, kolkata, chennai, "11:30", "19:00");

        trainScheduleRepository.saveAll(
                List.of(sc1, sc2, sc3)
        );

        System.out.println("Sample data inserted successfully.");
    }

    }

