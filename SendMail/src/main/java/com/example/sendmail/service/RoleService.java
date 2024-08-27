package com.example.sendmail.service;

import com.example.sendmail.model.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role findByName(String name);
}
