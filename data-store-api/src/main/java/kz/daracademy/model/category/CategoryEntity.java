package kz.daracademy.model.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String categoryId;
    private String categoryName;
    private String orderInSorting;

}
