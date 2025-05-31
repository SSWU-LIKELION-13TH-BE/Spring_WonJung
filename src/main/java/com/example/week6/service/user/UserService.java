package com.example.week6.service.user;

import com.example.week6.dto.user.request.UserLoginRequestDto;
import com.example.week6.dto.user.request.UserPasswordChangeRequestDto;
import com.example.week6.dto.user.request.UserSignupRequestDto;
import com.example.week6.dto.user.response.UserInfoResponseDto;
import com.example.week6.dto.user.response.UserLoginResponseDto;
import com.example.week6.entity.User;
import com.example.week6.repository.UserRepository;
import com.example.week6.security.JwtTokenProvider;
import com.example.week6.validation.apiPayload.code.ErrorStatus;
import com.example.week6.validation.apiPayload.exception.GeneralException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;          // 비밀번호 암호화
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 회원가입 (비밀번호 암호화 후 저장)
    public void signup(UserSignupRequestDto requestDto) {

        // 아이디 줃복 검사
        if (userRepository.existsByUserId(requestDto.getUserId())) {
            throw new GeneralException(ErrorStatus.USERNAME_ALREADY_EXISTS);
        }

        User user = new User();
        user.setUserId(requestDto.getUserId());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setName(requestDto.getName());
        user.setProfileImage(requestDto.getProfileImage());

        userRepository.save(user);
    }

    // 로그인 (ID/PW 검증 후 JWT 발급)
    public UserLoginResponseDto login(UserLoginRequestDto requestDto) {
        User user = userRepository.findByUserId(requestDto.getUserId())
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with ID: " + requestDto.getUserId()));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
        String token = jwtTokenProvider.createToken(user.getUserId());
        return new UserLoginResponseDto(user.getUserId(), token);
    }

    // 사용자 정보 조회
    public UserInfoResponseDto getUserInfo(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("입력하신 Id의 사용자가 존재하지 않습니다: " + userId));
        return new UserInfoResponseDto(
                user.getUserId(),
                user.getName(),
                user.getProfileImage()
        );
    }

    // 비밀번호 변경
    public void changePassword(String userId, UserPasswordChangeRequestDto requestDto) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("입력하신 Id의 사용자가 존재하지 않습니다: " + userId));

        // 현재 비밀번호 확인
        if (!passwordEncoder.matches(requestDto.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다");
        }

        // 새 비밀번호 확인
        if (!requestDto.getNewPassword().equals(requestDto.getConfirmPassword())) {
            throw new IllegalArgumentException("입력하신 비밀번호가 일치하지 않습니다.");
        }

        // 새 비밀번호 암호화 후 저장
        user.setPassword(passwordEncoder.encode(requestDto.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserId())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }
}
