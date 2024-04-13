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
@Table(name="menu", schema = "core",uniqueConstraints = {@UniqueConstraint(name = "uk_menu_name",columnNames = {"name"})})
public class MenuEntity extends Auditable<String> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1905122041950251207L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false,length = 50)
    private String name;

    @Column(name = "isparent",nullable = false,columnDefinition = "CHAR(1)")
    private String isParent;

    @Column(name = "idview", columnDefinition = "BIGINT")
    private Long idView;

    @Column(name = "status",nullable = false,columnDefinition = "CHAR(1)")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idview", referencedColumnName = "id", nullable = false,foreignKey = @ForeignKey(name = "fk_menu_view"),updatable = false,insertable = false)
    private ViewEntity viewEntity;
}
