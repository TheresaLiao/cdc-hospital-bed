package org.itri.view.humanhealth.detail;

import java.util.LinkedList;
import java.util.List;

import org.itri.view.humanhealth.dao.PersonState;
import org.itri.view.humanhealth.dao.Status;
import org.zkoss.admin.ecommerce.dao.Type;

import org.zkoss.bind.annotation.Init;

public class PersonInfo {
	  private List<PersonState> states;

	    @Init
	    public void init(){
	        queryStates();
	    }

	    private void queryStates() {
	        states = new LinkedList<>();
	        for (int i = 0 ; i < 1 ; i++){
	        	for (int j = 0 ; j < 1 ; j++){	
	            PersonState state = new PersonState();
	            
	            state.setName("Lee John "+String.valueOf(i));
	            state.setBodyTemperature(39.5);
	            state.setHeartBeat(118);
	            state.setBreathRate(20);
	            state.setBedRoom("2" + String.format("%02d",(i+1))+"-"+String.valueOf(j+1));
	            state.setSpo2(90);
	            
	            state.setType(Type.values()[0]);
	            state.setValue(1317 * (i + 1));
	            state.setRatio(0.329);
	            state.setStatus(Status.values()[1]);
	            
	            states.add(state);
	        	}
	        }
	    }

	    public List<PersonState> getStates() {
	        return states;
	    }
}
