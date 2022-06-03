package com.hr.hrapi.response;

import com.hr.hrapi.model.UserSkill;

import java.util.List;

public class UserSkillResponse {

    private List<UserSkill> userSkills;

    public List<UserSkill> getUserSkills() {
        return userSkills;
    }

    public void setUserSkills(List<UserSkill> userSkills) {
        this.userSkills = userSkills;
    }
}
