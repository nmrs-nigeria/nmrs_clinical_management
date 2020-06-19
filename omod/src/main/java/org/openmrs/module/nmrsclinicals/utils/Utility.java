/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.nmrsclinicals.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;

/**
 * @author MORRISON.I
 */
public class Utility {
	
	public static List<Encounter> getAllPatientEncounters(Patient p) {
		
		return Context.getEncounterService().getEncountersByPatient(p);
		
	}
	
	public Set<Obs> getAllPatientObs(List<Encounter> allPatientEncounters) {

        Set<Obs> patientObs = new HashSet<>();

        allPatientEncounters.stream().forEach(b -> {
            patientObs.addAll(b.getAllObs());
        });

        return patientObs;
    }
	
	public static Set<Obs> getAllCareCardObs(List<Encounter> allPatientEncounters){
        Set<Obs> patientCareCardObs = new HashSet<>();
                
         allPatientEncounters.stream().filter(a -> a.getEncounterType()
              .getEncounterTypeId().equals(ConstantUtil.CARE_CARD_ENCOUNTER_TYPE))
              .forEach(b -> {
              patientCareCardObs.addAll(b.getAllObs());
              });
         
         return patientCareCardObs;
    }
	
	public static Set<Obs> getAllLabObs(List<Encounter> allPatientEncounters){
        Set<Obs> patientLabObs = new HashSet<>();
                
         allPatientEncounters.stream().filter(a -> a.getEncounterType()
              .getEncounterTypeId().equals(ConstantUtil.LAB_ORDER_ENCOUNTER_TYPE))
              .forEach(b -> {
              patientLabObs.addAll(b.getAllObs());
              });
         
         return patientLabObs;
    }
	
	public static Set<Obs> getAllPharmacyObs(List<Encounter> allPatientEncounters){
        Set<Obs> patientPharmObs = new HashSet<>();
                
         allPatientEncounters.stream().filter(a -> a.getEncounterType()
              .getEncounterTypeId().equals(ConstantUtil.PHARMACY_ENCOUNTER_TYPE))
              .forEach(b -> {
              patientPharmObs.addAll(b.getAllObs());
              });
         
         return patientPharmObs;
    }
	
	public static Set<LocalDate> getAllDateTimeFromObs(Set<Obs> obs){
           Set<LocalDate> result = new HashSet<>();
           
         obs.stream().forEach(a-> {
        result.add(a.getObsDatetime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        });
         
         return result;
        
       }
}
