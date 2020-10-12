package project.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.springboot.model.Person;
import project.springboot.repository.PersonRepository;

@Controller
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/personregister")
	public String begin() {
		return "register/personregister";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/saveperson")
	public String save(Person person) {
		personRepository.save(person);
		return "register/personregister";
	}

}
