package com.rishabh.HeaderParser.domain;

public class UserRequestHeader {

    String ipAddress;
    String language;
    String operatingSystem;

    public UserRequestHeader(String ipAddress, String language, String operatingSystem) {
        this.ipAddress = ipAddress;
        this.language = language;
        this.operatingSystem = operatingSystem;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }
}
