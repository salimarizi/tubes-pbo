package com.tubes.Utility;

public final class UserSession {
    private static UserSession instance;

    private int id;
    private String name;
    private String username;
    private String role;

    private UserSession(int id, String name, String userName, String role) {
        this.id = id;
        this.name = name;
        this.username = userName;
        this.role = role;
    }

    public static UserSession getInstace() {
        return instance;
    }

    public static UserSession getInstace(int id, String name, String userName, String role) {
        if(instance == null) {
            instance = new UserSession(id, name, userName, role);
        }
        return instance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public void cleanUserSession() {
        instance = null;
        id = 0;// or null
        name = null; // or null
        username = null; // or null
        role = null; // or null
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}