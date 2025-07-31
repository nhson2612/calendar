package com.nhson.authservice.application.model;

public enum Role {
    USER("user"),
    ADMIN("admin");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public static Role fromName(String name) {
        for (Role role : Role.values()) {
            if (role.name.equalsIgnoreCase(name)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No Role with name " + name + " found.");
    }
}