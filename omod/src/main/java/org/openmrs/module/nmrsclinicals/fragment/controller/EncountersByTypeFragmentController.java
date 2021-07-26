package org.openmrs.module.nmrsclinicals.fragment.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.codehaus.jackson.map.ObjectMapper;
import org.openmrs.api.EncounterService;
import org.openmrs.api.PatientService;
import org.openmrs.module.nmrsclinicals.models.PatientEncounterByType;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for a fragment that shows the encounters that have taken place today
 */
public class EncountersByTypeFragmentController {
	
	public void controller(FragmentModel model, @SpringBean("encounterService") EncounterService service,
	        @SpringBean("patientService") PatientService patientService) {
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date startOfDay = cal.getTime();
		
		cal.add(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.MILLISECOND, -1);
		Date endOfDay = cal.getTime();
		
		try {
			startOfDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse("2021-06-16 00:00:00.000");
			endOfDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse("2021-06-16 23:59:59.999");
		}
		catch (Exception ex) {}
		
		model.addAttribute("encounters",
		    service.getEncounters(null, null, startOfDay, endOfDay, null, null, null, null, null, false));
	}
	
	public String getEncounters(
			@SpringBean("encounterService") EncounterService encounterService,
			@SpringBean("patientService") PatientService patientService,
			@RequestParam(value = "patientId", required = true) String patientId,
			UiUtils ui
	){
		System.out.println(patientId);
		Date startOfDay;
		Date endOfDay;
		List<PatientEncounterByType> encounters = new ArrayList<>();
		try {
			 startOfDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse("2021-02-16 00:00:00.000");
			 endOfDay = new Date();
			encounters = encounterService.getEncounters(patientService.getPatientByUuid(patientId), null, null, null,
					null, null, null, null, null, false)
			.stream().map( enc -> new PatientEncounterByType(enc.getEncounterId(), enc.getEncounterType().getName(),
					enc.getForm().getName(), enc.getEncounterDatetime(),
					enc.getVisit().getId(),
					enc.getForm().getUuid()))
			.collect(Collectors.toList());
			System.out.println(encounters.toString());
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(encounters);
//		return  SimpleObject.fromCollection(encounters, ui);
		}catch (Exception ex){
			ex.printStackTrace();
			return  null;
		}
	}
}
