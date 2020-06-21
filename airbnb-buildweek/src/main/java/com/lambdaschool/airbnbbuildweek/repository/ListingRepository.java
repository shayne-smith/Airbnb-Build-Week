package com.lambdaschool.airbnbbuildweek.repository;

import com.lambdaschool.airbnbbuildweek.models.Listing;
import org.springframework.data.repository.CrudRepository;

public interface ListingRepository extends CrudRepository<Listing, Long>
{
}
