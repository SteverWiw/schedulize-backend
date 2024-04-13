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
@Table(name = "documenttype",schema = "core")
public class DocumentTypeEntity extends Auditable<String> implements Serializable {
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
