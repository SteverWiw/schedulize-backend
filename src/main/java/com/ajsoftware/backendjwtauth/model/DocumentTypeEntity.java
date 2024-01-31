package com.ajsoftware.backendjwtauth.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "documenttype",schema = "ajscore")
public class DocumentTypeEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1905122041950251207L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",length = 30,nullable = false)
    private String documentName;

    @Column(name = "description",length = 100,nullable = false)
    private String description;

    @Column(name = "status",columnDefinition = "CHAR(1)",nullable = false)
    private String status;

    @Column(name = "abbreviation",columnDefinition = "CHAR(3)",nullable = false)
    private String abbreviation;
}
