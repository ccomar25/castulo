package com.upemor.sesioncuatro.controller;


import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.upemor.sesioncuatro.model.Member;
import com.upemor.sesioncuatro.model.Role;
import com.upemor.sesioncuatro.repository.MemberRepository;
import com.upemor.sesioncuatro.repository.RoleRepository;
import com.upemor.sesioncuatro.service.MemberService;






@Controller
@RequestMapping("/members")
public class MemberController {
	
	@Autowired
	private MemberRepository memberRepository; 
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("")
	public String home(Model model) {		
		return "members/home";
	}
	
	@GetMapping("/list")
	public String allMembers(Model model) {
		List<Member> members = memberRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		model.addAttribute("members", members);
		return "members/list";
	}
	
	@GetMapping("/create")
	public String createMemberForm(Model model) {
		List<Role> roles = roleRepository.findAll();
		model.addAttribute("roles",roles);
		model.addAttribute("member", new Member());
		return "members/create";
	}
	
	@PostMapping("/create")
	  public String createMemberSubmit(@ModelAttribute Member member, @RequestParam List<Role> roles,Model model) {
		 memberService.createMember(member, roles);
		 model.addAttribute("member", member);
	    return "members/result";
	  }
	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
	    Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid member Id:" + id));
	   List<Role> roles = roleRepository.findAll();
	    model.addAttribute("roles", roles);
	    model.addAttribute("member", member);
	    return "members/update";
	}
	@PostMapping("/update/{id}")
	public String updateMember(@PathVariable("id") Integer id, @Valid Member member,@RequestParam List<Role> roles, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        member.setId(id);
	        return "members/update";
	    }
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		member.setPassword(encoder.encode(member.getPassword()));
		member.setRole(roles);
	    memberRepository.save(member);
	    model.addAttribute("member", memberRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
	    return "redirect:/members/list";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteMember(@PathVariable("id") Integer id, Model model) {
	    Member member = memberRepository.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid member Id:" + id));
	    memberRepository.delete(member);
	    model.addAttribute("members", memberRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
	    return "redirect:/members/list";
	}
}