package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Ads {
    private Integer author;
    private byte[] image;
    private Integer pk;
    private Integer price;
    private String title;
}
