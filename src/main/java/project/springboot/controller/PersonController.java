package project.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import project.springboot.model.Person;
import project.springboot.model.Telephone;
import project.springboot.repository.PersonRepository;
import project.springboot.repository.PhoneRepository;

@Controller
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PhoneRepository phoneRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/personregister")
	public ModelAndView begin() {
		ModelAndView andView = new ModelAndView("register/personregister");
		andView.addObject("personobj", new Person());
		Iterable<Person> personsIt = personRepository.findAll();
		andView.addObject("persons", personsIt);
		return andView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "**/saveperson")
	public ModelAndView save(@Valid Person person, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			ModelAndView andView = new ModelAndView("register/personregister");
			Iterable<Person> personsIt = personRepository.findAll();
			andView.addObject("persons", personsIt);
			andView.addObject("personobj", person);
			
			List<String> msg = new ArrayList<String>();
			for (ObjectError objectError: bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage()); //Came from the annotations
			}
			
			andView.addObject("msg", msg);
			return andView;
		}
		
		personRepository.save(person);
		
		ModelAndView andView = new ModelAndView("register/personregister");
		Iterable<Person> personsIt = personRepository.findAll();
		andView.addObject("persons", personsIt);
		andView.addObject("personobj", new Person());
		return andView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/personslist")
	public ModelAndView persons() {
		ModelAndView andView = new ModelAndView("register/personregister");
		Iterable<Person> personsIt = personRepository.findAll();
		andView.addObject("persons", personsIt);
		andView.addObject("personobj", new Person());
		return andView;
	}
	
	@GetMapping("/editperson/{idperson}")
	public ModelAndView edit(@PathVariable("idperson") Long idperson) {

		Optional<Person> person = personRepository.findById(idperson);
		
		ModelAndView andView = new ModelAndView("register/personregister");
		andView.addObject("personobj", person.get());
		return andView;
	}
	
	@GetMapping("/deleteperson/{idperson}")
	public ModelAndView delete(@PathVariable("idperson") Long idperson) {

		personRepository.deleteById(idperson);
		
		ModelAndView andView = new ModelAndView("register/personregister");
		andView.addObject("persons", personRepository.findAll());
		andView.addObject("personobj", new Person());
		return andView;
	}	
	
	@PostMapping("**/findperson")
	public ModelAndView find(@RequestParam("findname") String findname) {
		ModelAndView andView = new ModelAndView("register/personregister");
		andView.addObject("persons", personRepository.findPersonByName(findname));
		andView.addObject("personobj", new Person());
		return andView;
	}
	
	@GetMapping("/phones/{idperson}")
	public ModelAndView phones(@PathVariable("idperson") Long idperson) {

		Optional<Person> person = personRepository.findById(idperson);
		
		ModelAndView andView = new ModelAndView("register/phoneregister");
		andView.addObject("personobj", person.get());
		andView.addObject("phones", phoneRepository.getTelephones(idperson));
		return andView;
	}	
	
	@PostMapping("**/addphoneperson/{personid}")
	public ModelAndView addPhonePerson(Telephone phone, @PathVariable("personid") Long personid ) {
		
		Person person = personRepository.findById(personid).get();
		phone.setPerson(person);
		
		phoneRepository.save(phone);
		
		ModelAndView andView = new ModelAndView("register/phoneregister");
		andView.addObject("personobj", person);
		andView.addObject("phones", phoneRepository.getTelephones(personid));
		return andView;
	}
	
	@GetMapping("/deletephone/{idphone}")
	public ModelAndView deletePhone(@PathVariable("idphone") Long idphone) {
		
		Person person = phoneRepository.findById(idphone).get().getPerson();

		phoneRepository.deleteById(idphone);
		
		ModelAndView andView = new ModelAndView("register/phoneregister");
		andView.addObject("personobj", person);;
		andView.addObject("phones", phoneRepository.getTelephones(person.getId()));
		return andView;
	}	

}
