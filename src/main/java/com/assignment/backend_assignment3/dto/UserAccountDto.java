package com.assignment.backend_assignment3.dto;


import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserAccountDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private LocalDateTime createdAt;


}
