package com.sportyshoes.entity;

import com.sportyshoes.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
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
    private String name;

    @Column(nullable = false, length = 100, unique = true, name = "email")
    private String email;

    private String gender;

    private String password;

    private String address;

    private String city;

    private String state;

    private String zipcode;

    @Column(name = "user_role", length = 50)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public UserEntity(String name, String email, String gender, String password, String address, String city, String state, String zipcode, UserRole userRole) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.password = password;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.userRole = userRole;
    }
}
