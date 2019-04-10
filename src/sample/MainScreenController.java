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

public class MainScreenController {

    private Main main ;

    @FXML
    private TableView<Part> partsMain;


    @FXML
    private TableColumn<Part, Integer> partsID;

    @FXML
    private TextField searchParts;

    @FXML
    private TableColumn<Part, String> partsName;

    @FXML
    private TableColumn<Part, Integer> partsInventory;

    @FXML
    private TableColumn<Part, Integer> partsPrice;

    @FXML
    private TextField searchProdsText;

    @FXML
    private TableView<Product> proMain;

    @FXML
    private TableColumn<Product, String> prodsName;

    @FXML
    private TableColumn<Product, Integer> prodsInventory;

    @FXML
    private TableColumn<Product, Integer> prodsPrice;

    @FXML
    private TableColumn<Product, Integer> prodsID;

    @FXML
    private Stage mainStage;

    @FXML
    private ObservableList<Part> tPart = FXCollections.observableArrayList();


    @FXML
    private ObservableList<Product> tProduct = FXCollections.observableArrayList();



    public MainScreenController() {

    }

    //intialize and populate the tables
    @FXML
    private void initialize(){

        partsID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsInventory.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        partsPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        prodsID.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
        prodsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodsInventory.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        prodsPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    //parts
    public void searchParts(ActionEvent event) {


        String st = searchParts.getText();


        if(st.equals("")){
            partsMain.setItems(getAllparts());
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



    public void addParts(ActionEvent event) {
        main.showPart();
    }


    //search part helper function
    public void spart(Part p){
        if(p != null){

            tPart.clear();
            tPart.add(p);
            partsMain.setItems(tPart);
        }else{
            partsMain.setItems(getAllparts());
            main.showAlert("Please Search By ID or Name");

        }
    }

    //modifying parts
    public void modifyParts(ActionEvent event) {
        Part part = partsMain.getSelectionModel().getSelectedItem();
        if(part != null){
            main.modPart(part);

        }else{
            main.showAlert("Please select a part to modify");
        }
    }


    //delete parts
    public void deleteParts(ActionEvent event) {
        Part part = partsMain.getSelectionModel().getSelectedItem();
        if(part != null){

            if( main.showConfirm("Confirm", "Are you sure you want to delete?", "Press OK to delete.")  == true ){
                deletePart(part);
            }

        }else {
            main.showAlert("Please select a part to delete");
        }
    }



    //product

    public void searchPros(ActionEvent event) {

        String st = searchProdsText.getText();

        if(st.equals("")){
            proMain.setItems(getAllProducts());
        }else if( st.matches(".*[a-zA-Z].*") || st.matches(".*[^A-Za-z0-9].*") ){
            Product p = Inventory.lookupProductByName(st);
            spro(p);

        }
        else if(st.matches(".*[0-9].*")){
            Product p = Inventory.lookupProduct(Integer.parseInt(st));
            spro(p);


        }

    }

    //product search helper function
    public void spro(Product p){
        if(p != null){
            tProduct.clear();
            tProduct.add(p);
            proMain.setItems(tProduct);
        }else{
            proMain.setItems(getAllProducts());
            main.showAlert("Please search by Name or ID");
        }
    }

    public void addPros(ActionEvent event) {
        main.showProduct();
    }

    public void modifyPros(ActionEvent event) {
        Product p = proMain.getSelectionModel().getSelectedItem();
        if(p != null){
            main.modProduct(p);
        }else{
            main.showAlert("Please select a product to modify");
        }
    }

    public void deletePros(ActionEvent event) {
        Product p = proMain.getSelectionModel().getSelectedItem();

        if(p!=null){
            if( main.showConfirm("Confirm", "Once a product is deleted, it can't be recovered!", "Are you sure?")  == true){
                removeProduct(p);
            }
        }else{
            main.showAlert("Please select a product to delete!");
        }

    }

    public void exitScreen(ActionEvent event) {
        if (main.showConfirm("Confirm!", "Are you sure you want to exit?", "Press Ok to exit.") == true) {
            System.exit(0);
        }
    }


    //populate data
    public void dataParts(){
        Inhouse ih = new Inhouse(incPartID(), "Part 1", 8.99, 12, 10, 100, 12);
        Outsourced ot = new Outsourced(incPartID(), "Part 2", 19.99, 78, 100, 200, "Company 1");
        addPart(ih);
        addPart(ot);
    }

    //populate products
    public void dataProducts(){
        ArrayList<Part> sPart1 = new ArrayList<>();
        ArrayList<Part> sPart2 = new ArrayList<>();

        sPart1.add(lookupPart(1));
        sPart2.add(lookupPart(2));

        Product p1 = new Product(incProductID(), "Product 1", 67.88, 67, 10, 100, sPart1);
        Product p2 = new Product(incProductID(), "Product 2", 35.53, 78, 10, 100, sPart2);

        Inventory.addProduct(p1);
        Inventory.addProduct(p2);
    }


    //populate the mainscreen with parts and products data
    public void populateMain(Main main){
        this.main = main;
        dataParts();
        dataProducts();
        partsMain.setItems(getAllparts());
        proMain.setItems(getAllProducts());
    }

    public void setStage(Stage stage){
        this.mainStage = stage;

    }
}
