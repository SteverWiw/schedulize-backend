package com.schedulize.backend.application.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {

    String userName;
    String password;
    String status;
    Long idRole;
    Long idPerson;
}
