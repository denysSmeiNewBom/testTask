package com.example.bootuserstest.utils;

import com.example.bootuserstest.model.User;
import java.time.LocalDateTime;

public class UserUtils {
    public static void dateRezolve(User user){
        user.setDateRecord(user.getDateRecord() != null ? user.getDateRecord() : LocalDateTime.now());
    }
}
