/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.nmrsclinicals.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.nmrsclinicals.models.LinkedIndicator;
import org.openmrs.module.nmrsclinicals.utils.ConstantUtil;
import org.openmrs.module.nmrsclinicals.utils.Utility;

/**
 * @author MORRISON.I
 */
public class Visuals {
	
	public LinkedIndicator getAllPatientWeights(Patient p) {

        Map<LocalDate, Double> weightMap = new HashMap<>();
        Map<LocalDate, Double> viralloadMap = new HashMap<>();
        Map<LocalDate, Double> pharmMap = new HashMap<>();

        Set<LocalDate> obsDateTime = new HashSet<>();

        List<Encounter> allPatientEncounters = Utility.getAllPatientEncounters(p);

        Set<Obs> careCardObs = Utility.getAllCareCardObs(allPatientEncounters);
        Set<Obs> labObs = Utility.getAllLabObs(allPatientEncounters);
        Set<Obs> pharmObs = Utility.getAllPharmacyObs(allPatientEncounters);

        obsDateTime.addAll(Utility.getAllDateTimeFromObs(careCardObs));
        obsDateTime.addAll(Utility.getAllDateTimeFromObs(labObs));
        obsDateTime.addAll(Utility.getAllDateTimeFromObs(pharmObs));

        //create weight map
        for (Obs b : careCardObs) {

            if (Objects.equals(b.getConcept().getConceptId(), ConstantUtil.WEIGHT_CONCEPT)) {
                weightMap.put(b.getObsDatetime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), b.getValueNumeric());
            }

        }

        //create viralload maps
        for (Obs b : labObs) {

            if (Objects.equals(b.getConcept().getConceptId(), ConstantUtil.VIRALLOAD_CONCEPT)) {
                viralloadMap.put(b.getObsDatetime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), b.getValueNumeric());
            }

        }

        //create pickup map
        for (Obs b : pharmObs) {

            if (Objects.equals(b.getConcept().getConceptId(), ConstantUtil.ADULT_FIRST_LINE)) {
                pharmMap.put(b.getObsDatetime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 1.0);
            } else if (Objects.equals(b.getConcept().getConceptId(), ConstantUtil.ADULT_SECOND_LINE)) {
                pharmMap.put(b.getObsDatetime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 1.0);
            } else if (Objects.equals(b.getConcept().getConceptId(), ConstantUtil.ADULT_THIRD_LINR)) {
                pharmMap.put(b.getObsDatetime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 1.0);
            } else if (Objects.equals(b.getConcept().getConceptId(), ConstantUtil.PED_FIRST_LINE)) {
                pharmMap.put(b.getObsDatetime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 1.0);
            } else if (Objects.equals(b.getConcept().getConceptId(), ConstantUtil.PED_SECOND_LINE)) {
                pharmMap.put(b.getObsDatetime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 1.0);
            } else if (Objects.equals(b.getConcept().getConceptId(), ConstantUtil.PED_THIRD_LINE)) {
                pharmMap.put(b.getObsDatetime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 1.0);
            } else {
                pharmMap.put(b.getObsDatetime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), null);
            }

        }

        return rearrangeIndicators(obsDateTime, weightMap, viralloadMap, pharmMap);

    }
	
	private LinkedIndicator rearrangeIndicators(Set<LocalDate> allObsDate, Map<LocalDate, Double> weightMap,
            Map<LocalDate, Double> viralloadMap, Map<LocalDate, Double> pharmMap) {

        LinkedIndicator linkedIndicator = new LinkedIndicator();

        List<Double> weights = new ArrayList<>();
        List<Double> viralLoads = new ArrayList<>();
        List<Double> pickUps = new ArrayList<>();
        Set<String> dates = new HashSet<>();
        
        allObsDate.stream()
                .forEach(a -> {
                    weights.add(weightMap.get(a));
                    viralLoads.add(viralloadMap.get(a));
                    pickUps.add(pharmMap.get(a));
                    dates.add(a.toString());
                });

        
        linkedIndicator.setObsDate(dates);
        linkedIndicator.setPickUps(pickUps);
        linkedIndicator.setViralLoads(viralLoads);
        linkedIndicator.setWeights(weights);

        return linkedIndicator;

    }
	
	public LinkedIndicator createDummyData() {

        LinkedIndicator ld = new LinkedIndicator();
        Set<String> dates = new HashSet<>();
        Date d = new Date(2016, 6, 3);
        Date d2 = new Date(2016, 5, 3);
        Date d3 = new Date(2016, 4, 3);
        Date d4 = new Date(2016, 3, 22);
        Date d5 = new Date(2016, 7, 12);

        dates.add(d.toString());
        dates.add(d2.toString());
        dates.add(d3.toString());
        dates.add(d4.toString());
        dates.add(d5.toString());

        List<Double> weights = new ArrayList<>();

        List<Double> viralLoads = new ArrayList<>();

        List<Double> pickUps = new ArrayList<>();

        weights.add(82.0);
        weights.add(62.0);
        weights.add(74.0);
        weights.add(60.0);
        weights.add(55.0);

        viralLoads.add(102.0);
        viralLoads.add(90.0);
        viralLoads.add(85.0);
        viralLoads.add(50.0);
        viralLoads.add(85.0);

        pickUps.add(1.0);
        pickUps.add(1.0);
        pickUps.add(null);
        pickUps.add(1.0);
        pickUps.add(null);

        ld.setObsDate(dates);
        ld.setPickUps(pickUps);
        ld.setViralLoads(viralLoads);
        ld.setWeights(weights);
        
        
        return ld;

    }
}
