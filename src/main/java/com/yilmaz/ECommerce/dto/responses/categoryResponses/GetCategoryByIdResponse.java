package com.yilmaz.ECommerce.dto.responses.categoryResponses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class GetCategoryByIdResponse {
    private Long id;
    private String name;
}
