package kz.daracademy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")

public class Category {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String categoryId;
    private String name;
    private String orderInSorting;

}
