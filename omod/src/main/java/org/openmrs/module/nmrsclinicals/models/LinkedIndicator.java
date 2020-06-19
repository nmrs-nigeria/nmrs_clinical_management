/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.nmrsclinicals.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * @author MORRISON.I
 */
public class LinkedIndicator implements Serializable {
	
	private Set<String> obsDate;
	
	private List<Double> weights;
	
	private List<Double> viralLoads;
	
	private List<Double> pickUps;
	
	public Set<String> getObsDate() {
		return obsDate;
	}
	
	public void setObsDate(Set<String> obsDate) {
		this.obsDate = obsDate;
	}
	
	public List<Double> getWeights() {
		return weights;
	}
	
	public void setWeights(List<Double> weights) {
		this.weights = weights;
	}
	
	public List<Double> getViralLoads() {
		return viralLoads;
	}
	
	public void setViralLoads(List<Double> viralLoads) {
		this.viralLoads = viralLoads;
	}
	
	public List<Double> getPickUps() {
		return pickUps;
	}
	
	public void setPickUps(List<Double> pickUps) {
		this.pickUps = pickUps;
	}
	
}
