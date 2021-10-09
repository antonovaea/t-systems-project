package project.spaceshop.dto;


import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto {

    @Column(name = "id_user")
    private int id;

    @NotBlank
    @Column(name = "user_name")
    private String userName;

    @NotBlank
    @Column(name = "user_surname")
    private String userSurname;

    @Email
    @NotBlank
    @Column(name = "email", unique = true)
    private String email;

    @Size(min = 11, max = 11)
    @NotBlank
    @Column(name = "phone", unique = true)
    private String phone;

    @NotBlank
    @Size(min = 5, max = 15)
    @Column(name = "user_password")
    private String password;

    public UserDto(){

    }

    public UserDto(Integer id, String userName, String userSurname, String email, String phone, String password) {
        this.id = id;
        this.userName = userName;
        this.userSurname = userSurname;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
