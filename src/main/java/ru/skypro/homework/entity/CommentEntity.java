package ru.skypro.homework.entity;

import jdk.jfr.Timestamp;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comments")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;
    @Timestamp
    private LocalDateTime createdAt;
    private String text;
    @ManyToOne
    @JoinColumn(name = "ads_id")
    private AdsEntity ads;
}
