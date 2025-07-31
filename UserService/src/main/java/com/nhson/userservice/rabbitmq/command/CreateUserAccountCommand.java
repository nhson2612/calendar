package com.nhson.userservice.rabbitmq.command;

public class CreateUserAccountCommand {
    private Long profileId;
    private String username;
    private String password;

    public CreateUserAccountCommand(Long profileId,String username, String password) {
        this.username = username;
        this.password = password;
        this.profileId = profileId;
    }

    public Long getProfileId() {return profileId;}
    public void setProfileId(Long profileId) {this.profileId = profileId;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
}
