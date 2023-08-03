package com.arbaazkhan.propertymanagement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/*
 *  Much like PropertyDTO, but where PropertyDTO interacts with client and service layer, This class will only interact with the DB
 */
//These 2 annotations tells hibernate to turn this class into a database table
@Entity
@Table(name = "PROPERTY_TABLE")
@Getter
@Setter
@NoArgsConstructor  // tells spring to generate a constructor with no parameters
public class PropertyEntity {

    @Id //lets jpa know that id is a primary key column
    @GeneratedValue(strategy = GenerationType.AUTO) //will auto generate id for the property
    private Long id;
    @Column(name = "PROPERTY_TITLE", nullable = false)  //way to give columns specific constraints
    private String title;
    private String description;
    private Double price;
    private String address;

    //Says that many properties can belong to 1 user
    @ManyToOne(fetch = FetchType.LAZY) //it will not fetch the user data while fetching Property
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserEntity userEntity;
}
