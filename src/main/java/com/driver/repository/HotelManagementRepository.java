package com.driver.repository;


import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;

@Repository
public class HotelManagementRepository {
    HashMap<String,Hotel> hos_db = new HashMap<>();
    HashMap<Integer, User> user_db = new HashMap<>();

    HashMap<String, Booking> booking_db = new HashMap<>();

    public String add_hotel(Hotel hotel)
    {
        try {
            String hotel_name = hotel.getHotelName();

            if(hos_db.containsKey(hotel_name))
            {
                return "";
            }
            else {
                hos_db.put(hotel_name,hotel);

            }

        }
        catch (NullPointerException e)
        {
            return "";
        }
        return "SUCCESS";
    }
    public void update_hotel_details(Hotel hotel)
    {
        hos_db.put(hotel.getHotelName(),hotel);

    }
    public Integer add_user(User user)
    {

        user_db.put(user.getaadharCardNo(),user);
        return user.getaadharCardNo();
    }
    public String get_hotel_most_facility()
    {
        int max= 0;
        String hos_name="";
        for(Hotel hotel:hos_db.values())
        {
            List<Facility> temp = hotel.getFacilities();
            int size = temp.size();
            if(max<size)
            {
                max = size;
                hos_name = hotel.getHotelName();
            }
        }
        return hos_name;
    }
    public int get_room_price(Booking book)
    {
        if(book.getNoOfRooms()>hos_db.get(book.getHotelName()).getAvailableRooms())
            return -1;
        booking_db.put(book.getBookingId(),book);
        String hot_name = book.getHotelName();
        int hot_price_per_night = hos_db.get(hot_name).getPricePerNight();
        return hot_price_per_night;
    }
    public int get_bookong_make_by_person(int aadhar_id)
    {
        int count=0;
        for(Booking booking:booking_db.values())
        {
            if(booking.getBookingAadharCard()==aadhar_id)
            {
                count++;
            }
        }
        return count;
    }
    public Hotel update_facility(List<Facility> newfacility,String hot_name)
    {
        Hotel hotel = hos_db.get(hot_name);
        List<Facility> list = hotel.getFacilities();//if needed add try n catch block here
        for(Facility fac:newfacility)
        {
            if(list.contains(fac)==false)
            {
                list.add(fac);
            }
        }
        hotel.setFacilities(list);
        return hotel;
    }
}
