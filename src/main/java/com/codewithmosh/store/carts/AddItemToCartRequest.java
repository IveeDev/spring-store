package com.codewithmosh.store.carts;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddItemToCartRequest {
    @NotBlank
    private Long productId;
}
