package com.practise.test.service;

import com.practise.test.dto.UserDTO;
import com.practise.test.entity.GroupRole;
import com.practise.test.entity.User;
import com.practise.test.repository.GroupRoleRepository;
import com.practise.test.repository.UserRepository;
import com.practise.test.utils.PasswordHandle;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.practise.test.utils.PasswordHandle.hashPassword;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRoleRepository groupRoleRepository;


//    public UserDTO createUser(UserDTO userDTO) {
////        User user = UserMapper.mapToUser(userDTO);
////        User newUser = userRepository.save(user);
////        return UserMapper.mapToUserDTO(newUser);
//        return userDTO;
//    }


    public Map<String, Object> getAllUsers(int page, int limit, String search) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("createdAt").descending());
        Page<User> usersPage = userRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));

            if (search != null && !search.isEmpty()) {
                Predicate fullNamePredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("fullName")), "%" + search.toLowerCase() + "%");
                Predicate emailPredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("email")), "%" + search.toLowerCase() + "%");
                predicates.add(criteriaBuilder.or(fullNamePredicate, emailPredicate));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);

        List<Map<String, Object>> users = usersPage.getContent().stream().map(user -> {
            Map<String, Object> userData = new LinkedHashMap<>();
            userData.put("id", user.getId());
            userData.put("email", user.getEmail());
            userData.put("username", user.getUsername());
            userData.put("password", user.getPassword());
            userData.put("fullName", user.getFullName());
            userData.put("groupRoleId", user.getGroupRoleId());
            userData.put("phoneNumber", user.getPhoneNumber());
            userData.put("birthday", user.getBirthday());
            userData.put("gender", user.getGender());
            userData.put("avatar", user.getAvatar());
            userData.put("banner", user.getBanner());
            userData.put("isBlocked", user.isBlocked());
            userData.put("isDeleted", user.isDeleted());
            userData.put("isUpdated", user.isUpdated());
            userData.put("createdAt", user.getCreatedAt());
            userData.put("updatedAt", user.getUpdatedAt());

            Map<String, String> groupRole = new HashMap<>();
            groupRole.put("name", user.getGroupRole().getName());
            groupRole.put("displayName", user.getGroupRole().getDisplayName());
            userData.put("groupRole", groupRole);

            return userData;
        }).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("users", users);
        response.put("page", page);
        response.put("limit", limit);
        response.put("totalItem", usersPage.getTotalElements());
        response.put("totalPage", usersPage.getTotalPages());

        return response;
    }

    public Map<String, Object> createUser(User user) {
        Map<String, Object> response = new HashMap<>();
    try
    {
        // Kiểm tra tài khoản đã tồn tại
        Optional<User> existingUser = Optional.ofNullable(userRepository.findByUsername(user.getUsername()));
        if (existingUser.isPresent()) {
            response.put("status", 409);
            response.put("success", false);
            response.put("message", "Tài khoản đã tồn tại trên hệ thống");
            response.put("data", null);
            response.put("error", "Conflict");
            return response;
        }

        // Kiểm tra phân quyền
        Optional<GroupRole> groupRole = groupRoleRepository.findById(user.getGroupRoleId());
        if (!groupRole.isPresent()) {
            response.put("status", 400);
            response.put("success", false);
            response.put("message", "Phân quyền không tồn tại trên hệ thống");
            response.put("data", null);
            response.put("error", "Phân quyền không tồn tại");
            return response;
        }

        // Tạo ID mới và lưu người dùng
        user.setId(UUID.randomUUID().toString());
        user.setGroupRole(groupRole.get());  // Gán thông tin phân quyền
        user.setPassword(PasswordHandle.hashPassword(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        // Lưu vào DB
        User createdUser = userRepository.save(user);

        Map<String, Object> userData = new HashMap<>();

        userData.put("id", user.getId());
        userData.put("username", user.getUsername());
        userData.put("email", user.getEmail());
        userData.put("fullName", user.getFullName());
        userData.put("groupRoleId", user.getGroupRoleId());
        userData.put("phoneNumber", user.getPhoneNumber());
        userData.put("birthday", user.getBirthday());
        userData.put("gender", user.getGender());
        userData.put("avatar", user.getAvatar());
        userData.put("banner", user.getBanner());
        userData.put("isBlocked", user.isBlocked());
        userData.put("isDeleted", user.isDeleted());
        userData.put("isUpdated", user.isUpdated());
        userData.put("createdAt", user.getCreatedAt());
        userData.put("updatedAt", user.getUpdatedAt());

        // Thêm thông tin groupRole vào
        Map<String, Object> groupRoleData = new HashMap<>();
        groupRoleData.put("name", groupRole.get().getName());
        groupRoleData.put("displayName", groupRole.get().getDisplayName());
        userData.put("groupRole", groupRoleData);
        response.put("status", 200);
        response.put("success", true);
        response.put("message", null);
        response.put("data", userData);
        response.put("error", null);
        return response;
    }
        catch (Exception e) {
        response.put("status", 500);
        response.put("success", false);
        response.put("message", "Lỗi them moi  người dùng");
        response.put("data", null);
        response.put("error", e.getMessage());
        return response;
    }
    }

    public Map<String, Object> updateUser(String userId, User data1) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<GroupRole> groupRole = groupRoleRepository.findById(data1.getGroupRoleId());

            // Kiểm tra phân quyền
            if (!groupRole.isPresent()) {
                response.put("status", 400);
                response.put("success", false);
                response.put("message", "Phân quyền không tồn tại");
                response.put("data", null);
                response.put("error", "Phân quyền bạn chọn không tồn tại trên hệ thống");
                return response;
            }

            // Tìm người dùng cần cập nhật
            Optional<User> existingUser = userRepository.findById(userId);
            if (!existingUser.isPresent()) {
                response.put("status", 404);
                response.put("success", false);
                response.put("message", "Người dùng không tồn tại");
                response.put("data", null);
                response.put("error", "Người dùng không tồn tại trên hệ thống");
                return response;
            }

            User users = existingUser.get();
            // Cập nhật thông tin người dùng
            users.setFullName(data1.getFullName());
            users.setAvatar(data1.getAvatar());
            users.setEmail(data1.getEmail());
            users.setPhoneNumber(data1.getPhoneNumber());
            users.setBirthday(data1.getBirthday());
            users.setGender(data1.getGender());
            users.setGroupRoleId(data1.getGroupRoleId());

            // Lưu thông tin người dùng đã được cập nhật
            userRepository.save(users);
            // Trả về thông tin người dùng đã cập nhật
            Map<String, Object> userData = new HashMap<>();

            userData.put("id", users.getId());
            userData.put("username", users.getUsername());
            userData.put("fullName", users.getFullName());
            userData.put("groupRoleId", users.getGroupRoleId());
            userData.put("phoneNumber", users.getPhoneNumber());
            userData.put("birthday", users.getBirthday());
            userData.put("gender", users.getGender());
            userData.put("avatar", users.getAvatar());
            userData.put("banner", users.getBanner());
            userData.put("isBlocked", users.isBlocked());
            userData.put("isDeleted", users.isDeleted());
            userData.put("isUpdated", users.isUpdated());
            userData.put("createdAt", users.getCreatedAt());
            userData.put("updatedAt", users.getUpdatedAt());

            // Thêm thông tin groupRole vào
            Map<String, Object> groupRoleData = new HashMap<>();
            groupRoleData.put("name", groupRole.get().getName());
            groupRoleData.put("displayName", groupRole.get().getDisplayName());
            userData.put("groupRole", groupRoleData);
            response.put("status", 200);
            response.put("success", true);
            response.put("message", null);
            response.put("data", userData);
            response.put("error", null);
            return response;

        }
        catch (Exception e) {
            response.put("status", 500);
            response.put("success", false);
            response.put("message", "Lỗi cập nhật người dùng");
            response.put("data", null);
            response.put("error", e.getMessage());
            return response;
        }
    }

    public Map<String, Object> deleteUser(String userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Kiểm tra sự tồn tại của người dùng
            Optional<User> existingUser = userRepository.findById(userId);
            if (!existingUser.isPresent()) {
                response.put("status", 404);
                response.put("success", false);
                response.put("message", "Không tìm thấy người dùng hoặc người dùng đã bị xóa");
                response.put("data", null);
                response.put("error", "Không tìm thấy người dùng hoặc người dùng đã bị xóa");
                return response;
            }

            // Kiểm tra xem người dùng đã bị xóa hay chưa
            User user = existingUser.get();
            if (user.isDeleted()) {
                response.put("status", 400);
                response.put("success", false);
                response.put("message", "Người dùng đã bị xóa");
                response.put("data", null);
                response.put("error", "Người dùng đã bị xóa");
                return response;
            }

            // Đánh dấu người dùng là đã xóa
            user.setDeleted(true);
            userRepository.save(user);

            // Trả về thông tin người dùng đã bị xóa
            Map<String, Object> userData = new HashMap<>();
            userData.put("message", "Xóa người dùng thành công");
            userData.put("userId", userId);

            response.put("status", 200);
            response.put("success", true);
            response.put("message", null);
            response.put("data", userData);
            response.put("error", null);

            return response;

        } catch (Exception e) {
            response.put("status", 500);
            response.put("success", false);
            response.put("message", "Lỗi khi xóa người dùng");
            response.put("data", null);
            response.put("error", e.getMessage());
            return response;
        }
    }
}
