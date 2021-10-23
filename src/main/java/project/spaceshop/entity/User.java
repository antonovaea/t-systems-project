package project.spaceshop.entity;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users", schema = "planetshop")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column("id")
    private int id;

    @NotNull
    @Column("user_name")
    private String userName;

    @NotNull
    @Column("user_surname")
    private String userSurname;

    @NotNull
    @Column("user_date_of_birth")
    private String userDateOfBirth;

    @Column("role")
    private String role;

    @NotNull
    @Column("phone")
    private String phone;

    @NotNull
    @Column("email")
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserAddress> userAddresses = new ArrayList<>();

    @NotNull
    @Column("password")
    private String password;

    public User() {

    }

    public User(int id, @NotNull String userName, @NotNull String userSurname, @NotNull String userDateOfBirth, String role, @NotNull String phone, @NotNull String email, List<UserAddress> userAddresses, @NotNull String password) {
        this.id = id;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userDateOfBirth = userDateOfBirth;
        this.role = role;
        this.phone = phone;
        this.email = email;
        this.userAddresses = userAddresses;
        this.password = password;
    }

    public User(int id, @NotNull String userName, @NotNull String userSurname, @NotNull String userDateOfBirth, String role, @NotNull String phone, @NotNull String email, List<UserAddress> userAddresses, List<Order> orders, @NotNull String password) {
        this.id = id;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userDateOfBirth = userDateOfBirth;
        this.role = role;
        this.phone = phone;
        this.email = email;
        this.userAddresses = userAddresses;
//        this.orders = orders;
        this.password = password;
    }

//    public List<Order> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(List<Order> orders) {
//        this.orders = orders;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(@NotNull String userName) {
        this.userName = userName;
    }


    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(@NotNull String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserDateOfBirth() {
        return userDateOfBirth;
    }

    public void setUserDateOfBirth(@NotNull String userDateOfBirth) {
        this.userDateOfBirth = userDateOfBirth;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(@NotNull String phone) {
        this.phone = phone;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    public List<UserAddress> getUserAddresses() {
        return userAddresses;
    }

    public void setUserAddresses(List<UserAddress> userAddresses) {
        this.userAddresses = userAddresses;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", userDateOfBirth='" + userDateOfBirth + '\'' +
                ", role='" + role + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", userAddresses=" + userAddresses +
                ", password='" + password + '\'' +
                '}';
    }
}
