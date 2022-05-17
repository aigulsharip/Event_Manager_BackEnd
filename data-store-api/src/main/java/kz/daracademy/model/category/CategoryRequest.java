package kz.daracademy.model.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryRequest {
    private String categoryId;
    private String categoryName;
    private String orderInSorting;

}
