package net.awazone.awazoneUserService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "user"
)
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "user_sequence",
            strategy = GenerationType.SEQUENCE
    )
    @Column(
            name = "user_id"
    )
    private Long id;

    @Column(
            unique = true
    )
    private String IdNumber;
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String password;
    private String emailAddress;
    private String occupation;
    private String userName;
    private LocalDate dateOfBirth;
    private String myRefererCode;
    private Boolean kycStatus;
    private Boolean locked;
    private String imageUrl;
    private String heardAboutUs;

    @Column(columnDefinition = "integer default 0")
    private Integer currentCredit;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "kyc_id",
            referencedColumnName = "kyc_id"
    )
    private KycRecord kycRecord;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "referrer",
            referencedColumnName = "user_id"
    )
    private User referer;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "location_id",
            referencedColumnName = "location_id"
    )
    private Location location;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "bank_details_id",
            referencedColumnName = "bank_details_id"
    )
    private BankDetails bankDetails;


}
