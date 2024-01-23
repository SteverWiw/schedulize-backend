package com.ajsoftware.backendjwtauth.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Action",schema = "ajscore")
public class ActionEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1905122041950251207L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "actionname",nullable = false,length = 20)
    private String actionName;

    @Column(name = "status",nullable = false,columnDefinition = "CHAR(1)")
    private String status;
}
