package com.assignment.backend_assignment3.service.impl;

import com.assignment.backend_assignment3.domain.UserAccount;
import com.assignment.backend_assignment3.dto.ApiResponseDto;
import com.assignment.backend_assignment3.dto.UserAccountDto;
import com.assignment.backend_assignment3.repository.UserAccountRepository;
import com.assignment.backend_assignment3.service.UserAccountService;
import com.assignment.backend_assignment3.service.mapstruct.UserAccountMapper;
import com.assignment.backend_assignment3.utils.JwtUtils;
import com.assignment.backend_assignment3.utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserAccountServiceImpl implements UserAccountService {


    @Autowired
    private UserAccountRepository repository;

    @Autowired
    private UserAccountMapper mapper;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public ApiResponseDto register(UserAccountDto userAccount) {

        ApiResponseDto apiResponseDto = new ApiResponseDto();
        if (userAccount == null) {
            apiResponseDto.setStatusCode("FAIL");
            apiResponseDto.setMessage("Dữ liệu không hợp lệ");
            return apiResponseDto;
        }
        if (userAccount.getEmail() == null || userAccount.getEmail().isEmpty() || userAccount.getPassword() == null || userAccount.getPassword().isEmpty()) {
            apiResponseDto.setStatusCode("FAIL");
            apiResponseDto.setMessage("Email hoặc mật khẩu không hợp lệ");
            return apiResponseDto;
        }

        try {
            UserAccount anotherUserAccount = repository.findByEmail(userAccount.getEmail()).orElse(null);
            if (anotherUserAccount == null) {
                String hashedPassword = PasswordUtils.hashPassword(userAccount.getPassword());
                UserAccount newUserAccount = mapper.toEntity(userAccount);

                newUserAccount.setPassword(hashedPassword);
                newUserAccount.setCreatedAt(LocalDateTime.now());
                UserAccount savedUserAccount = repository.save(newUserAccount);

                apiResponseDto.setStatusCode("SUCCESS");
                apiResponseDto.setMessage("Đăng ký thành công");
            } else {
                apiResponseDto.setStatusCode("FAIL");
                apiResponseDto.setMessage("Email đã tồn tại");
            }
        } catch (Exception e) {
            apiResponseDto.setStatusCode("ERROR");
            apiResponseDto.setMessage("Lỗi hệ thống");
        }
        return apiResponseDto;
    }

    @Override
    public ApiResponseDto login(UserAccountDto userAccount) {
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        if (userAccount == null) {
            apiResponseDto.setStatusCode("FAIL");
            apiResponseDto.setMessage("Dữ liệu không hợp lệ");
            apiResponseDto.setData(null);
            return apiResponseDto;
        }
        if (userAccount.getEmail() == null || userAccount.getEmail().isEmpty() || userAccount.getPassword() == null || userAccount.getPassword().isEmpty()) {
            apiResponseDto.setStatusCode("FAIL");
            apiResponseDto.setMessage("Email hoặc mật khẩu không hợp lệ");
            apiResponseDto.setData(null);
            return apiResponseDto;
        }

        try {
            UserAccount anotherUserAccount = repository.findByEmail(userAccount.getEmail()).orElse(null);
            if (anotherUserAccount != null) {
                if (PasswordUtils.verifyPassword(userAccount.getPassword(), anotherUserAccount.getPassword())) {
                    apiResponseDto.setStatusCode("SUCCESS");
                    apiResponseDto.setMessage("Đăng nhập thành công");

                    String accessToken = jwtUtils.generateUUIDToken();
                    anotherUserAccount.setAccessToken(accessToken);
                    repository.save(anotherUserAccount);

                    apiResponseDto.setData("Bearer " + accessToken);
                } else {
                    apiResponseDto.setStatusCode("FAIL");
                    apiResponseDto.setMessage("Mật khẩu không đúng");
                    apiResponseDto.setData(null);
                }
            } else {
                apiResponseDto.setStatusCode("FAIL");
                apiResponseDto.setMessage("Email không tồn tại");
                apiResponseDto.setData(null);
            }
        } catch (Exception e) {
            apiResponseDto.setStatusCode("ERROR");
            apiResponseDto.setMessage("Lỗi hệ thống");
            apiResponseDto.setData(null);
        }
        return apiResponseDto;

    }


}
