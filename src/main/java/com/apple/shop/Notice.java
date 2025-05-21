package com.apple.shop;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Notice {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;

        String contents;
        String title;

}
