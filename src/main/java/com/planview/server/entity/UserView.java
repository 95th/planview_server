package com.planview.server.entity;

public class UserView {
    private int id;
    private String userName;
    private String emailId;
    private String name;
    private boolean admin;
    private boolean locked;

    public UserView() {
    }

    public UserView(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.emailId = user.getEmailId();
        this.name = user.getFirstName() + " " + user.getLastName();
        this.admin = user.getRole() == UserRole.ADMIN;
        this.locked = user.isLocked();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

}
