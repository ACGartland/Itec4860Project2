package com.example.demo;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MyTasks {
    RestTemplate restTemplate = new RestTemplate();

    private String makeModel;
    private int year;
    private double retailPrice;

    @Scheduled(cron = "*/2 * * * * *")
    public void addVehicle(){
        makeModel = RandomStringUtils.randomAlphabetic(15);
        year = RandomUtils.nextInt(1986, 2018);
        retailPrice = RandomUtils.nextInt(15000, 45000);
        Vehicle newVehicle = new Vehicle(0, makeModel, year, retailPrice);

        String url = "http://localhost:8080/addVehicle";
        restTemplate.postForObject(url, newVehicle, Vehicle.class);
    }

    @Scheduled(cron = "2/7 * * * * *")
    public void deleteVehicle(){
        int deleteVehicleId = RandomUtils.nextInt(1,100);

        String url = "http://localhost:8080/deleteVehicle/" + deleteVehicleId;
        Vehicle v = restTemplate.getForObject("http://localhost:8080/getVehicle/" + deleteVehicleId, Vehicle.class);

        if(v !=null) {
            restTemplate.delete(url, Vehicle.class);
            System.out.println("Deleted " + deleteVehicleId);
        }
    }

//    @Scheduled(cron = "1/5 * * * * *")
//    public void updateVehicle(){
//        int updateVehicleId = RandomUtils.nextInt(1, 100);
//        String url = "http://localhost:8080/updateVehicle";
//        Vehicle newVehicle = new Vehicle(updateVehicleId, "Updated", 1500, 00000);
//
//        Vehicle vehicle = restTemplate.getForObject("http://localhost:8080/getVehicle/" + updateVehicleId, Vehicle.class);
//        if(vehicle != null) {
//            restTemplate.put(url, newVehicle, Vehicle.class);
//            System.out.println("Updated " + updateVehicleId);
//        }
//
//    }

}
