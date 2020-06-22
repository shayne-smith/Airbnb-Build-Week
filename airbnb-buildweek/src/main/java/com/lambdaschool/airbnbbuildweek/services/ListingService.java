package com.lambdaschool.airbnbbuildweek.services;

import com.lambdaschool.airbnbbuildweek.models.Listing;
import com.lambdaschool.airbnbbuildweek.models.User;

import java.util.List;

public interface ListingService
{
    List<Listing> findAll();

    Listing findListingById(long id);

    void delete(long id);

    Listing update(
        long listingid,
        String listingname,
        String location,
        int minnumnights,
        int maxnumguests,
        boolean petsallowed,
        int numrooms,
        int numbeds,
        double optimalPrice,
        int size,
        User user);

    Listing save(
        String listingname,
        String roomtype,
        String location,
        int minnumnights,
        int maxnumguests,
        boolean petsallowed,
        int numrooms,
        int numbeds,
        double optimalPrice,
        int size,
        User user);

    List<Listing> findByUserName(String username);
}
