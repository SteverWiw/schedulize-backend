package com.ajsoftware.backendjwtauth.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="role", schema = "ajscore",uniqueConstraints = {@UniqueConstraint(name = "uk_role_name",columnNames = {"rolename"})})
public class RoleEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1905122041950251207L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rolename",nullable = false,length = 20)
    private String roleName;

    @Column(name = "description",nullable = false,length = 20)
    private String description;

    @Column(name = "status",nullable = false,columnDefinition = "CHAR(1)")
    private String status;

}
