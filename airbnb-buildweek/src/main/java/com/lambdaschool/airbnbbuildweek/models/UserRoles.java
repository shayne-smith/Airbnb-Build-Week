package com.lambdaschool.airbnbbuildweek.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * The entity allowing interaction with the userroles table.
 * The join table between users and roles.
 * <p>
 * Table enforces a unique constraint of the combination of userid and roleid.
 * These two together form the primary key.
 * <p>
 * When you have a compound primary key, you must implement Serializable for Hibernate
 * When you implement Serializable you must implement equals and hash code
 */
@Entity
@Table(name = "userroles",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"userid", "roleid"})})
public class UserRoles
    extends Auditable
    implements Serializable
{
    /**
     * 1/2 of the primary key (long) for userroles.
     * Also is a foreign key into the users table
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "roles",
        allowSetters = true)
    private User user;

    /**
     * 1/2 of the primary key (long) for userroles.
     * Also is a foreign key into the roles table
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "roleid")
    @JsonIgnoreProperties(value = "users",
        allowSetters = true)
    private Role role;

    /**
     * Default constructor used primarily by the JPA.
     */
    public UserRoles()
    {
    }

    /**
     * Given the params, create a new user role combination object
     *
     * @param user The user object of this relationship
     * @param role The role object of this relationship
     */
    public UserRoles(
        User user,
        Role role)
    {
        this.user = user;
        this.role = role;
    }

    /**
     * The getter for User
     *
     * @return the complete user object associated with user role combination
     */
    public User getUser()
    {
        return user;
    }

    /**
     * Setter for user
     *
     * @param user change the user object associated with this user role combination to this one.
     */
    public void setUser(User user)
    {
        this.user = user;
    }

    /**
     * Getter for role
     *
     * @return the complete role object associated with this user role combination
     */
    public Role getRole()
    {
        return role;
    }

    /**
     * Setter for role
     *
     * @param role change role object associated with this user role combination to this one.
     */
    public void setRole(Role role)
    {
        this.role = role;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        UserRoles userRoles = (UserRoles) o;
        return getUser().equals(userRoles.getUser()) &&
            getRole().equals(userRoles.getRole());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getUser(),
            getRole());
    }
}
