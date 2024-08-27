package com.example.security_jwt.service;

import com.example.security_jwt.dto.role.RoleDTO;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    RoleDTO findByName(String name);
}
