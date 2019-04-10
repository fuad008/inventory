package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import sample.Models.*;

import java.util.ArrayList;

import static sample.Models.Inventory.*;


public class ProductController {

    private final Main main = new Main();
    private Stage productStage;
    private int productID;
    private Product sProd;
    private int index;


    @FXML
    private Label prolabel;

    @FXML
    private TextField prodID;

    @FXML
    private TextField prodName;

    @FXML
    private TextField prodInstock;

    @FXML
    private TextField prodPrice;

    @FXML
    private TextField prodMax;

    @FXML
    private TextField prodMin;

    @FXML
    private TextField partSearchText;


    @FXML
    private TableView<Part> partsTV;

    @FXML
    private TableColumn<Part, Integer> partID;

    @FXML
    private TableColumn<Part, String> partName;

    @FXML
    private TableColumn<Part, Integer> partInstock;

    @FXML
    private TableColumn<Part, Double> partPrice;


    @FXML
    private TableView<Part> aspartTV;
    @FXML
    private TableColumn<Part, Integer> aspartID;

    @FXML
    private TableColumn<Part, String> aspartName;

    @FXML
    private TableColumn<Part, Integer> aspartInstock;

    @FXML
    private TableColumn<Part, Double> aspartPrice;

    @FXML
    private ObservableList<Part> tPart = FXCollections.observableArrayList();

    @FXML
    private ObservableList<Part> cPart = FXCollections.observableArrayList();


    @FXML
    private void initialize(){

        //populate parts table
        partID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInstock.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        //populate the associate table
        aspartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        aspartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        aspartInstock.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        aspartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        prodInstock.setText(String.valueOf(0));
        partsTV.setItems(Inventory.getAllparts());
    }


    public void setProductStage(Stage proStage){
        this.productStage = proStage;
    }

    //search for parts in product modify screen
    public void searchPart(ActionEvent event) {

        String st = partSearchText.getText();


        if(st.equals("")){

            partsTV.setItems(getAllparts());

        }else{
            //if search text includes any letters
            if(st.matches(".*[a-zA-Z].*")){
                Part p = lookupbyName(st);
                spart(p);
            }else if(st.matches(".*[^A-Za-z0-9].*")){
                Part p = lookupbyName(st);
                spart(p);
            }else if(st.matches(".*[0-9].*")){
                Part p = lookupPart(Integer.parseInt(st));
                spart(p);
            }

        }//main else

    }


    //search part helper function
    public void spart(Part p){
        if(p != null){

            tPart.clear();
            tPart.add(p);
            partsTV.setItems(tPart);
        }else{
            partsTV.setItems(getAllparts());
            main.showAlert("No Parts Found! Please Search by Part ID or Name");
        }
    }

    //sets up ID when user clicks on add Product
    public void incProd(){
        prodID.setText(Integer.toString(Inventory.incProductID()));
    }


    //sets up data for modifying product
    public void setupProduct(Product p){

        sProd = p;

        cPart = sProd.getObAssociatedPart();

        prodID.setText(Integer.toString(p.getProductID()));
        prodName.setText(p.getName());
        prodPrice.setText(Double.toString(p.getPrice()));
        prodInstock.setText(Integer.toString(p.getInStock()));
        prodMax.setText(Integer.toString(p.getMax()));
        prodMin.setText(Integer.toString(p.getMin()));

        aspartTV.setItems(cPart);

    }

    //adding part to the product when creating/modifying product

    public void addPart(ActionEvent event) {
        Part p = partsTV.getSelectionModel().getSelectedItem();
        cPart.add(p);
        aspartTV.setItems(cPart);
    }


    //delete the part from the product when modifying product
    public void deletePart(ActionEvent event) {


        Part part = aspartTV.getSelectionModel().getSelectedItem();

        if(part != null){
            int len = aspartTV.getItems().size();

            if(len > 1){

                if( main.showConfirm("Confirm", "Deleting Parts is irreverseable! Are you sure?", "Press Ok to delete.") == true ){
                    cPart.remove(part);
                    aspartTV.setItems(cPart);
                }

            }else{

                main.showAlert("Can't Delete! Each product must have atleast one part.");
            }
        }else{
            main.showAlert("Please select a part to delete!");
        }

    }

    //save new product
    public void saveNewProd() {

        if(validate() != false){
            //get the data from the text feilds
            int id = Integer.parseInt(prodID.getText());
            String name = prodName.getText();
            int inStock = Integer.parseInt(prodInstock.getText());
            double price = Double.parseDouble(prodPrice.getText());
            int min = Integer.parseInt(prodMin.getText());
            int max = Integer.parseInt(prodMax.getText());

            //create a new product with data above
            Product p = new Product();
            p.setProductID(id);
            p.setName(name);
            p.setInStock(inStock);
            p.setPrice(price);
            p.setMin(min);
            p.setMax(max);

            //add associated part to the product
            ArrayList<Part> part = new ArrayList<>();
            part.addAll(aspartTV.getItems());
            p.addAssociatedPart(part);

            //finally add the product to inventory
            Inventory.addProduct(p);

            productStage.close();
        }

    }


    //save the modified product
    public void saveModProd(){

        if(validate() == true){
            //get the data from the text feilds
            int id = Integer.parseInt(prodID.getText());
            String name = prodName.getText();
            int inStock = Integer.parseInt(prodInstock.getText());
            double price = Double.parseDouble(prodPrice.getText());
            int min = Integer.parseInt(prodMin.getText());
            int max = Integer.parseInt(prodMax.getText());

            //create a new product with data above
            Product p = new Product();
            p.setProductID(id);
            p.setName(name);
            p.setInStock(inStock);
            p.setPrice(price);
            p.setMin(min);
            p.setMax(max);

            //add associated part to the product
            ArrayList<Part> part = new ArrayList<>();
            part.addAll(aspartTV.getItems());
            p.addAssociatedPart(part);

            Inventory.updateProduct(index, p);
            productStage.close();
        }
    }


    //initializes the index of the product
    public void getIndex(int num){
        this.index = num;
    }



    //cancel product handler
    public void cancelProd(ActionEvent event) {
        if( main.showConfirm("Confirm", "Are you sure you want to cancel?", "Press OK to cancel.")  == true ){
            Inventory.desProductID();
            productStage.close();
        }

    }

    //cancel modified handler
    public void cancelModProd(ActionEvent event) {
        if( main.showConfirm("Confirm", "Are you sure you want to cancel?", "Press OK to cancel.")  == true ){
            productStage.close();
        }
    }

    public boolean validate(){
        //get the data from the text feilds
        String name = prodName.getText().trim();
        String inStock = prodInstock.getText().trim();
        String price = prodPrice.getText().trim();
        String min = prodMin.getText().trim();
        String max = prodMax.getText().trim();
        int partlen = aspartTV.getItems().size();

        if(partlen == 0){
            main.showAlert("There must be atleast one associated part!");
            return false;
        }else if(name.isEmpty() || inStock.isEmpty() || price.isEmpty() || min.isEmpty() || max.isEmpty()){
            main.showAlert("Please fill up all the fields");
            return false;
        }else{
            return true;
        }


    }//validate


}
