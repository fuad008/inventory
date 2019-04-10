package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Models.*;

import javax.swing.*;


public class PartController {


    private final Main main = new Main();
    private Stage partStage;
    private int idPart;
    private Part part;
    private int index;

    @FXML
    private TextField partID;

    @FXML
    private TextField partName;

    @FXML
    private TextField partInStock;

    @FXML
    private TextField partPrice;

    @FXML
    private TextField machineID;



    @FXML
    private Label machineLabel;

    @FXML
    private TextField partMin;

    @FXML
    private TextField partMax;

    @FXML
    private ToggleGroup togglePart;

    @FXML
    private RadioButton inhouseButton;

    @FXML
    private RadioButton outsourceButton;



    @FXML
    private void initialize(){
        togglePart = new ToggleGroup();
        this.inhouseButton.setToggleGroup(togglePart);
        this.outsourceButton.setToggleGroup(togglePart);

    }

    //intializes the stage for the part
    public void setPartStage(Stage partStage){
        this.partStage = partStage;
    }


    //outsource radio button click handler
    public void outsourceSelect(ActionEvent event) {
        machineLabel.setText("Company Name");
        machineID.setPromptText("Company Name");
    }


    //saves new part to the inventory
    public void saveNewPart(ActionEvent event){

        if(validate() == true){
            String name = partName.getText();
            int inStock = Integer.parseInt(partInStock.getText());
            Double price = Double.parseDouble(partPrice.getText());
            int min = Integer.parseInt(partMin.getText());
            int max = Integer.parseInt(partMax.getText());
            String Machine = machineID.getText();


            if(this.togglePart.getSelectedToggle().equals(inhouseButton)){
                Inhouse iPart = new Inhouse();
                iPart.setPartID(idPart);
                iPart.setName(name);
                iPart.setInStock(inStock);
                iPart.setPrice(price);
                iPart.setMin(min);
                iPart.setMax(max);
                iPart.setMachineID(Integer.parseInt(Machine));
                Inventory.addPart(iPart);

            }else {
                Outsourced oPart = new Outsourced();
                oPart.setPartID(idPart);
                oPart.setName(name);
                oPart.setInStock(inStock);
                oPart.setPrice(price);
                oPart.setMin(min);
                oPart.setMax(max);
                oPart.setCompanyName(Machine);
                Inventory.addPart(oPart);

            }

            partStage.close();
        }

    }


    // saves the modified part
    public void saveModPart(ActionEvent event){

        if(validate() == true){
            int id = Integer.parseInt(partID.getText());
            String name = partName.getText();
            int inStock = Integer.parseInt(partInStock.getText());
            Double price = Double.parseDouble(partPrice.getText());
            int min = Integer.parseInt(partMin.getText());
            int max = Integer.parseInt(partMax.getText());
            String machine = machineID.getText();

            if(this.togglePart.getSelectedToggle().equals(this.inhouseButton)){
                Inhouse ih = new Inhouse();
                ih.setPartID(id);
                ih.setName(name);
                ih.setInStock(inStock);
                ih.setPrice(price);
                ih.setMin(min);
                ih.setMax(max);
                ih.setMachineID(Integer.parseInt(machine));
                Inventory.updatePart(index, ih);
                partStage.close();


            }else{
                Outsourced os = new Outsourced();
                os.setPartID(id);
                os.setName(name);
                os.setInStock(inStock);
                os.setPrice(price);
                os.setMin(min);
                os.setMax(max);
                os.setCompanyName(machine);
                Inventory.updatePart(index, os);
                partStage.close();
            }
        }


    }

    // populates the data to modify part
    public void setupPart(Part part){

        partID.setText(Integer.toString(part.getPartID()));
        partName.setText(part.getName());
        partInStock.setText(Integer.toString(part.getInStock()));
        partPrice.setText(Double.toString(part.getPrice()));
        partMin.setText(Integer.toString(part.getMin()));
        partMax.setText(Integer.toString(part.getMax()));

        if(part instanceof Inhouse){

            String st = Integer.toString(((Inhouse)part).getMachineID());
            machineID.setText(st);
            machineLabel.setText("Machine ID");
            inhouseButton.setSelected(true);
            inhouseButton.requestFocus();

        }else{
            String st = ((Outsourced)part).getCompanyName();
            machineID.setText(st);
            machineLabel.setText("Company Name");
            outsourceButton.setSelected(true);
            outsourceButton.requestFocus();
        }

    }


    // cancel click handler for new part
    public void cancelPart(ActionEvent event){

        if( main.showConfirm("Confirm", "Are you sure you want to cancel?", "Press OK to cancel.")  == true ){
            partStage.close();
            Inventory.desPartID();
        }




    }


    // intializes the index for the part in the inventory
    public void getIndex(int num){
        this.index = num;
    }



    //handles cancel for the modified part
    public void cancelModPart(ActionEvent event){
        if( main.showConfirm("Confirm", "Are you sure you want to cancel?", "Press OK to cancel.")  == true ){
            partStage.close();
        }

    }


    //initializes the part id
    public void incPart(){
        idPart = Inventory.incPartID();
        partID.setText(Integer.toString(idPart));
    }



    // inhouse radio button click handler
    public void inhouseSelect(ActionEvent event){
        machineLabel.setText("Machine ID");
        machineID.setPromptText("Machine ID");
    }



    public boolean validate(){
        String name = partName.getText().trim();
        String inStock = partInStock.getText().trim();
        String price = partPrice.getText().trim();
        String min = partMin.getText().trim();
        String max = partMax.getText().trim();
        String Machine = machineID.getText().trim();

        if(name.isEmpty() || inStock.isEmpty() || price.isEmpty() || min.isEmpty() || max.isEmpty() || Machine.isEmpty()){
            main.showAlert("Please, fill up all the feilds!");
            return false;
        }else{
            return true;
        }
    }


}
