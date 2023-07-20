package ru.skypro.homework.entity;

import jdk.jfr.Timestamp;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ads")
public class AdsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    @ManyToOne
    @JoinColumn(name = "author")
    private UserEntity author;
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] image;
    private Integer price;
    private String title;
    private String description;
    @Timestamp
    private LocalDateTime createdAt;
}
