package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="ads")
public class AdsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity  author;
    private String image;
    private Integer price;
    private String title;
    private String description;
}
