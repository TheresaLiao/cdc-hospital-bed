package org.itri.view.humanhealth;

import java.util.LinkedList;
import java.util.List;

import org.itri.view.humanhealth.dao.PersonState;
import org.zkoss.admin.ecommerce.dao.Type;
import org.zkoss.bind.annotation.Init;

public class PersonInfos {
	  private List<PersonState> states;

	    @Init
	    public void init(){
	        queryStates();
	    }

	    private void queryStates() {
	        states = new LinkedList<>();
	        for (int i = 0 ; i < 810 ; i++){
	            PersonState state = new PersonState();
	            
	            state.setName("Lee John "+String.valueOf(i));
	            state.setBodyTemperature(39.5);
	            state.setHeartBeat(118);
	            state.setBreathRate(20);
	            state.setBedRoom("307-" + String.valueOf(i+1));
	            
	            state.setType(Type.values()[0]);
	            state.setValue(1317 * (i + 1));
	            state.setRatio(0.329);
	            
	            states.add(state);
	        }
	    }

	    public List<PersonState> getStates() {
	        return states;
	    }
}
