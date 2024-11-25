package com.practise.test.dto.authorization;

import com.practise.test.model.authorization.AccessToken;
import com.practise.test.model.role.FunctionByRole;
import com.practise.test.model.user.UserInfo;

import java.util.List;

public class LoginReponseDTO {
    private AccessToken accessToken;
    private UserInfo userInfo;
    private List<FunctionByRole> permissions;

    public LoginReponseDTO() {
    }

    public LoginReponseDTO(AccessToken accessToken, UserInfo userInfo, List<FunctionByRole> permissions) {
        this.accessToken = accessToken;
        this.userInfo = userInfo;
        this.permissions = permissions;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<FunctionByRole> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<FunctionByRole> permissions) {
        this.permissions = permissions;
    }
}
