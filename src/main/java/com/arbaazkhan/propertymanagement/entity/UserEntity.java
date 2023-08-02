package com.arbaazkhan.propertymanagement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "USER_TABLE")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id //lets jpa know that id is a primary key column
    @GeneratedValue(strategy = GenerationType.AUTO) //will auto generate id for the property
    private Long id;
    private String name;
    @Column(name = "EMAIL", nullable = false)
    private String email;
    private String phone;
    private String password;
}
