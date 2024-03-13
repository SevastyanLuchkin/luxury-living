package ru.luxury.living.model;

import com.poiji.annotation.ExcelCell;
import lombok.Data;

@Data
public class CatalogExcel {

    @ExcelCell(0)
    private String article;

    @ExcelCell(1)
    private String name;

    @ExcelCell(2)
    private String description;

    @ExcelCell(3)
    private String category;

    @ExcelCell(4)
    private String type;

    @ExcelCell(5)
    private String brand;

    @ExcelCell(6)
    private String collection;

    @ExcelCell(7)
    private String materials;

    @ExcelCell(8)
    private String country;

    @ExcelCell(9)
    private String volume;

    @ExcelCell(10)
    private String price;

    @ExcelCell(11)
    private String inStock;
}
