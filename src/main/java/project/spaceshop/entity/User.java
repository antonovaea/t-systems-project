package project.spaceshop.entity;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;

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

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user_address")
    private UserAddress userAddress;

    @NotNull
    @Column("password")
    private String password;

    public User(){

    }

    public User(int id, String userName, String userSurname, String userDateOfBirth, String role, String phone, String email, UserAddress userAddress, String password) {
        this.id = id;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userDateOfBirth = userDateOfBirth;
        this.role = role;
        this.phone = phone;
        this.email = email;
        this.userAddress = userAddress;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NotNull String userName) {
        this.userName = userName;
    }

    @NotNull
    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(@NotNull String userSurname) {
        this.userSurname = userSurname;
    }

    @NotNull
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

    @NotNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(@NotNull String phone) {
        this.phone = phone;
    }

    @NotNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(@NotNull UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    @NotNull
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
                ", userDateOfBirth=" + userDateOfBirth +
                ", role='" + role + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", userAddress=" + userAddress +
                ", password='" + password + '\'' +
                '}';
    }
}
