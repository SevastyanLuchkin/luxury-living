package ru.luxury.living.model;

import lombok.Data;

@Data
public class ProjectRequest {

    private String title;

    private String description;

    private Long imageId;

    private Long[] imageIds;
}
