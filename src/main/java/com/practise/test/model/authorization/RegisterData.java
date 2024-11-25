package com.practise.test.model.authorization;

import com.practise.test.dto.authorization.RegisterRequestDTO;

import java.util.UUID;

public class RegisterData extends RegisterRequestDTO {
    private String id;
    private String groupRoleId;

    public RegisterData() {
    }

    public RegisterData(String id, String groupRoleId) {
        this.id = id;
        this.groupRoleId = groupRoleId;
    }

    public RegisterData(String id, RegisterRequestDTO registerRequestDTO) {
        super(registerRequestDTO.getUsername(), registerRequestDTO.getPassword(), registerRequestDTO.getFullName(), registerRequestDTO.getRole());
        this.id = id;
        this.groupRoleId = registerRequestDTO.getRole();
    }

    public RegisterData(RegisterRequestDTO registerRequestDTO) {
        super(registerRequestDTO.getUsername(), registerRequestDTO.getPassword(), registerRequestDTO.getFullName(), registerRequestDTO.getRole());
        this.id =  UUID.randomUUID().toString();
        this.groupRoleId = registerRequestDTO.getRole();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupRoleId() {
        return groupRoleId;
    }

    public void setGroupRoleId(String groupRoleId) {
        this.groupRoleId = groupRoleId;
    }

    @Override
    public String toString() {
        return "RegisterData{" +
                "id='" + id + '\'' +
                ", groupRoleId='" + groupRoleId + '\'' +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", role='" + getRole() + '\'' +
                '}';
    }
}
