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
public class BankDetails {

    @Id
    @SequenceGenerator(
            name = "bank_details_sequence",
            sequenceName = "bank_details_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "bank_details_sequence",
            strategy = GenerationType.SEQUENCE
    )
    @Column(name = "bank_details_id")
    private Long id;

    private String accountNumber;
    private String accountType;
    private String accountName;
    private String bankName;
}
