package com.drivingschool.util;

public class EmailUtil {
    public static void sendEmail(String to, String subject, String body) {
        System.out.println("Sending email to: " + to + "\nSubject: " + subject + "\nBody: " + body);
    }
}