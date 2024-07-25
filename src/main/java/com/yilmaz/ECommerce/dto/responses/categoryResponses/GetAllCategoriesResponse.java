package com.yilmaz.ECommerce.dto.responses.categoryResponses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class GetAllCategoriesResponse {
    private int id;
    private String name;

}
