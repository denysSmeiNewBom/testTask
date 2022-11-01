package com.example.bootuserstest.utils;

public enum Operators {
    UKR("380"),
    POL("48"),
    GER("49");

    private final String code;

    Operators(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
