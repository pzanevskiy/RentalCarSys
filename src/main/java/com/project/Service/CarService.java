package com.project.Service;

import com.project.DB.CarDB;
import com.project.entities.Car;
import com.project.enums.CarStatus;

import java.util.ArrayList;

public class CarService {

    public Car addCar(String name,String model, String price){
        Car car=new Car();
        ArrayList<Car> cars=null;
        cars = CarDB.getCars();
        int id=cars.get(cars.size()-1).getId()+1;
        car.setId(id);
        car.setName(name);
        car.setModel(model);
        car.setPrice(Integer.parseInt(price));
        car.setStatus(CarStatus.FREE);
        CarDB.addCar(car);
        return car;
    }

    public Car getCar(int id){
        return CarDB.getCarById(id);
    }

    public void updateCar(int id, String name,String model, String price, String status){
        Car car=null;
        car= CarDB.getCarById(id);
        car.setName(name);
        car.setModel(model);
        car.setPrice(Integer.parseInt(price));
        switch (status){
            case "free": { car.setStatus(CarStatus.FREE); break; }
            case "selected": { car.setStatus(CarStatus.SELECTED); break; }
            default: { break; }
        }

        CarDB.updateCar(car);
    }

    public void deleteCar(int id){
        Car car=getCar(id);
        CarDB.removeCar(id);
    }

    public ArrayList<Car> getCars(){
        return CarDB.getCars();
    }

    public ArrayList<Car> selectCars(String value,int range){
        ArrayList<Car> cars=null;
        cars=CarDB.getCars();
        if(value!="" && range==0){
            cars=CarDB.getCarsByBrand(value);
        }
        if(value=="" && range>10){
            cars=CarDB.getCarsByRange(range);
        }
        if(value!="" && range>10){
            cars=CarDB.getCarsByBrandWithRange(value,range);
        }
        return cars;
    }

    public ArrayList<String> getCarBrands(){
        return CarDB.getDistinctCarNames();
    }
}
