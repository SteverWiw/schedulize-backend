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
@Table(name = "apiinfo",schema = "core",uniqueConstraints ={@UniqueConstraint(name = "uk_api_name",columnNames = {"apiname"})} )
public class ApiInfoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1905122041950251207L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "apiname",nullable = false,length = 20)
    private String apiName;

    @Column(name = "source",nullable = false,length = 200)
    private String source;

    @Column(name = "required",nullable = false,length = 2000)
    private String required;

    @Column(name = "method",nullable = false,length = 10)
    private String method;

}
