package com.hr.hrapi.response;

import com.hr.hrapi.model.Skill;

import java.util.List;

public class SkillResponse {

    private List<Skill> skill;

    public List<Skill> getSkill() {
        return skill;
    }

    public void setSkill(List<Skill> skill) {
        this.skill = skill;
    }
}
