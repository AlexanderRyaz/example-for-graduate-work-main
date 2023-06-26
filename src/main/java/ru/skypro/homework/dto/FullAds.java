package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FullAds {
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private byte[] image;
    private String phone;
    private Integer pk;
    private Integer price;
    private String title;
}
