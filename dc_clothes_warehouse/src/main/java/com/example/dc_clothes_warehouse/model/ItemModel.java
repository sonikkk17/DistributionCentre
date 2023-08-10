package com.example.dc_clothes_warehouse.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class ItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String name;
    private FashionBrand brand;
    @Min(2022)
    private int yearOfCreation;
    @Min(value = 1001, message = "Price must be higher than 1000")
    private double price;
    private int quantity;
    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

    public ItemModel() {
        this.createdAt = LocalDateTime.now();
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "distribution_centre_id", nullable = false)
    private DistributionCentre distributionCentre;

    public enum FashionBrand {
        BALENCIAGA("Balenciaga"), STONE_ISLAND("Stone Island"), DIOR("Dior"), GUCCI("Gucci"), VERSACE("Versace"), LOUIS_VUITTON("Louis Vuitton");

        private String brandName;

        private FashionBrand(String brandName) {
            this.brandName = brandName;
        }

        public String getBrandName() {
            return brandName;
        }

    }
}
