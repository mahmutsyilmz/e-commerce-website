package com.yilmaz.ECommerce.model.dto.requests.categoryRequests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UpdateCategoryRequest {
    private String name;
}
