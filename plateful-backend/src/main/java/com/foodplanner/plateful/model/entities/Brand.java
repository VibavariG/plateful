package com.foodplanner.plateful.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

import static com.foodplanner.plateful.utils.Constants.BRANDS;

@Table(BRANDS)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
    private UUID id;
    private String name;
}
