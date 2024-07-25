package com.yilmaz.ECommerce.dto.requests.categoryRequests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CreateCategoryRequest {
    private String name;
}
