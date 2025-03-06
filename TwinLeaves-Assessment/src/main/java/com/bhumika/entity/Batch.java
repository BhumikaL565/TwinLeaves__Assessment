package com.bhumika.entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "batch_table")
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batchId")
    private Integer batchId;

    
    @Column(name = "mrp")
    private Integer mrp;

    @Column(name = "sp")
    private Integer sp;

    @Column(name = "purchasePrice")
    private Integer purchasePrice;

    @Column(name = "availableQuantity")
    private Integer availableQuantity;

    @Column(name = "inwardedOn")
    private LocalDate inwardedOn;

    @ManyToOne
    @JoinColumn(name = "gtinId", nullable = false)
    private Gtin gtin;
}