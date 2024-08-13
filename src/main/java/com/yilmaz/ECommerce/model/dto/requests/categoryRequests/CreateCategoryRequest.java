package com.yilmaz.ECommerce.model.dto.requests.categoryRequests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CreateCategoryRequest {
    private String name;
}
