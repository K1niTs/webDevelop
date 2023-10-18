package com.example.webdevelop.webDevelop.Models;

import jakarta.persistence.*;
@MappedSuperclass
public abstract class BaseEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        protected int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }


