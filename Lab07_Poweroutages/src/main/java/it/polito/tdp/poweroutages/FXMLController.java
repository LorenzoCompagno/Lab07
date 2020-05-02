/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Blackout;
import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Nerc> cmbNERC;

    @FXML
    private TextField txtYears;

    @FXML
    private TextField txtHours;

    @FXML
    private Button btnWorstCase;

    @FXML
    private TextArea txtResult;

    @FXML
    void doNERC(ActionEvent event) {
    	btnWorstCase.setDisable(false);
    }

    @FXML
    void doWorstCase(ActionEvent event) {
    	txtResult.clear();
    	String ore = txtHours.getText();
    	String anni = txtYears.getText();
    	if(ore.isEmpty()) {
    		txtYears.setText("Devi scrivere qualcosa!");
    		return;
    	}
    	if(anni.isEmpty()) {
    		txtYears.setText("Devi scrivere qualcosa!");
    		return;
    	}
    	if((!ore.matches("[0-9]+")) && (!anni.matches("[0-9]+"))) {
    		txtHours.setText("Puoi inserire solo numeri!");
    		txtYears.setText("Puoi inserire solo numeri!");
    		return;
    	}
    	
    	if(!ore.matches("[0-9]+")) {
    		txtHours.setText("Puoi inserire solo numeri!");
    		return;
    	}
    	if(!anni.matches("[0-9]+")) {
    		txtYears.setText("Puoi inserire solo numeri!");
    		return;
    	}
    	int hours = Integer.parseInt(ore);
    	int years = Integer.parseInt(anni);
    	List <Blackout> blackout = model.getWorstCase(cmbNERC.getValue().getId(), hours, years);
    	int totore=0, totCustomers=0;
    	for(Blackout b: blackout) {
    		totore += b.getDuration();
    		totCustomers += b.getCustomersAffected();
    	}
    	txtResult.appendText("Tot people affected: "+String.format("%d", totCustomers) +"\nTot hours of outage: "+totore+"\n");
    	for(Blackout b: blackout)
    		txtResult.appendText(b.toString());
    }

    @FXML
    void initialize() {
        assert cmbNERC != null : "fx:id=\"cmbNERC\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnWorstCase != null : "fx:id=\"btnWorstCase\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		cmbNERC.getItems().addAll(model.getNercList());
		
		
	}
    
}
