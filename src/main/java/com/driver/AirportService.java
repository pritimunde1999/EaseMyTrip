package com.driver;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AirportService {

    AirportRepository airportRepository = new AirportRepository();

    public void add_airport(Airport airport) {
        airportRepository.add_airport(airport);
    }

    public String getLargestAirportName() {
        return airportRepository.getLargestAirportName();
    }

    public void add_flight(Flight flight)
    {
        airportRepository.add_flight(flight);
    }

    public void add_passenger(Passenger passenger)
    {
        airportRepository.add_passenger(passenger);
    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity)
    {
        return airportRepository.getShortestDurationOfPossibleBetweenTwoCities(fromCity,toCity);
    }

    public String bookTicket(int flightId, int passengerId)
    {
       return airportRepository.bookTicket(flightId,passengerId);
    }

    public String cancelTicket(int flightId, int passengerId)
    {
        return airportRepository.cancelTicket(flightId,passengerId);
    }

    public int countOfBookingByPassenger(int passengerId)
    {
        return airportRepository.countOfBookingByPassenger(passengerId);
    }

    public String getAirportNameFromFlightId(int flightId)
    {
        return airportRepository.getAirportNameFromFlightId(flightId);
    }

    public int calculateFlightFare(int flightId)
    {
        return airportRepository.calculateFlightFare(flightId);
    }

    public int getNumberOfPeopleOn(Date date, String airportName)
    {
        return airportRepository.getNumberOfPeopleOn(date,airportName);
    }

    public int calculateRevenueOfAFlight(int flightId)
    {
        return airportRepository.calculateRevenueOfAFlight(flightId);
    }
}
