package kz.daracademy.model.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryResponse {
    private String categoryId;
    private String categoryName;
    private String orderInSorting;

}
