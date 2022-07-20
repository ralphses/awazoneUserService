package net.awazone.awazoneUserService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "kyc_record"
)
public class KycRecord {

    @Id
    @SequenceGenerator(
            name = "kyc_generator",
            sequenceName = "kyc_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "kyc_generator",
            strategy = GenerationType.SEQUENCE
    )
    @Column(
            name = "kyc_id"
    )

    private Long kycId;

    @Column(
            length = 2
    )
    private Boolean status;

    private String documentType;
    private String documentImgUrl;

}
