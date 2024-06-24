package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;


import java.util.List;

public class HotelManagementService {
    HotelManagemeentRepository Repository = new HotelManagemeentRepository();
    public String addHotel(Hotel hotel) {
        return Repository.addHotel(hotel);
    }



    public String getHotelWithMostFacilities() {
        return Repository.getHotelWithMostFacilities();
    }

    public int bookARoom(Booking booking) {
        return Repository.bookARoom(booking);
    }

    public int getBookings(Integer aadharCard) {
        return Repository.getBookings(aadharCard);
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        return Repository.updateFacilities(newFacilities,hotelName);
    }

    public Integer addUser(User user) {
        return Repository.addUser(user);
    }
}
