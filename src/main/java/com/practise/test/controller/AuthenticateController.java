package com.practise.test.controller;

import com.practise.test.dto.AppData.AppResponseBase;
import com.practise.test.dto.authorization.LoginReponseDTO;
import com.practise.test.dto.authorization.LoginRequestDTO;
import com.practise.test.dto.authorization.RegisterRequestDTO;
import com.practise.test.model.authorization.AccessToken;
import com.practise.test.model.authorization.TokenPayload;
import com.practise.test.service.AuthenticateService;
import com.practise.test.service.JwtTokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticateController {
    @Autowired
    private AuthenticateService authenticateService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping("/login")
    ResponseEntity<AppResponseBase> authenticate(@RequestBody LoginRequestDTO loginRequestDTO, HttpServletResponse response) {
        AppResponseBase appResponseBase = authenticateService.login(loginRequestDTO);
        if (appResponseBase.isSuccess()) {
            LoginReponseDTO loginReponseDTO = (LoginReponseDTO) appResponseBase.getData();
            AccessToken accessToken = (AccessToken) loginReponseDTO.getAccessToken();

            // Tạo cookie chứa token
            Cookie cookie = new Cookie("accessToken", accessToken.getToken());
            cookie.setSecure(true); // Chỉ gửi qua HTTPS
            cookie.setPath("/"); // Cookie áp dụng cho toàn bộ ứng dụng
            cookie.setMaxAge(7 * 24 * 60 * 60); // Cookie có thời hạn 7 ngày

            // Gắn cookie vào phản hồi HTTP
            response.addCookie(cookie);
        }
        return ResponseEntity.status(appResponseBase.getStatus()).body(appResponseBase);
    }

    @PostMapping("/register")
    ResponseEntity<AppResponseBase> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        AppResponseBase appResponseBase = authenticateService.register(registerRequestDTO);
        return ResponseEntity.status(appResponseBase.getStatus()).body(appResponseBase);
    }

    @GetMapping("/get-me")
    ResponseEntity<AppResponseBase> getMe(@RequestAttribute("userId") String userId) {
        AppResponseBase appResponseBase = authenticateService.getMe(userId);
        return ResponseEntity.status(appResponseBase.getStatus()).body(appResponseBase);
    }

    @GetMapping("/verify-token")
    ResponseEntity<AppResponseBase> verifyToken() {
        boolean verifyToken = jwtTokenService.verifyToken("eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiY29udGVzdGFudDEiLCJpc3MiOiJkZXYuY29tIiwicm9sZU5hbWUiOiJOZ3V54buFbiBU4bqhIFF1eeG7gW4iLCJleHAiOjE3MzI0NDM5OTYsImlhdCI6MTczMjQ0MDM5NiwidXNlcklkIjoiZDE4NWZhNjAtMTRhYy00Y2FkLWE0YWMtYzQwMWU2Mjk1ZWIyIiwidXNlcm5hbWUiOiIzIn0.v_wYTxzA0jp0d3aOjP6NSW4i3ZMwztHOVArWW6VIymnugQehi5rMx8zXFRAna5T0z5lO7O73W5lVRATSW7cu5A");
        return ResponseEntity.status(200).body(new AppResponseBase(200, verifyToken, "Verify token", verifyToken, null));
    }


}
