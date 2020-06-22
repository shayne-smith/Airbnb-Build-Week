package com.lambdaschool.airbnbbuildweek.controllers;

import com.lambdaschool.airbnbbuildweek.models.Listing;
import com.lambdaschool.airbnbbuildweek.models.User;
import com.lambdaschool.airbnbbuildweek.services.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/listings")
public class ListingController
{
    @Autowired
    ListingService listingService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/listings",
        produces = {"application/json"})
    public ResponseEntity<?> listAllListings()
    {
        List<Listing> allListings = listingService.findAll();
        return new ResponseEntity<>(allListings,
            HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/listing/{listingId}",
        produces = {"application/json"})
    public ResponseEntity<?> getListingById(
        @PathVariable
            Long listingId)
    {
        Listing l = listingService.findListingById(listingId);
        return new ResponseEntity<>(l,
            HttpStatus.OK);
    }

    @DeleteMapping(value = "/listing/{listingId}")
    public ResponseEntity<?> deleteListingById(
        @PathVariable
            long listingId)
    {
        listingService.delete(listingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/listing/{listingId}")
    public ResponseEntity<?> updateListing(
        @PathVariable
            long listingId,
        String listingname,
        String location,
        int minnumnights,
        int maxnumguests,
        boolean petsallowed,
        int numrooms,
        int numbeds,
        double optimalPrice,
        int size,
        User user)
    {
        listingService.update(
            listingId,
            listingname,
            location,
            minnumnights,
            maxnumguests,
            petsallowed,
            numrooms,
            numbeds,
            optimalPrice,
            size,
            user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/listing/{listingId}")
    public ResponseEntity<?> addNewListing(
        @PathVariable
            long listingId,
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
        User user)
        throws
        URISyntaxException
    {
        Listing newListing = listingService.save(
            listingname,
            roomtype,
            location,
            minnumnights,
            maxnumguests,
            petsallowed,
            numrooms,
            numbeds,
            optimalPrice,
            size,
            user);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newListingURI = ServletUriComponentsBuilder.fromCurrentServletMapping()
            .path("/listings/listing/{listingId}")
            .buildAndExpand(newListing.getListingid())
            .toUri();
        responseHeaders.setLocation(newListingURI);

        return new ResponseEntity<>(null,
            responseHeaders,
            HttpStatus.CREATED);
    }

    @GetMapping(value = "/{userName}",
        produces = {"application/json"})
    public ResponseEntity<?> findListingByUserName(
        @PathVariable
            String userName)
    {
        List<Listing> theListings = listingService.findByUserName(userName);
        return new ResponseEntity<>(theListings,
            HttpStatus.OK);
    }
}
