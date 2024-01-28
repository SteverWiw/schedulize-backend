package com.ajsoftware.backendjwtauth.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "apirole",schema = "ajscore")
public class ApiRole implements Serializable {
    @Serial
    private static final long serialVersionUID = 1905122041950251207L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "idapi",nullable = false)
    private Long apiId;
    @Column(name = "idrole",nullable = false)
    private Long roleId;

    @Column(name = "status",nullable = false,columnDefinition = "CHAR(1)")
    private String status;
    @Column(name = "description",nullable = false,length = 100)
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idrole",referencedColumnName = "id",nullable = false,foreignKey = @ForeignKey(name = "fk_role_roleapi"),updatable = false,insertable = false)
    private RoleEntity role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idapi",referencedColumnName = "id",nullable = false,foreignKey = @ForeignKey(name = "fk_api_roleapi"),updatable = false,insertable = false)
    private ApiInfoEntity apiInfo;
}
