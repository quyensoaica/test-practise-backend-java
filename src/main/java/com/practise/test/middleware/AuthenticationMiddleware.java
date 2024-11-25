package com.practise.test.middleware;


import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.practise.test.model.authorization.TokenPayload;
import com.practise.test.service.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

@Component
@WebFilter("/**") // Áp dụng cho tất cả các endpoint, có thể thay đổi theo nhu cầu
public class AuthenticationMiddleware extends OncePerRequestFilter {
    @NonFinal
    protected static final String SIGNER_KEY = "uINhVKYiBr0wmzDA2ngkPkoKDl7aWxsTuINhVKYiBr0wmzDA2ngkPkoKDl7aWxsT";

    @Autowired
    private JwtTokenService jwtTokenService;

    private static final List<String> NON_SECURE_PATHS = Arrays.asList(
            "/api/auth/login",
            "/api/auth/verify-token",
            "/api/auth/register",
            "/api/auth/get-group-roles",
            "/api/provinces/get-provincies",
            "/api/member-counts/get-member-counts"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {


        // Xử lý preflight requests
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        logger.info("Request URI: " + request.getRequestURI());
        logger.info("Authorization Header: " + request.getHeader("Authorization"));
        logger.info("Cookies: " + Arrays.toString(request.getCookies()));

        // Bỏ qua các đường dẫn không yêu cầu xác thực
        if (NON_SECURE_PATHS.contains(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        // Kiểm tra token từ header hoặc cookie
        String token = getTokenFromRequest(request);
        logger.info("Token: " + token);

        if (token == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized - No token provided");
            return;
        }

        try {

            boolean isValid = verifyToken(token);
            if (!isValid) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized - Invalid token");
                return;
            }

            TokenPayload tokenPayload = decodeToken(token);

            // Lấy payload từ token
            String userId = tokenPayload.getUserId();
            String username = tokenPayload.getUsername();
            String role = tokenPayload.getRole();
            String roleName = tokenPayload.getRoleName();

            // Lưu thông tin người dùng vào request attributes
            request.setAttribute("userId", userId);
            request.setAttribute("username", username);
            request.setAttribute("role", role);
            request.setAttribute("roleName", roleName);

            // Tiếp tục với filter chain
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized - Invalid token" + e.toString());
        }
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        // Lấy token từ header Authorization hoặc cookie
        String token = null;

        // Kiểm tra header Authorization
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
        }

        // Kiểm tra cookie nếu token không có trong header
        if (token == null) {
            token = getAccessTokenFromCookie(request);  // Hoặc bạn có thể lấy từ cookies
        }

        return token;
    }

    public static String getAccessTokenFromCookie(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("accessToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null; // Không tìm thấy cookie
    }

    public static boolean verifyToken(String token) {
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            return jwsObject.verify(new MACVerifier(SIGNER_KEY.getBytes()));
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static TokenPayload decodeToken(String token) {
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            JWTClaimsSet jwtClaimsSet = JWTClaimsSet.parse(jwsObject.getPayload().toJSONObject());
            return new TokenPayload(
                    jwtClaimsSet.getStringClaim("userId"),
                    jwtClaimsSet.getStringClaim("username"),
                    jwtClaimsSet.getStringClaim("role"),
                    jwtClaimsSet.getStringClaim("roleName")
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
