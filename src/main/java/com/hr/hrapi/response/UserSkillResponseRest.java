package com.hr.hrapi.response;

public class UserSkillResponseRest extends ResponseRest {

    private UserSkillResponse userSkillResponse = new UserSkillResponse();

    public UserSkillResponse getUserSkillResponse() {
        return userSkillResponse;
    }

    public void setUserSkillResponse(UserSkillResponse userSkillResponse) {
        this.userSkillResponse = userSkillResponse;
    }
}
