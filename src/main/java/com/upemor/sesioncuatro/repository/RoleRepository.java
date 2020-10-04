package com.upemor.sesioncuatro.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upemor.sesioncuatro.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Role findByName(String string);
	
	boolean existsRoleByName(String name);
	

}
