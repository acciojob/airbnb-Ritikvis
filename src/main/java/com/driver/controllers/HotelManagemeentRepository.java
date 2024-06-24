package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;

import java.util.*;


public class HotelManagemeentRepository {
    Map<String, Hotel> hotelDb = new HashMap<>();
    Map<Integer, User> userDb = new HashMap<>();
    Map<Integer, List<Booking>> bookingDb = new HashMap<>();
    public String addHotel(Hotel hotel) {
        String hotelName = hotel.getHotelName();
        if(hotelDb.containsKey(hotelName) || hotelName==null){
           return "FAILURE";
        }
        else{
            hotelDb.put(hotelName,hotel);
            return "SUCCESS";
        }
    }


    public String getHotelWithMostFacilities() {
        int facilities = 0;

        String hotelName = "";

        for (Hotel hotel : hotelDb.values()) {

            if (hotel.getFacilities().size() > facilities) {
                facilities = hotel.getFacilities().size();
                hotelName = hotel.getHotelName();
            } else if (hotel.getFacilities().size() == facilities) {
                if (hotel.getHotelName().compareTo(hotelName) < 0) {
                    hotelName = hotel.getHotelName();
                }
            }
        }
        return hotelName;
    }
    public int bookARoom(Booking booking) {
        if(hotelDb.containsKey(booking.getHotelName())){
            Hotel hotel = hotelDb.get(booking.getHotelName());
            if(hotel.getAvailableRooms() >= booking.getNoOfRooms()){
                booking.setBookingId(String.valueOf(UUID.randomUUID()));
                booking.setAmountToBePaid(hotel.getPricePerNight() * booking.getNoOfRooms());
                hotel.setAvailableRooms(hotel.getAvailableRooms() - booking.getNoOfRooms());
                hotelDb.put(hotel.getHotelName(), hotel);
                if(bookingDb.containsKey(booking.getBookingAadharCard())){
                    List<Booking> bookingList = bookingDb.get(booking.getBookingAadharCard());
                    bookingList.add(booking);
                    bookingDb.put(booking.getBookingAadharCard(),bookingList);
                } else{
                    List<Booking> bookingList = new ArrayList<>();
                    bookingList.add(booking);
                    bookingDb.put(booking.getBookingAadharCard(),bookingList);
                }
                return booking.getAmountToBePaid();
            } else {
                return -1;
            }
        }
        return -1;
    }

    public int getBookings(Integer aadharCard) {
        if(bookingDb.containsKey(aadharCard))
            return bookingDb.get(aadharCard).size();
        else
            return 0;
    }
    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName){
        List<Facility> oldFacilities = hotelDb.get(hotelName).getFacilities();

        for(Facility facility: newFacilities){

            if(oldFacilities.contains(facility)){
                continue;
            }else{
                oldFacilities.add(facility);
            }
        }

        Hotel hotel = hotelDb.get(hotelName);
        hotel.setFacilities(oldFacilities);

        hotelDb.put(hotelName,hotel);

        return hotel;
    }


    public Integer addUser(com.driver.model.User user) {
        int addharNumber = user.getaadharCardNo();
        userDb.put(addharNumber,user);
        return addharNumber;
    }
}
