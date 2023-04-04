package com.driver;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class AirportRepository {

    HashMap<String,Airport> airportDb = new HashMap<>();
    HashMap<Integer, Flight> flightDb = new HashMap<>();
    HashMap<Integer, Passenger> passengerDb = new HashMap<>();
    HashMap<Integer, List<Integer>> flightBookDb = new HashMap<>();

    public void add_airport(Airport airport)
    {
        String key = airport.getAirportName();
         airportDb.put(key,airport);
    }

    public String getLargestAirportName()
    {
        int max = 0; String maxName ="";
        for(Airport airport : airportDb.values())
        {
            if(airport.getNoOfTerminals()>max)
            {
                max = airport.getNoOfTerminals();
                maxName = airport.getAirportName();
            }
            if(airport.getNoOfTerminals()==max)
            {
                if(airport.getAirportName().compareTo(maxName)<0)
                {
                    maxName = airport.getAirportName();
                }
            }
        }

        return maxName;
    }

    public void add_flight(Flight flight)
    {
        int key = flight.getFlightId();
        flightDb.put(key,flight);
    }


    public void add_passenger(Passenger passenger)
    {
        int key = passenger.getPassengerId();
        passengerDb.put(key,passenger);
    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity)
    {
        double min = Double.MAX_VALUE;
        for(Flight flight : flightDb.values())
        {
            if(flight.getFromCity().equals(fromCity) && flight.getToCity().equals(toCity))
            {
                if(flight.getDuration()<min)
                {
                    min = flight.getDuration();
                }
            }
        }
        return min;
    }

    public String bookTicket(int flightId, int passengerId) {
          if (flightBookDb.containsKey(flightId) && flightBookDb.get(flightId).size() >= flightDb.get(flightId).getMaxCapacity())
          {
                return "FAILURE";
          }



        List<Integer> passengers = flightBookDb.get(flightId);
        if (passengers != null && passengers.contains(passengerId)) {
            return "FAILURE";
        }
        else {
            if(passengers == null){
                passengers = new ArrayList<>();
            }
            passengers.add(passengerId);
            flightBookDb.put(flightId, passengers);
            return "SUCCESS";
        }

    }

    public String cancelTicket(int flightId, int passengerId)
    {
        if (flightBookDb.containsKey(flightId)) {
            List<Integer> passengers = flightBookDb.get(flightId);
            if (passengers.contains(passengerId)) {
                passengers.remove(passengerId);
                return "SUCCESS";
            }
        }
        return "FAILURE";
    }

    public int countOfBookingByPassenger(int passengerId)
    {
        int count =0;
        for(List<Integer>id : flightBookDb.values())
        {
            for(int val : id)
            {
                if(val==passengerId)
                {
                    count++;
                }
            }
        }
        return count;
    }

    public String getAirportNameFromFlightId(int flightId)
    {

        if(!flightDb.containsKey(flightId)) return null;

        City city = flightDb.get(flightId).getFromCity();

        for(String name: airportDb.keySet())
        {
            if(airportDb.get(name).getCity().equals(city))
            {
                return name;
            }
        }

        return null;
    }


    public int calculateFlightFare(int flightId)
    {
        int price =3000;
        if(flightDb.containsKey(flightId))
        {
            price = 3000 + (flightBookDb.get(flightId).size()*50);
        }



        return price;

    }

    public int getNumberOfPeopleOn(Date date, String airportName)
    {
       City city = airportDb.get(airportName).getCity();

       List<Integer> flightIds = new ArrayList<>();

       for(int id : flightDb.keySet())
       {
           if((flightDb.get(id).getFromCity().equals(city) || flightDb.get(id).getToCity().equals(city)) && flightDb.get(id).getFlightDate().equals(date))
           {
               flightIds.add(id);
           }
       }

       int count =0;

       for(int id : flightIds)
       {
           count+= flightBookDb.get(id).size();
       }

       return count;
    }

    public int calculateRevenueOfAFlight(int flightId)
    {
        int bookedSeats = flightBookDb.get(flightId).size();
        int perSeatRevenue = 3000* bookedSeats;
        int totalRevenue = bookedSeats * perSeatRevenue;

        return totalRevenue;
    }
}
