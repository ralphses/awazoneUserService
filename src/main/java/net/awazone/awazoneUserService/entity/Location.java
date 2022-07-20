package net.awazone.awazoneUserService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Location {

    @Id
    @SequenceGenerator(
            name = "location_sequence",
            sequenceName = "location_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "location_sequence",
            strategy = GenerationType.SEQUENCE
    )
    @Column(
            name = "location_id"
    )
    private Long id;

    private String addressLine;
    private String district;
    private String city;
    private String state;
    private String country;


}
