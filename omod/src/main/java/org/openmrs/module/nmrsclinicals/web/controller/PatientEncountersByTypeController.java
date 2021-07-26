package org.openmrs.module.nmrsclinicals.web.controller;

import org.openmrs.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("${rootrootArtifactid}.PatientEncountersByTypeController")
@RequestMapping(value = "module/nmrsclinicals/encounterbytype.form")
public class PatientEncountersByTypeController {
	
	@Autowired
	UserService userService;
	
	/** Success form view name */
	private final String VIEW = "/module/nmrsclinicals/nmrsclinicals";
	
	/**
	 * Initially called after the getUsers method to get the landing form name
	 * 
	 * @return String form view name
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String onGet() {
		return VIEW;
	}
	
}
