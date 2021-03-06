package com.ashang.blog.Entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 文章数据库实体表
 */

@Entity
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long articleId;

    @Column
    private Long userId;
    @Column
    private String articleTitle;

    @Column
    private String articleDescription;

    @Column
    private  String articleContent;

    @Column String articleTag;
}
