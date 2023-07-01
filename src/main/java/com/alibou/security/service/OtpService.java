package com.alibou.security.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class OtpService {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static SecureRandom random = new SecureRandom();

    public String generateOTP() {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(CHARACTERS.length());
            otp.append(CHARACTERS.charAt(index));
        }
        return otp.toString();
    }

    private boolean isOTPValid(LocalDateTime otpCreationTime) {
        LocalDateTime currentTime = LocalDateTime.now();
        long minutesPassed = ChronoUnit.MINUTES.between(otpCreationTime, currentTime);
        return minutesPassed <= 30;
    }

    public boolean verifyOTP(String otp , String otpDatabase , LocalDateTime otpCreationTime) {
        if (otpDatabase.equals(otp) && isOTPValid(otpCreationTime)) {
            return true;
        }
        return false;
    }

}
