package com.ajsoftware.backendjwtauth.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "person",schema = "ajscore")
public class PersonEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1905122041950251207L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "documenttypeid",nullable = false)
    private Long documentTypeId;

    @Column(name = "documentid",nullable = false,columnDefinition = "CHAR(20)")
    private String documentId;

    @Column(name = "firstname",length = 50,nullable = false)
    private String firstName;

    @Column(name = "secondname",length = 50)
    private String secondName;

    @Column(name = "firstsurname",length = 50,nullable = false)
    private String firstSurname;

    @Column(name = "secondsurname",length = 50)
    private String secondSurname;

    @Column(name = "email",length = 50,nullable = false)
    private String email;

    @Column(name = "phonenumber")
    private Long phoneNumber;

    @Column(name = "address")
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "documenttypeid", nullable = false,foreignKey = @ForeignKey(name = "fk_document_type"),updatable = false,insertable = false)
    private DocumentTypeEntity documentType;
}
