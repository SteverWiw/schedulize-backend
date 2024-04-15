package com.schedulize.backend.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="views", schema = "core")
public class ViewEntity extends Auditable<String> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1905122041950251207L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false,length = 50)
    private String name;

    @Column(name = "route",nullable = false,length = 150)
    private String route;

    @Column(name = "icon",nullable = false,length = 150)
    private String icon;

    @Column(name = "status",nullable = false,columnDefinition = "CHAR(1)")
    private String status;
}
