package com.yilmaz.ECommerce.dto.responses.categoryResponses;

import com.yilmaz.ECommerce.model.concretes.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class GetAllCategoriesWithProducts {
    private int id;
    private String name;
    private int productCount;
    private List<String> productNames;
}
