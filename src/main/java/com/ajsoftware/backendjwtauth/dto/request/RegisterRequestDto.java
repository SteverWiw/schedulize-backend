package com.ajsoftware.backendjwtauth.dto.request;

import com.ajsoftware.backendjwtauth.model.Role;
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
    String firstName;
    String lastName;
    String status;
    Role    role;
}
