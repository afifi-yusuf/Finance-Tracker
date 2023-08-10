package com.example.CS_IA_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Loan extends Home implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    private Button calcButton;
    @FXML
    private ChoiceBox<String> compoundBox;
    @FXML
    private ChoiceBox<String> payBackBox;
    @FXML
    private TextField loanAmount;
    @FXML
    private TextField loanTermYears;
    @FXML
    private TextField loanTermMonths;
    @FXML
    private TextField interestRate;
    @FXML
    private Label payRate;
    @FXML
    private Label total;
    @FXML
    private Label interestLabel;
    @FXML
    private Label error;





    private String[] compound = {"Monthly (APR)", "Annually (APY)", "Semi-Annually", "Quarterly", "Semi-Monthly", "Bi-Weekly", "Weekly", "Daily"};
    private String[] payBack = {"Every Day", "Every Week", "Every 2 Weeks","Every Half Month", "Every Month","Every Quarter", "Every 6 months", "Every Year"};





    public void back(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.close(backButton);
        m.changeScene("home.fxml");

    }
    public void calc(){
       try{
            error.setText("");
            Double amount = Double.valueOf(loanAmount.getText());
            String years = loanTermYears.getText();
            double Years = Double.valueOf(years);
            String months = loanTermMonths.getText();
            double Months = Double.valueOf(months);
            Double rate = Double.valueOf(interestRate.getText());
            double time = (Months/12) + Years;
            String compound = compoundBox.getValue();
            double numCompounds = 1;
            if (compound.equals("Annually (APY)")){
                numCompounds = 1;
            }
            else if (compound.equals("Monthly (APR)")){
                numCompounds = 12;
            }
            else if (compound.equals("Semi-Annually")){
                numCompounds = 2;
            }
            else if (compound.equals("Quarterly")){
                numCompounds = 4;
            }
            else if (compound.equals("Semi-Monthly")){
                numCompounds = 24;
            }
            else if (compound.equals("Bi-Weekly")){
                numCompounds = 26;
            }
            else if (compound.equals("Weekly")){
                numCompounds = 52;
            }
            else if (compound.equals("Daily")){
                numCompounds = 365;
            }
            String pay = payBackBox.getValue();
           //private String[] payBack = {"Every Day", "Every Week", "Every 2 Weeks","Every Half Month", "Every Month","Every Quarter", "Every 6 months", "Every Year"};
           double payBackPeriod = 1;
           if (pay.equals("Every Day")){
               payBackPeriod = 365;
           }
           else if (pay.equals("Every Week")){
               payBackPeriod = 52;
           }
           else if (pay.equals("Every 2 Weeks")){
               payBackPeriod = 26;
           }
           else if (pay.equals("Every Half Month")){
               payBackPeriod = 26;
           }
           else if (pay.equals("Every Month")){
               payBackPeriod = 12;
           }
           else if (pay.equals("Every Quarter")){
               payBackPeriod = 4;
           }
           else if (pay.equals("Every 6 months")){
               payBackPeriod = 2;
           }
           double newRate = (rate/(100.0 * numCompounds));
           double totalPayment = amount * (Math.pow(1.0 + newRate, numCompounds * time));
           double totalInterest = totalPayment - amount;
           double paymentRate = totalPayment/(payBackPeriod * time);
           payRate.setText("Payment " + pay + ": $" + Double.toString(roundDoubleTwoDP(paymentRate)));
           total.setText("Total of " + Integer.toString((int)Math.round(payBackPeriod * time)) + " payments: $" + Double.toString(roundDoubleTwoDP(totalPayment)));
           interestLabel.setText("Total interest: $" + Double.toString(roundDoubleTwoDP(totalInterest)));
           }
        catch(Exception e){
           error.setText("Missing or Incorrect Field");
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        compoundBox.getItems().addAll(compound);
        payBackBox.getItems().addAll(payBack);
    }
}
