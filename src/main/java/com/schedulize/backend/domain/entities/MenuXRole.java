package com.schedulize.backend.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="menuxrole", schema = "core")
public class MenuXRole extends Auditable<String> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1905122041950251207L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idrole",nullable = false)
    private Long idRole;

    @Column(name = "idmenu",nullable = false)
    private Long idMenu;

    @Column(name = "status",nullable = false,columnDefinition = "CHAR(1)")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idrole", referencedColumnName = "id", nullable = false,foreignKey = @ForeignKey(name = "fk_menu_role"),updatable = false,insertable = false)
    private RoleEntity roleEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idmenu", referencedColumnName = "id", nullable = false,foreignKey = @ForeignKey(name = "fk_menu_menu"),updatable = false,insertable = false)
    private MenuEntity menuEntity;
}
