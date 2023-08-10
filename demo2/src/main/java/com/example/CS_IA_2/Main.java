package com.example.CS_IA_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private static Stage stg;
    public void changeScene(String fxml) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(fxml));
        /*
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("FinTracker");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
    // loadFile method which returns an ArrayList of the contents of the file
    public static void loadFile(String filename){
        List<Cashflow> cashflows = new ArrayList<Cashflow>();
        try{
            FileInputStream fin = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fin);
            Object o;
            while(true){
                try{
                    o = ois.readObject();
                    if(o instanceof Cashflow){
                        cashflows.add((Cashflow)o);
                    }else {
                        System.out.println("Unexpected object in file.");
                    }
                } catch(EOFException ex){
                    break;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            ois.close();
            for(Cashflow c : cashflows){
                System.out.println(c);
            }

        } catch(IOException e ){
            e.printStackTrace();
        }
    }
    //write file method for writing to "REvenues.bin" file
    public static void writeFile(Cashflow c){
        String filename = "Revenues.bin";
        try{
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(c);
            oos.close();
        } catch(IOException e){
            e.printStackTrace();
        }

    }




    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600,400);
        stage.setTitle("FinTracker");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        launch();

    }

    public void close(Button button){
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}