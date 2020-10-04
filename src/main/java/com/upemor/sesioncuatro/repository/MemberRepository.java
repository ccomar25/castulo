package com.upemor.sesioncuatro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upemor.sesioncuatro.model.Member;

@Repository 
public interface MemberRepository extends JpaRepository<Member, Integer> {
	
	List<Member> findById(int id);
	
	List<Member> findById(String name);
	
	List<Member> findByEmail(String email);
	
	boolean existsMemberByName(String name);

}
