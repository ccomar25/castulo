package com.upemor.sesioncuatro.component;

import java.util.List;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.upemor.sesioncuatro.model.Member;
import com.upemor.sesioncuatro.model.Role;
import com.upemor.sesioncuatro.repository.MemberRepository;
import com.upemor.sesioncuatro.repository.RoleRepository;
import com.upemor.sesioncuatro.service.MemberService;

@Component
public class AdminCreator {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@PostConstruct
	public void adminCreate() {
		if(!roleRepository.existsById(1))
		{
			Role role = new Role();
			role.setName("ADMIN");
			roleRepository.save(role);
		}
		List<Role> roles = roleRepository.findAll();
		if(!memberRepository.existsMemberByName("admin")) {
			Member member = new Member();
			member.setEmail("admin@localhost.com");
			member.setName("admin");
			member.setPassword("admin");
			memberService.createMember(member, roles);
		}
		
	}
}
