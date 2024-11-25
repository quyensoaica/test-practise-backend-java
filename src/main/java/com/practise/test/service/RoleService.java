package com.practise.test.service;

import com.practise.test.dto.AppData.AppErrorBase;
import com.practise.test.dto.AppData.AppResponseBase;
import com.practise.test.entity.GroupRole;
import com.practise.test.repository.FunctionRepository;
import com.practise.test.repository.GroupRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private GroupRoleRepository groupRoleRepository;

    @Autowired
    private FunctionRepository functionRepository;

    public AppResponseBase getUserRoles(String groupRoleId) {
        try {
            Optional<GroupRole> groupRole = groupRoleRepository.findById(groupRoleId);
            if (groupRole.isEmpty()) {
                return new AppResponseBase(
                        404,
                        false,
                        "Cannot Find Group Role Id",
                        null,
                        new AppErrorBase("Cannot Find Group Role Id", "Cannot find Group Role id")
                );
            }
            return new AppResponseBase(
                    200,
                    true,
                    "Get User Group Role Success",
                    groupRole,
                    null
            );
        } catch (RuntimeException e) {
            AppResponseBase appResponseBase = new AppResponseBase(
            500,
            false,
            "Error From Server",
            null,
                new AppErrorBase("Lỗi từ phía server", "Lỗi từ phía server")
            );
            return appResponseBase;
        }
    }

    public AppResponseBase getCurrentUserPermissions(String roleId) {
        try {
            List<Object[]> userPermissions = functionRepository .getCurrentUserPermissions(roleId);

            return new AppResponseBase(
                    200,
                    true,
                    "Permissions Retrieved Successfully",
                    userPermissions,
                    null
            );
        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Error From Server",
                    null,
                    new AppErrorBase("Server Error", e.getMessage())
            );
        }
    }

    // Lấy Group Role theo groupRoleId
    public AppResponseBase getGroupRole(String groupRoleId) {
        try {
            Optional<GroupRole> groupRole = groupRoleRepository.findById(groupRoleId);
            if (groupRole.isEmpty()) {
                return new AppResponseBase(
                        404,
                        false,
                        "Group Role Not Found",
                        null,
                        new AppErrorBase("Not Found", "Group role with given ID does not exist")
                );
            }
            return new AppResponseBase(
                    200,
                    true,
                    "Group Role Retrieved Successfully",
                    groupRole,
                    null
            );
        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Error From Server",
                    null,
                    new AppErrorBase("Server Error", e.getMessage())
            );
        }
    }

    // Lấy tất cả Group Roles
    public AppResponseBase getAllGroupRoles() {
        try {
            List<GroupRole> groupRoles = groupRoleRepository.findAllActiveGroupRoles();

            if (groupRoles.isEmpty()) {
                return new AppResponseBase(
                        404,
                        false,
                        "No Group Roles Found",
                        null,
                        new AppErrorBase("Not Found", "No group roles found")
                );
            }
            return new AppResponseBase(
                    200,
                    true,
                    "Group Roles Retrieved Successfully",
                    groupRoles,
                    null
            );
        } catch (RuntimeException e) {
            return new AppResponseBase(
                    500,
                    false,
                    "Error From Server",
                    null,
                    new AppErrorBase("Server Error", e.getMessage())
            );
        }
    }
}
