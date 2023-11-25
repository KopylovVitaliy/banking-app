package com.example.bankingapp.service.impl;

import com.example.bankingapp.service.SecurityService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    private PasswordEncoder passwordEncoder;
    @Override
    public void verification(String pinCode, String actual) {
      if(!passwordEncoder.matches(pinCode, actual)) {
          throw new RuntimeException();
      }
    }

    @Override
    public String encodePinCode(String pinCode) {
        return passwordEncoder.encode(pinCode);
    }
}
