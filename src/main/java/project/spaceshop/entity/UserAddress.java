package project.spaceshop.entity;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
@Entity
@Table(name = "user_address", schema = "planetshop")
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_address")
    private int id;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "street")
    private String street;

    @Column(name = "house")
    private String house;

    @Column(name = "flat")
    private String flat;

    public UserAddress(){

    }

    public UserAddress(int id, User user, String country, String city, String postcode, String street, String house, String flat) {
        this.id = id;
        this.user = user;
        this.country = country;
        this.city = city;
        this.postcode = postcode;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(@NotNull String country) {
        this.country = country;
    }


    public String getCity() {
        return city;
    }

    public void setCity(@NotNull String city) {
        this.city = city;
    }


    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(@NotNull String postcode) {
        this.postcode = postcode;
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(@NotNull String street) {
        this.street = street;
    }


    public String getHouse() {
        return house;
    }

    public void setHouse(@NotNull String house) {
        this.house = house;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    @Override
    public String toString() {
        return "Address: " + "Country: " + country + " City: " + city + " Postcode: " + postcode + " Street " + street +
                " House: " + house + " Flat: " + flat;

    }

    public String toStringForOrder() {
        return "Address: " + "Country: " + country + " City: " + city + " Postcode: " + postcode + " Street " + street +
                " House: " + house + " Flat: " + flat;
    }
}
