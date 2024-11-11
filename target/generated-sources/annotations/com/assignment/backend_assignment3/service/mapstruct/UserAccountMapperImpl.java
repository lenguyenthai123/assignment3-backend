package com.assignment.backend_assignment3.service.mapstruct;

import com.assignment.backend_assignment3.domain.UserAccount;
import com.assignment.backend_assignment3.dto.UserAccountDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T02:38:21+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class UserAccountMapperImpl implements UserAccountMapper {

    @Override
    public UserAccountDto toDto(UserAccount userAccount) {
        if ( userAccount == null ) {
            return null;
        }

        UserAccountDto.UserAccountDtoBuilder userAccountDto = UserAccountDto.builder();

        userAccountDto.id( userAccount.getId() );
        userAccountDto.username( userAccount.getUsername() );
        userAccountDto.email( userAccount.getEmail() );
        userAccountDto.accessToken( userAccount.getAccessToken() );

        return userAccountDto.build();
    }

    @Override
    public UserAccount toEntity(UserAccountDto UserAccountDto) {
        if ( UserAccountDto == null ) {
            return null;
        }

        UserAccount userAccount = new UserAccount();

        userAccount.setId( UserAccountDto.getId() );
        userAccount.setUsername( UserAccountDto.getUsername() );
        userAccount.setEmail( UserAccountDto.getEmail() );
        userAccount.setPassword( UserAccountDto.getPassword() );
        userAccount.setCreatedAt( UserAccountDto.getCreatedAt() );
        userAccount.setAccessToken( UserAccountDto.getAccessToken() );

        return userAccount;
    }
}
