package com.upemor.sesioncuatro.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.upemor.sesioncuatro.model.Member;
import com.upemor.sesioncuatro.model.Role;
import com.upemor.sesioncuatro.repository.MemberRepository;


@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository memberRepository;

	public void createMember(Member member, List<Role> roles) {
		if(member.getCreatedDate() == null) {
			Date date = new Date();			
			member.setCreatedDate(date);
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        member.setPassword(encoder.encode(member.getPassword()));
        member.setRole(roles);
		memberRepository.save(member);
	}

}

