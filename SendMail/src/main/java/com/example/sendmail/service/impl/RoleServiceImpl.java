package com.example.sendmail.service.impl;

import com.example.sendmail.model.Role;
import com.example.sendmail.repository.RoleRepository;
import com.example.sendmail.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name).get();
    }
}
