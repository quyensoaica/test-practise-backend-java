package com.practise.test.service;

import com.practise.test.constants.EGroupRole;
import com.practise.test.dto.AppData.AppErrorBase;
import com.practise.test.dto.AppData.AppResponseBase;
import com.practise.test.dto.authorization.LoginReponseDTO;
import com.practise.test.dto.authorization.LoginRequestDTO;
import com.practise.test.dto.authorization.RegisterRequestDTO;
import com.practise.test.dto.authorization.RegisterResponseDTO;
import com.practise.test.dto.user.UserInfoDTO;
import com.practise.test.entity.GroupRole;
import com.practise.test.entity.User;
import com.practise.test.model.authorization.AccessToken;
import com.practise.test.model.authorization.RegisterData;
import com.practise.test.model.authorization.TokenPayload;
import com.practise.test.model.role.GroupRoleInfo;
import com.practise.test.model.user.UserInfo;
import com.practise.test.repository.GroupRoleRepository;
import com.practise.test.repository.UserRepository;
import com.practise.test.utils.PasswordHandle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticateService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRoleRepository groupRoleRepository;

    @Autowired
    private JwtTokenService jwtService;

    @Autowired
    private RoleService roleService;

    public AppResponseBase getUserByEmail(String email) {
        try {
            if (email == null || email.isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "Email is required",
                        null,
                        new AppErrorBase("Bad Request", "Email is required")
                );
            }

            User user = userRepository.findByEmail(email);

            if (user.getId() == null || user.getId().isEmpty()) {
                return new AppResponseBase(
                        404,
                        false,
                        "User not found",
                        null,
                        new AppErrorBase("Not found", "User not found")
                );
            }

            return new AppResponseBase(
                     200,
                    true,
                    "Get user by email success",
                    user,
                    null
            );
        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Error from server",
                    null,
                    new AppErrorBase("Server error", "Server error")
            );
        }
    }

    public AppResponseBase getUserByUsername(String username) {
        try {
            if (username == null || username.isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "Tên người dùng là bắt buộc",
                        null,
                        new AppErrorBase("Yêu cầu không hợp lệ", "Tên người dùng là bắt buộc")
                );
            }

             User user = userRepository.findByUsername(username);

            if (user.getId() == null || user.getId().isEmpty()) {
                return new AppResponseBase(
                        404,
                        false,
                        "Không tìm thấy người dùng",
                        null,
                        new AppErrorBase("Không tìm thấy", "Không tìm thấy người dùng")
                );
            }

            return new AppResponseBase(
                    200,
                    true,
                    "Lấy người dùng theo tên người dùng thành công",
                    user,
                    null
            );
        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Lỗi từ phía server",
                    null,
                    new AppErrorBase("Lỗi từ phía server", "Lỗi từ phía server")
            );
        }
    }

    public AppResponseBase login (LoginRequestDTO userLogin) {
        try {
            if (userLogin.getUsername() == null || userLogin.getUsername().isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "Tên đăng nhập là bắt buộc",
                        null,
                        new AppErrorBase("Yêu cầu không hợp lệ", "Tên đăng nhập là bắt buộc")
                );
            }
            if (userLogin.getPassword() == null || userLogin.getPassword().isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "Mật khẩu là bắt buộc",
                        null,
                        new AppErrorBase("Yêu cầu không hợp lệ", "Mật khẩu là bắt buộc")
                );
            }

            User user = userRepository.findByUsername(userLogin.getUsername());
            if (user.getId() == null || user.getId().isEmpty()) {
                return new AppResponseBase(
                        404,
                        false,
                        "Không tìm thấy người dùng",
                        null,
                        new AppErrorBase("Không tìm thấy", "Không tìm thấy người dùng")
                );
            }
            boolean checkPassword = PasswordHandle.verifyPassword(userLogin.getPassword(), user.getPassword());
            if (!checkPassword) {
                return new AppResponseBase(
                        400,
                        false,
                        "Mật khẩu không chính xác",
                        null,
                        new AppErrorBase("Mật khẩu không chính xác", "Mật khẩu không chính xác")
                );
            }

            AppResponseBase userPermission = roleService.getCurrentUserPermissions(user.getGroupRoleId());
            if (!userPermission.isSuccess()) {
                return userPermission;
            }
            ModelMapper modelMapper = new ModelMapper();
            UserInfo userInfo = modelMapper.map(user, UserInfo.class);

            GroupRole groupRole = user.getGroupRole();
            TokenPayload tokenPayload = new TokenPayload(
                    user.getId(),
                    user.getUsername(),
                    user.getGroupRoleId(),
                    groupRole.getName()
            );
            AccessToken token = jwtService.generateToken(tokenPayload);
            LoginReponseDTO loginReponseDTO = new LoginReponseDTO(
                    token,
                    userInfo,
                    null
            );

            return new AppResponseBase(
                    200,
                    true,
                    "Đăng nhập thành công",
                    loginReponseDTO,
                    null
            );

        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Lỗi từ phía server",
                    null,
                    new AppErrorBase("Lỗi từ phía server", "Lỗi từ phía server")
            );

        }
    }
    public AppResponseBase register(RegisterRequestDTO userRegister) {
        try {
            if (userRegister.getUsername() == null || userRegister.getUsername().isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "Tên đăng nhập là bắt buộc",
                        null,
                        new AppErrorBase("Yêu cầu không hợp lệ", "Tên đăng nhập là bắt buộc")
                );
            }
            if (userRegister.getPassword() == null || userRegister.getPassword().isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "Mật khẩu là bắt buộc",
                        null,
                        new AppErrorBase("Yêu cầu không hợp lệ", "Mật khẩu là bắt buộc")
                );
            }
            if (userRegister.getFullName() == null || userRegister.getFullName().isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "Họ tên là bắt buộc",
                        null,
                        new AppErrorBase("Yêu cầu không hợp lệ", "Họ tên là bắt buộc")
                );
            }

            User checkExits = userRepository.findByUsername(userRegister.getUsername());
            if (checkExits != null && checkExits.getId() != null && !checkExits.getId().isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "Tên đăng nhập đã tồn tại trên hệ thống",
                        null,
                        new AppErrorBase("Tên đăng nhập đã tồn tại", "Tên đăng nhập đã tồn tại trên hệ thống")
                );
            }

            GroupRole groupRole = groupRoleRepository.findById(EGroupRole.CONTESTANT.getRole()).orElseThrow(() -> new RuntimeException("Không tìm thấy quyền người dùng"));
            if (groupRole == null || groupRole.getId() == null || groupRole.getId().isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "Không tìm thấy quyền người dùng",
                        null,
                        new AppErrorBase("Không tìm thấy quyền người dùng", "Không tìm thấy quyền người dùng")
                );
            }

            RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO(
                    userRegister.getUsername(),
                    PasswordHandle.hashPassword(userRegister.getPassword()),
                    userRegister.getFullName(),
                    EGroupRole.CONTESTANT.getRole()
            );

            RegisterData registerData = new RegisterData(registerRequestDTO);

            ModelMapper modelMapper = new ModelMapper();
            User user = modelMapper.map(registerData, User.class);
            user.setGroupRole(groupRole);

            User createdUser = userRepository.save(user);
            if (createdUser.getId() == null || createdUser.getId().isEmpty()) {
                return new AppResponseBase(
                        500,
                        false,
                        "Đăng kí tài khoản không thành công",
                        null,
                        new AppErrorBase("Đăng kí tài khoản không thành công", "Đăng kí tài khoản không thành công")
                );
            }

            RegisterResponseDTO registerResponseDTO = modelMapper.map(createdUser, RegisterResponseDTO.class);

            return new AppResponseBase(
                    200,
                    true,
                    "Đăng ký người dùng thành công",
                    registerResponseDTO,
                    null
            );
        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Lỗi từ phía server",
                    null,
                    new AppErrorBase("Lỗi từ phía server", "Lỗi từ phía server" + e.toString())
            );
        }
    }

    public AppResponseBase getMe(String userId) {
        try {
            if (userId.isEmpty()) {
                return new AppResponseBase(
                        400,
                        false,
                        "Id người dùng là bắt buộc",
                        null,
                        new AppErrorBase("Yêu cầu không hợp lệ", "Id người dùng là bắt buộc")
                );
            }
            Optional<User> user = userRepository.findById(userId);
            if (user.isEmpty()) {
                return new AppResponseBase(
                        404,
                        false,
                        "Không tìm thấy người dùng",
                        null,
                        new AppErrorBase("Không tìm thấy", "Không tìm thấy người dùng")
                );
            }
            GroupRole groupRole = user.get().getGroupRole();
            UserInfoDTO userInfoDTO = new UserInfoDTO(
                    user.get().getId(),
                    user.get().getUsername(),
                    user.get().getFullName(),
                    user.get().getGroupRoleId(),
                    user.get().getPhoneNumber(),
                    user.get().getEmail(),
                    user.get().getBirthday(),
                    user.get().getGender(),
                    user.get().getAvatar(),
                    user.get().getBanner(),
                    user.get().isBlocked(),
                    user.get().isUpdated(),
                    new GroupRoleInfo(groupRole.getName(), groupRole.getDisplayName())
            );
            return new AppResponseBase(
                    200,
                    true,
                    "Lấy thông tin người dùng thành công",
                    userInfoDTO,
                    null
            );
        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Lỗi từ phía server" + e.toString(),
                    null,
                    new AppErrorBase("Lỗi từ phía server", "Lỗi từ phía server")
            );
        }
    }
}
