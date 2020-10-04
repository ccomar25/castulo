package com.upemor.sesioncuatro.service;

import java.util.List;

import com.upemor.sesioncuatro.model.Member;
import com.upemor.sesioncuatro.model.Role;




public interface MemberService {

	void createMember(Member member , List<Role> roles );
}
