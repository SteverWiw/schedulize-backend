package com.ajsoftware.backendjwtauth.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "roleaction",schema = "ajscore",uniqueConstraints = {@UniqueConstraint(name = "uk_role_action",columnNames = {"idrole","idaction"})})
public class RoleActionEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1905122041950251207L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idrole",nullable = false)
    private Long idRole;

    @Column(name = "idaction",nullable = false)
    private Long idAction;

    @Column(name = "description",nullable = false,length = 100)
    private String description;

    @Column(name = "status",nullable = false,columnDefinition = "CHAR(1)")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idrole",referencedColumnName = "id",nullable = false,foreignKey = @ForeignKey(name = "fk_role_roleaction"),updatable = false,insertable = false)
    private RoleEntity role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idaction",referencedColumnName = "id",nullable = false,foreignKey = @ForeignKey(name = "fk_action_roleaction"),updatable = false,insertable = false)
    private ActionEntity action;
}
