package com.example.bankingapp.service;

public interface SecurityService {
    void verification(String pinCode, String correct);
    String encodePinCode(String pinCode);
}
