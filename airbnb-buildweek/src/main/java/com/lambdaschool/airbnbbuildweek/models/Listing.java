package com.lambdaschool.airbnbbuildweek.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "listings")
public class Listing extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long listingid;

    @NotNull
    @Column(nullable = false)
    private String listingname;

    @NotNull
    @Column(nullable = false)
    private String roomtype;

    @NotNull
    @Column(nullable = false)
    private String location;

    private int minnumnights;

    private int maxnumguests;

    private boolean petsallowed = false;

    private int numrooms;

    private int numbeds;

    private double optimalPrice;

    private int size;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "userid",
        nullable = false)
    @JsonIgnoreProperties(value = "listings",
        allowSetters = true)
    private User user;

    public Listing()
    {
    }

    public Listing(
        @NotNull String listingname,
        @NotNull String roomtype,
        @NotNull String location,
        int minnumnights,
        int maxnumguests,
        boolean petsallowed,
        int numrooms,
        int numbeds,
        double optimalPrice,
        int size,
        @NotNull User user)
    {
        this.listingname = listingname;
        this.roomtype = roomtype;
        this.location = location;
        this.minnumnights = minnumnights;
        this.maxnumguests = maxnumguests;
        this.petsallowed = petsallowed;
        this.numrooms = numrooms;
        this.numbeds = numbeds;
        this.optimalPrice = optimalPrice;
        this.size = size;
        this.user = user;
    }

    public long getListingid()
    {
        return listingid;
    }

    public void setListingid(long listingid)
    {
        this.listingid = listingid;
    }

    public String getListingname()
    {
        return listingname;
    }

    public void setListingname(String listingname)
    {
        this.listingname = listingname;
    }

    public String getRoomtype()
    {
        return roomtype;
    }

    public void setRoomtype(String roomtype)
    {
        this.roomtype = roomtype;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public int getMinnumnights()
    {
        return minnumnights;
    }

    public void setMinnumnights(int minnumnights)
    {
        this.minnumnights = minnumnights;
    }

    public int getMaxnumguests()
    {
        return maxnumguests;
    }

    public void setMaxnumguests(int maxnumguests)
    {
        this.maxnumguests = maxnumguests;
    }

    public boolean isPetsallowed()
    {
        return petsallowed;
    }

    public void setPetsallowed(boolean petsallowed)
    {
        this.petsallowed = petsallowed;
    }

    public int getNumrooms()
    {
        return numrooms;
    }

    public void setNumrooms(int numrooms)
    {
        this.numrooms = numrooms;
    }

    public int getNumbeds()
    {
        return numbeds;
    }

    public void setNumbeds(int numbeds)
    {
        this.numbeds = numbeds;
    }

    public double getOptimalPrice()
    {
        return optimalPrice;
    }

    public void setOptimalPrice(double optimalPrice)
    {
        this.optimalPrice = optimalPrice;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "Listing{" +
            "listingid=" + listingid +
            ", listingname='" + listingname + '\'' +
            ", roomtype='" + roomtype + '\'' +
            ", location='" + location + '\'' +
            ", minnumnights=" + minnumnights +
            ", maxnumguests=" + maxnumguests +
            ", petsallowed=" + petsallowed +
            ", numrooms=" + numrooms +
            ", numbeds=" + numbeds +
            ", optimalPrice=" + optimalPrice +
            ", size=" + size +
            ", user=" + user +
            '}';
    }
}
