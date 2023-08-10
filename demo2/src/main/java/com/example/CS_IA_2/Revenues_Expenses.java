package com.example.CS_IA_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Revenues_Expenses extends Home implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    private Button backButton2;
    @FXML
    private ChoiceBox<String> revenueCategory;
    @FXML
    private ChoiceBox<String> expenseCategory;
    @FXML
    private TextField revenueAmountField;
    @FXML
    private TextField expenseAmountField;
    @FXML
    private CheckBox expenseIsRecurring;
    @FXML
    private CheckBox revenueIsRecurring;
    @FXML
    private Label added;
    @FXML
    private Label error;
    @FXML
    private Label e_added;
    @FXML
    private Label e_error;
    @FXML
    private Button revUpdateButton;
    @FXML
    private Button expUpdateButton;



    public static String[] revenues = {"Real Estate Income", "Salary", "Commission", "Sale of Goods", "Dividends"};
    public static String[] expenses = {"Transportation", "Restaurant", "Clothing","Luxury Goods", "Groceries","Personal Care", "Entertainment", "Housing", "Medical", "Utilities", "Holidays"};

    //defining ArrayList of revenue and expense objects in the Revenues_Expenses class
    static List<Revenue> revList = new ArrayList<Revenue>();
    static List<Expense> expList = new ArrayList<Expense>();

    public Revenues_Expenses() throws IOException {
    }


    public void back(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.close(backButton);
        m.changeScene("home.fxml");

    }


    /*public static void main(String[] args) throws IOException {
        String filename = "Revenues.bin";
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

    }*/

    //String filename = "Revenues.bin";
    static FileOutputStream fos;
    static {
        try {
            fos = new FileOutputStream("Revenues.bin");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    ObjectOutputStream oos = new ObjectOutputStream(fos);

    //method in another class utilizing the loadFile and writeFile methods
    public void revFile(){
        try{
            String filename = "Revenues.bin";
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(Revenue rev : revList){
                oos.writeObject(rev);
                //Main.writeFile(rev);
            }
            oos.close();
            error.setText("");
            Main.loadFile("Revenues.bin");
            added.setText("File updated successfully!");
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    public void expFile(){
        try{
            String filename = "Expenses.bin";
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(Expense exp : expList){
                oos.writeObject(exp);
            }
            oos.close();
            e_error.setText("");
            e_added.setText("File updated successfully!");
        } catch (IOException e){
            e.printStackTrace();
        }
        Main.loadFile("Expenses.bin");
    }

    //addRevenue and addExpense methods to add Revenue and Expense objects to respective ArrayLists

    public void addRevenue(){
        try{
            error.setText("");
            String r_category = revenueCategory.getValue();
            Double r_amount = Double.valueOf(revenueAmountField.getText());
            boolean r_isRecurring = revenueIsRecurring.isSelected();
            Revenue rev = new Revenue(r_amount, r_category, r_isRecurring);
            revList.add(rev);
            added.setText("Added successfully!");
            //System.out.println(rev);
        }
        catch(Exception e){
            added.setText("");
            error.setText("Missing or incorrect field");
        }
    }

    public void addExpense(){
        try{
            e_error.setText("");
            String e_category = expenseCategory.getValue();
            Double e_amount = Double.valueOf(expenseAmountField.getText());
            boolean e_isRecurring = expenseIsRecurring.isSelected();
            Expense exp = new Expense(e_amount, e_category, e_isRecurring);
            expList.add(exp);
            e_added.setText("Added successfully!");
        }
        catch(Exception e){
            e_added.setText("");
            e_error.setText("Missing or incorrect field");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        revenueCategory.getItems().addAll(revenues);
        expenseCategory.getItems().addAll(expenses);
    }

    public List<Revenue> getRevList() {
        return this.revList;
    }
    public String[] getExpenses(){
        return expenses;
    }
}
