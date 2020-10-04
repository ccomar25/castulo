package com.upemor.sesioncuatro.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upemor.sesioncuatro.model.Role;
import com.upemor.sesioncuatro.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	public void createRole(Role role) {
		
		roleRepository.save(role);
	}

}
