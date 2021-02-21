package com.sportyshoes.entity;

import com.sportyshoes.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@DynamicUpdate
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @Size(min = 4, max = 30, message = "{user.name.invalid}")
    @NotEmpty(message = "Please enter name")
    private String name;

    @Column(nullable = false, length = 100, unique = true, name = "email")
    @Email(message = "{user.email.invalid}")
    @NotEmpty(message = "Please enter email")
    private String email;

    @NotEmpty(message = "Please select your Gender")
    private String gender;

    @Size(min = 4, max = 50, message = "Please create a strong password")
    @NotEmpty(message = "Please enter password")
    private String password;

    @Size(min = 4, max = 50, message = "Please enter valid address")
    @NotEmpty(message = "Please enter address")
    private String address;

    @Size(min = 4, max = 20, message = "Please provide a valid city")
    @NotEmpty(message = "Please enter city")
    private String city;

    @Size(min = 4, max = 20, message = "Please select a valid state")
    @NotEmpty(message = "Please enter state")
    private String state;

    @Size(min = 6, max = 6, message = "Please provide a valid zipcode")
    @NotEmpty(message = "Please enter zipcode")
    private String zipcode;

    @NotNull(message = "please accept the terms and conditions")
    @AssertTrue
    private Boolean terms;

    @Column(name = "user_role", length = 50)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public UserEntity(String name, String email, String gender, String password, String address, String city, String state, String zipcode, Boolean terms, UserRole userRole) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.password = password;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.terms = terms;
        this.userRole = userRole;
    }
}
