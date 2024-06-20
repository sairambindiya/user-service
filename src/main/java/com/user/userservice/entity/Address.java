package com.user.userservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="address", uniqueConstraints = {@UniqueConstraint(columnNames = {"houseNumber","address1",
"city","state", "postalCode"})})
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="houseNumber")
    private String houseNumber;
    @Column(name="address1")
    private String address1;

    private String city;
    private String state;
    @Column(name="postalCode")
    private String postalCode;

    @Column(name="userId")
    private Long userId;

}
