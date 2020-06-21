package com.lambdaschool.airbnbbuildweek.exceptions;

/**
 * A custom exception to be used when a resource is not but is suppose to be
 */
public class ResourceNotFoundException
    extends RuntimeException
{
    public ResourceNotFoundException(String message)
    {
        super("Error from an AirBnB Optimal Price Application " + message);
    }
}