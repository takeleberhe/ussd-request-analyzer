package com.matrixtech.ussd.ussd_analyzer.entity;

import jakarta.persistence.*;
import lombok.*;

// This entity maps to the `fraud_blacklist_view` SQL view.
@Entity
@Table(name = "fraud_blacklist_view")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FraudBlacklistView {

    @Id
    private String msisdn;

    @Column(name = "voucher_variants")
    private Long voucherVariants;

    public String getDistinctVouchers() {
        return "";
    }
}

