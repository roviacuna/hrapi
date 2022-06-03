package com.hr.hrapi.response;

import com.hr.hrapi.model.User;
import com.hr.hrapi.model.UserSkill;

import java.util.List;

public class UserSkillResponse {

    private List<UserSkill> userSkill;

    public List<UserSkill> getUserSkill() {
        return userSkill;
    }

    public void setUserSkill(List<UserSkill> userSkill) {
        this.userSkill = userSkill;
    }
}
