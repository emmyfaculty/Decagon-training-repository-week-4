package com.emmanuel;


import java.util.HashMap;

public class StoreHire {
    private HashMap<String, Roles> staffList;

    public StoreHire() {
        this.staffList = new HashMap<>();
    }

    public HashMap<String, Roles> getStaffList() {
        return staffList;
    }

    public String hire (User myUser, User newUser) {
        String message = "";
        if (myUser.getRole() == Roles.MANAGER && newUser.getRole() == Roles.APPLICANT) {
            newUser.setRole(Roles.CASHIER);
            staffList.put(newUser.getName(), newUser.getRole());
            message = "Welcome " + newUser.getName() + ", our new " + newUser.getRole();
        } else {
            message = "Only authorized users are allowed";
        }
        return message;
    }


}

