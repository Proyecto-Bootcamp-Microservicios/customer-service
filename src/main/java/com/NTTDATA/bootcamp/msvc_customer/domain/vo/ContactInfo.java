package com.NTTDATA.bootcamp.msvc_customer.domain.vo;

import lombok.Getter;

@Getter
public final class ContactInfo {

    private final String email;
    private final String phone;
    private final Address address;

    private ContactInfo(String email, String phone, Address address) {
        if(email == null || email.trim().isEmpty()) throw new IllegalArgumentException("Email cannot be null or empty");
        if (!isValidEmail(email)) throw new IllegalArgumentException("Invalid email");
        if(phone == null || phone.trim().isEmpty()) throw new IllegalArgumentException("Phone cannot be null or empty");
        if(address == null) throw new IllegalArgumentException("Address cannot be null");
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public static ContactInfo of(String email, String phone, Address address) {
        return new ContactInfo(email, phone, address);
    }

    private boolean isValidEmail(String email) {
        String regex = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$";
        return email.matches(regex);
    }

}
