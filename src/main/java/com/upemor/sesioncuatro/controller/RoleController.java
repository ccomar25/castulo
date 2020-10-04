package com.upemor.sesioncuatro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



import com.upemor.sesioncuatro.model.Role;
import com.upemor.sesioncuatro.repository.RoleRepository;

@Controller
@RequestMapping("/roles")
public class RoleController {
	
	 
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	public void setUserRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	@GetMapping("")
	public String home(Model model) {
		model.addAttribute("saludo", "Hola, menú roles de la plataforma");
		return "roles/home";
	}
	
	@GetMapping("/list")
	public String allRoles(Model model) {
		List<Role> roles = roleRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		model.addAttribute("roles", roles);
		return "roles/list";
	}
	
	@GetMapping("/create")
	public String createRoleForm(Model model) {
		//List<Role> roles = roleRepository.findAll();
		//model.addAttribute("roles",roles);
		model.addAttribute("role", new Role());
		return "roles/create";
	}
	//@GetMapping("/create")
	//public String createMemberForm(Model model) {
		//model.addAttribute("member", new Member());
	   // return "members/create";
	//}
	@PostMapping("/create")
	  public String createRoleSubmit(@ModelAttribute  Role role,Model model) {
		//if(member.getCreatedDate() == null) {
			//Date date = new Date();			
			//member.setCreatedDate(date);
		roleRepository.save(role);
		model.addAttribute("role" , role);
		return "roles/result";
		}
	
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//member.setPassword(encoder.encode(member.getPassword()));
		//member.setRole(roles);
		//Hacer uso de excepciones
		//memberRepository.save(member);
		//model.addAttribute("mensaje","Se ha creado un nuevo miembro exitosamente!");
	   // model.addAttribute("member", member);
	    //return "members/result";
	 // }
	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
	    Role role = roleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid role Id:" + id));
	  // List<Role> roles = roleRepository.findAll();
	    System.out.println(role.getName());
	    model.addAttribute("role", role);
	   // model.addAttribute("member", member);
	    return "roles/update";
	}
	@PostMapping("/update/{id}")
	public String updateRole(@PathVariable("id") Integer id, @Valid Role role, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        role.setId(id);
	        return "roles/update";
	    }
	    
	    roleRepository.save(role);
	    model.addAttribute("role", roleRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
	    return "redirect:/roles/list";
	   // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//member.setPassword(encoder.encode(member.getPassword()));
		//member.setRole(roles);
	   // memberRepository.save(member);
	   // model.addAttribute("member", memberRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
	   // return "redirect:/members/list";
	
	}
	
	@GetMapping("/delete/{id}")
	public String deleteRole(@PathVariable("id") Integer id, Model model) {
	    Role role = roleRepository.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid role Id:" + id));
	    roleRepository.delete(role);
	    model.addAttribute("role", roleRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
	    return "roles/list";
	}
}
	
	
	