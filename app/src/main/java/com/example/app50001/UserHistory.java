package com.example.app50001;

public class UserHistory {
    public String Email, address, displayName;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public UserHistory(String email, String address, String displayName) {
        Email = email;
        this.address = address;
        this.displayName = displayName;
    }

    public UserHistory() {
    }
}
