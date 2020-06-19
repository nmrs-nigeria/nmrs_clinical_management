/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.nmrsclinicals.fragment.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.openmrs.api.context.Context;
import org.openmrs.module.nmrsclinicals.models.LinkedIndicator;
import org.openmrs.module.nmrsclinicals.service.Visuals;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author MORRISON.I
 */
public class ClinicalFragmentController {
	
	public String getLinkedIndicator(@RequestParam(value = "uuid", required = true) String uuid) {
		
		ObjectMapper mapper = new ObjectMapper();
		LinkedIndicator result = new LinkedIndicator();
		Visuals visuals = new Visuals();
		String requestResponse = "";
		
		result = visuals.getAllPatientWeights(Context.getPatientService().getPatientByUuid(uuid));
		try {
			requestResponse = mapper.writeValueAsString(result);
		}
		catch (IOException ex) {
			Logger.getLogger(ClinicalFragmentController.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return requestResponse;
		
	}
	
	public String getLinkedIndicatorTest(@RequestParam(value = "uuid", required = true) String uuid) {
		
		ObjectMapper mapper = new ObjectMapper();
		LinkedIndicator result = new LinkedIndicator();
		Visuals visuals = new Visuals();
		String requestResponse = "";
		
		result = visuals.createDummyData();
		try {
			requestResponse = mapper.writeValueAsString(result);
		}
		catch (IOException ex) {
			Logger.getLogger(ClinicalFragmentController.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return requestResponse;
		
	}
	
}
