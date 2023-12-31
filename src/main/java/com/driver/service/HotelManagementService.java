package com.driver.service;


import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.repository.HotelManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Service
public class HotelManagementService {

    HotelManagementRepository repo_obj = new HotelManagementRepository();
    HashSet<Integer> room_id = new HashSet<>();
    public int generate_random_id()
    {
        Random rand = new Random();
        int id = rand.nextInt(1000);
        while(room_id.contains(id))
        {
            id = rand.nextInt();
        }
        return id;

    }
    public String add_hotel(Hotel hotel)
    {
        try{
            if(hotel.getHotelName().equals(""))
                return "FAILURE";
            String ans = repo_obj.add_hotel(hotel);
            return ans;
        }
        catch (NullPointerException e)
        {
            return "FAILURE";
        }


    }
    public Integer add_user(User user)
    {
        Integer ans = repo_obj.add_user(user);
        return ans;
    }
    public String get_hotelname_mostfacility()
    {
        String ans = repo_obj.get_hotel_most_facility();
        return ans;
    }
    public int get_priceofroom(Booking book)
    {
        int no_of_rooms = book.getNoOfRooms();
        int id = generate_random_id();
        int price_per_night = repo_obj.get_room_price(book,id+"");
        if(price_per_night==-1)
            return -1;
        int amount_to_be_pair = no_of_rooms*price_per_night;
        return amount_to_be_pair;

    }
    public int get_booking_by_person(int aadharid)
    {
        int ans = repo_obj.get_bookong_make_by_person(aadharid);
        return ans;
    }
    public Hotel update_facility(List<Facility> newfacility,String hot_name)
    {
        Hotel new_hotel_obj = repo_obj.update_facility(newfacility,hot_name);
        repo_obj.update_hotel_details(new_hotel_obj);
        return new_hotel_obj;



    }

}
