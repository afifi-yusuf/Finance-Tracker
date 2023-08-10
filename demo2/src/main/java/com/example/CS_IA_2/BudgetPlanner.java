package com.example.CS_IA_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BudgetPlanner extends Home implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    private TextField totalBudgetField;
    @FXML
    private ChoiceBox categories;
    @FXML
    private TextField budgetAmount;
    @FXML
    private Label success;
    @FXML
    private Label error;
    @FXML
    private Label monthlyBudgetLabel;
    @FXML
    private ProgressBar totalBar;
    @FXML
    private Label totalProgressLabel;
    @FXML
    private Label budgetLabel1;
    @FXML
    private Label budgetLabel2;
    @FXML
    private Label budgetLabel3;
    @FXML
    private Label budgetLabel4;
    @FXML
    private Label budgetLabel5;
    @FXML
    private ProgressBar bar1;
    @FXML
    private ProgressBar bar2;
    @FXML
    private ProgressBar bar3;
    @FXML
    private ProgressBar bar4;
    @FXML
    private ProgressBar bar5;
    @FXML
    private Label status1;
    @FXML
    private Label status2;
    @FXML
    private Label status3;
    @FXML
    private Label status4;
    @FXML
    private Label status5;


    private static double totalBudget;

    public void back(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.close(backButton);
        m.changeScene("home.fxml");
    }
    public void setTotalBudget(){
        totalBudget = Double.parseDouble(totalBudgetField.getText());
        monthlyBudgetLabel.setText("Monthly spending budget successfully set!");
    }

    List<Budget> budList = new ArrayList<Budget>();
    public void addBudget(){
        try{
            error.setText("");
            String category = (String) categories.getValue();
            double budget = Double.parseDouble(budgetAmount.getText());
            Budget b = new Budget(category, budget);
            budList.add(b);
            int count = 0;
            for (Budget bu : budList){
                count += 1;
            }
            if(count > 5){
                error.setText("Added more than 5!");
            }
            else{
                success.setText("Successfully added budget plan " + String.valueOf(count) + "/5!");
            }
        }catch(Exception e){
            success.setText("");
            error.setText("Missing or incorrect field!");
        }

    }

    public void loadView(){
        double ratio = calcOutflow()/totalBudget;
        if (ratio >= 1){
            totalBar.setProgress(1);
            totalProgressLabel.setText("Over budget!");
        } else {
            totalBar.setProgress(ratio);
            totalProgressLabel.setText("Within budget");
        }

        String categories[] = Revenues_Expenses.expenses;
        int values[] = new int[categories.length];
        int i = 0;
        for(String category : categories){
            for (Expense expense : Revenues_Expenses.expList){
                if (category.equals(expense.getCategory())){
                    values[i] += expense.getAmount();
                }
            } i++;
        }

        //2D array of labels for more efficient and easier access to two sets of labels
        Label[][] twoD_labels_array= {
                {budgetLabel1, status1},
                {budgetLabel2,status2},
                {budgetLabel3,status3},
                {budgetLabel4,status4},
                {budgetLabel5,status5}
        };
        int j = 0;
        ProgressBar[] barList = {bar1, bar2, bar3, bar4, bar5};
        for (Budget b : budList){
            twoD_labels_array[j][0].setText(b.getCategory());
            int k = 0;
            int val = 0;
            for(String category : categories){
                if (b.getCategory().equals(category)){
                    val = values[k];
                }
                k+=1;
            }
            if(val != 0){
                double ratio2 = val/b.getBudgetAmount();
                barList[j].setProgress(ratio2);
                if(ratio2>=1){
                    twoD_labels_array[j][1].setText("Over budget!");
                    // push a notification if the client is over budget using the javax.management.Notification class
                }
                else{twoD_labels_array[j][1].setText("Within budget");}

            } j+=1;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categories.getItems().addAll(Revenues_Expenses.expenses);
    }

    public static double getTotalBudget() {
        return totalBudget;
    }
}
