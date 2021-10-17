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

    @NotNull
    @Column(name = "country")
    private String country;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "postcode")
    private String postcode;

    @NotNull
    @Column(name = "street")
    private String street;

    @NotNull
    @Column(name = "house")
    private String house;

    @Column(name = "flat")
    private String flat;

    public UserAddress(){

    }

    public UserAddress(int id, User user, @NotNull String country, @NotNull String city, @NotNull String postcode, @NotNull String street, @NotNull String house, String flat) {
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

    @NotNull
    public String getCountry() {
        return country;
    }

    public void setCountry(@NotNull String country) {
        this.country = country;
    }

    @NotNull
    public String getCity() {
        return city;
    }

    public void setCity(@NotNull String city) {
        this.city = city;
    }

    @NotNull
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(@NotNull String postcode) {
        this.postcode = postcode;
    }

    @NotNull
    public String getStreet() {
        return street;
    }

    public void setStreet(@NotNull String street) {
        this.street = street;
    }

    @NotNull
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
        return "ClientAddress{" +
                "id=" + id +
                ", user=" + user +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", postcode='" + postcode + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", flat='" + flat + '\'' +
                '}';
    }

}
