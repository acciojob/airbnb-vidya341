package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.service.HotelManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/hotel")
public class HotelManagementController {

    @Autowired
    public HotelManagementService ser_obj;

    @PostMapping("/add-hotel")
    public String addHotel(@RequestBody Hotel hotel){

        //You need to add an hotel to the database
        //incase the hotelName is null or the hotel Object is null return an empty a FAILURE
        //Incase somebody is trying to add the duplicate hotelName return FAILURE
        //in all other cases return SUCCESS after successfully adding the hotel to the hotelDb.
        String ans = ser_obj.add_hotel(hotel);
        return ans;

    }

    @PostMapping("/add-user")
    public Integer addUser(@RequestBody User user){

        //You need to add a User Object to the database
        //Assume that user will always be a valid user and return the aadharCardNo of the user
        Integer ans = ser_obj.add_user(user);
       return ans;
    }

    @GetMapping("/get-hotel-with-most-facilities")
    public String getHotelWithMostFacilities(){

        //Out of all the hotels we have added so far, we need to find the hotelName with most no of facilities
        //Incase there is a tie return the lexicographically smaller hotelName
        //Incase there is not even a single hotel with atleast 1 facility return "" (empty string)
        String ans = ser_obj.get_hotelname_mostfacility();

        return ans;
    }

    @PostMapping("/book-a-room")
    public int bookARoom(@RequestBody Booking booking){

        //The booking object coming from postman will have all the attributes except bookingId and amountToBePaid;
        //Have bookingId as a random UUID generated String
        //save the booking Entity and keep the bookingId as a primary key
        //byCalculate the total amount paid  the person based on no. of rooms booked and price of the room per night.
        //If there arent enough rooms available in the hotel that we are trying to book return -1 
        //in other case return total amount paid
        int amount = ser_obj.get_priceofroom(booking);
        
        return amount;
    }
    
    @GetMapping("/get-bookings-by-a-person/{aadharCard}")
    public int getBookings(@PathVariable("aadharCard")Integer aadharCard)
    {
        //In this function return the bookings done by a person
        int count = ser_obj.get_booking_by_person(aadharCard);
        return count;
    }

    @PutMapping("/update-facilities")
    public Hotel updateFacilities(List<Facility> newFacilities,String hotelName){

        //We are having a new facilites that a hotel is planning to bring.
        //If the hotel is already having that facility ignore that facility otherwise add that facility in the hotelDb
        //return the final updated List of facilities and also update that in your hotelDb
        //Note that newFacilities can also have duplicate facilities possible
        Hotel new_hotel = ser_obj.update_facility(newFacilities,hotelName);
        return new_hotel;
    }

}
