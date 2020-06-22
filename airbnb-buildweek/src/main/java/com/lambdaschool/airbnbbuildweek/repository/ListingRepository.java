package com.lambdaschool.airbnbbuildweek.repository;

import com.lambdaschool.airbnbbuildweek.models.Listing;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ListingRepository extends CrudRepository<Listing, Long>
{
    List<Listing> findAllByUser_Username(String name);
}
