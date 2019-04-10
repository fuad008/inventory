package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Models.*;
import java.io.IOException;
import java.util.Optional;

public class Main extends Application {


    Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("MainScreen.fxml"));
        AnchorPane ms = (AnchorPane) fxmlLoader.load();

        MainScreenController controller = fxmlLoader.getController();
        controller.populateMain(this);
        controller.setStage(primaryStage);
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(ms, 900, 450));

        primaryStage.show();
    }


    //part

    public void showPart(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("Part.fxml"));
            AnchorPane partWindow = (AnchorPane) fxmlLoader.load();

            Stage partStage = new Stage();
            partStage.setTitle("Add Part");
            partStage.initOwner(primaryStage);
            partStage.initModality(Modality.APPLICATION_MODAL);

            Scene scene = new Scene(partWindow);
            partStage.setScene(scene);

            //giving contoller the access
            PartController controller = fxmlLoader.getController();
            controller.setPartStage(partStage);
            controller.incPart();

            partStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Inventory.desPartID();
                    partStage.close();
                }
            });

            partStage.showAndWait();



        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void modPart(Part part){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("ModifyPart.fxml"));
            AnchorPane partMWindow = (AnchorPane) fxmlLoader.load();

            Stage partStage = new Stage();
            partStage.setTitle("Modify Part");
            partStage.initOwner(primaryStage);
            partStage.initModality(Modality.APPLICATION_MODAL);

            Scene scene = new Scene(partMWindow);
            partStage.setScene(scene);

            PartController controller = fxmlLoader.getController();
            controller.setPartStage(partStage);
            controller.setupPart(part);
            controller.getIndex(Inventory.getAllparts().indexOf(part));

            partStage.showAndWait();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    //product

    public void showProduct(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("Product.fxml"));
            AnchorPane proWindow = (AnchorPane) fxmlLoader.load();

            Stage prodStage = new Stage();
            prodStage.setTitle("Add Product");
            prodStage.initOwner(primaryStage);
            prodStage.initModality(Modality.APPLICATION_MODAL);

            Scene scene = new Scene(proWindow);
            prodStage.setScene(scene);

            ProductController controller = fxmlLoader.getController();
            controller.setProductStage(prodStage);
            controller.incProd();

            prodStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Inventory.desProductID();
                    prodStage.close();
                }
            });


            prodStage.showAndWait();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void modProduct(Product p){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("ModifyProduct.fxml"));
            AnchorPane prdWindow = (AnchorPane) fxmlLoader.load();

            Stage prodStage = new Stage();
            prodStage.setTitle("Modify Product");
            prodStage.initOwner(primaryStage);
            prodStage.initModality(Modality.APPLICATION_MODAL);

            Scene scene = new Scene(prdWindow);
            prodStage.setScene(scene);

            ProductController controller = fxmlLoader.getController();
            controller.setProductStage(prodStage);
            controller.getIndex(Inventory.getAllProducts().indexOf(p));
            controller.setupProduct(p);

            prodStage.showAndWait();

        }catch (IOException e){
            e.printStackTrace();
        }
    }



    public void showAlert(String st){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, st, ButtonType.OK);
        alert.setTitle("Alert");
        alert.initOwner(primaryStage);
        alert.showAndWait();
    }

    public boolean showConfirm(String title, String text, String body){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(text);
        alert.setContentText(body);
        alert.initOwner(primaryStage);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            return true;
        }else{
            alert.close();
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

