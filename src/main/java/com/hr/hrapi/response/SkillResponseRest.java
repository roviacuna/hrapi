package com.hr.hrapi.response;

public class SkillResponseRest extends ResponseRest{

    private SkillResponse skillResponse = new SkillResponse();

    public SkillResponse getSkillResponse() {
        return skillResponse;
    }

    public void setSkillResponse(SkillResponse skillResponse) {
        this.skillResponse = skillResponse;
    }
}
