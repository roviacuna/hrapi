package com.hr.hrapi.response;

public class UserReponseRest extends ResponseRest{

    private UserResponse userResponse = new UserResponse();

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }
}
