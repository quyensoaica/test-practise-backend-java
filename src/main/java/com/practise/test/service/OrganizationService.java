package com.practise.test.service;

import com.practise.test.entity.Organization;
import com.practise.test.entity.ExamSchedule;
import com.practise.test.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import jakarta.persistence.criteria.Predicate;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;


    public Map<String, Object> getAllOrganizations() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<Organization> organizations = organizationRepository.findAll();

            List<Map<String, Object>> orgdata = organizations.stream()
                    .map(org -> {
                Map<String, Object> data = new LinkedHashMap<>();
                        data.put("id", org.getId());
                        data.put("name", org.getName());
                        data.put("description", org.getDescription());
                        data.put("createdAt", org.getCreatedAt());
                        data.put("updatedAt", org.getUpdatedAt());
                return data;
            }).collect(Collectors.toList());

            response.put("data", orgdata);
            response.put("error", null);
            response.put("message", "Get all organizations successfully");
            response.put("success", true);
            response.put("status", 200);

        } catch (Exception e) {
            response.put("data", null);
            response.put("message", "Internal server error");
            response.put("success", false);
            response.put("error", e.getMessage());
            response.put("status", 500);
        }

        return response;
    }

    public Map<String, Object> createOrganization(Organization data) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Kiểm tra tên tổ chức
            if (data.getName() == null || data.getName().isEmpty()) {
                response.put("data", null);
                response.put("message", "Name is required");
                response.put("success", false);
                response.put("error", Map.of("message", "Name is required", "errorDetail", "Name is required"));
                response.put("status", HttpStatus.BAD_REQUEST.value());
                return response;
            }

            // Tạo ID mới
            data.setId(UUID.randomUUID().toString());
            // Lưu tổ chức vào cơ sở dữ liệu
            Organization organization = organizationRepository.save(data);

            // Tìm lại tổ chức đã lưu
            Optional<Organization> createdOrganization = organizationRepository.findById(data.getId());
            if (!createdOrganization.isPresent()) {
                response.put("data", null);
                response.put("message", "Create organization failed");
                response.put("success", false);
                response.put("error", Map.of("message", "Create organization failed", "errorDetail", "Create organization failed"));
                response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
                return response;
            }

            // Trả về tổ chức đã tạo thành công
            Map<String, Object> orgData = new HashMap<>();
            orgData.put("id", organization.getId());
            orgData.put("name", organization.getName());
            orgData.put("description", organization.getDescription());
            orgData.put("createdAt", organization.getCreatedAt());
            orgData.put("updatedAt", organization.getUpdatedAt());

            response.put("status", 201);
            response.put("success", true);
            response.put("message", "Create organ successfully");
            response.put("data", orgData);
            response.put("error", null);
            return response;

        } catch (Exception e) {
            response.put("data", null);
            response.put("message", "Internal Server Error");
            response.put("success", false);
            response.put("error", Map.of("message", e.getMessage(), "errorDetail", e.getMessage()));
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return response;
    }

    public Map<String, Object> updateOrganization(String orgId, Organization data) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Tìm tổ chức cần cập nhật
            Optional<Organization> existingOrg = organizationRepository.findById(orgId);
            if (!existingOrg.isPresent()) {
                response.put("status", 404);
                response.put("success", false);
                response.put("message", "Tổ chức không tồn tại");
                response.put("data", null);
                response.put("error", "Tổ chức không tồn tại trên hệ thống");
                return response;
            }

            Organization organization = existingOrg.get();
            // Cập nhật thông tin tổ chức
            organization.setName(data.getName());
            organization.setDescription(data.getDescription());
            organization.setUpdatedAt(LocalDateTime.now());

            // Lưu thông tin tổ chức đã được cập nhật
            organizationRepository.save(organization);

            // Trả về thông tin tổ chức đã cập nhật
            Map<String, Object> orgData = new HashMap<>();
            orgData.put("id", organization.getId());
            orgData.put("name", organization.getName());
            orgData.put("description", organization.getDescription());
            orgData.put("createdAt", organization.getCreatedAt());
            orgData.put("updatedAt", organization.getUpdatedAt());

            response.put("status", 200);
            response.put("success", true);
            response.put("message", null);
            response.put("data", orgData);
            response.put("error", null);
            return response;

        } catch (Exception e) {
            response.put("status", 500);
            response.put("success", false);
            response.put("message", "Lỗi cập nhật tổ chức");
            response.put("data", null);
            response.put("error", e.getMessage());
            return response;
        }
    }

    public Map<String, Object> deleteOrganization(String organizationId) {
        Map<String, Object> response = new HashMap<>();
        try {

        // Kiểm tra xem organizationId có hợp lệ không
        if (organizationId == null || organizationId.isEmpty()) {
            response.put("data", null);
            response.put("message", "Id is required");
            response.put("success", false);
            response.put("error", Map.of("message", "Id is required", "errorDetail", "Id is required"));
            response.put("status", 400);
            return response;
        }

        // Tìm tổ chức theo ID
        Optional<Organization> organizationOpt = organizationRepository.findById(organizationId);

        if (!organizationOpt.isPresent()) {
            response.put("data", null);
            response.put("message", "Organization not found");
            response.put("success", false);
            response.put("error", Map.of("message", "Organization not found", "errorDetail", "Organization not found"));
            response.put("status", 404);
            return response;
        }

        // Xóa tổ chức khỏi cơ sở dữ liệu
        organizationRepository.deleteById(organizationId);

        response.put("data", Map.of("id", organizationId));
        response.put("message", "Xoa org thanh cong  ");
        response.put("success", true);
        response.put("error", null);
        response.put("status", 200);
        return response;
        } catch (Exception e) {
            response.put("status", 500);
            response.put("success", false);
            response.put("message", "Lỗi khi xóa org ");
            response.put("data", null);
            response.put("error", e.getMessage());
            return response;
        }
    }
}
