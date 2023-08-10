package com.example.CS_IA_2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.text.DateFormatSymbols;
import java.util.Date;

import java.io.IOException;

public class Home extends Application {

    @Override
    public void start(Stage stage) throws Exception {

    }

    String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;

    }

    @FXML
    private Button logout;
    @FXML
    private Label monthLabel;
    @FXML
    private Label overview;
    @FXML
    private Label netCashFlow;
    @FXML
    private Label remainingBudget;
    @FXML
    private Label savings;
    @FXML
    private Button revenuesExpensesButton;
    @FXML
    private Button statisticsButton;
    @FXML
    private Button loanButton;
    @FXML
    private Button recurringButton;
    @FXML
    private Button budgetButton;
    @FXML
    private Button investmentButton;
    @FXML
    private BarChart barChart;


    public void setMonthLabel() throws IOException{
        Date d = new Date();
        String currMonth = getMonthForInt(d.getMonth());
        int currYear = d.getYear() + 1900;
        String y = Integer.toString(currYear);
        monthLabel.setText(currMonth + " " + y);
        overview.setText(currMonth + " Overview");
    }
    private void set(ActionEvent actionEvent) throws IOException{
        setMonthLabel();
    }

    public double calcInflow(){
        double total = 0;
        for (Revenue revenue : Revenues_Expenses.revList){
            total += revenue.getAmount();
        }
        return total;
    }

    public double calcOutflow(){
        double total = 0;
        for (Expense expense : Revenues_Expenses.expList){
            total += expense.getAmount();
        }
        return total;
    }

    public void back(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.close(logout);
        m.changeScene("hello-view.fxml");
    }

    // method in driver class of fxml file to set bar chart
    public void setBarChart(){
        NumberAxis yAxis = new NumberAxis();
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Cash Inflow");
        series1.getData().add(new XYChart.Data("", calcInflow()));

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Cash Outflow");
        series2.getData().add(new XYChart.Data("", calcOutflow()));
        barChart.getData().addAll(series1, series2);
        double netCashflow = calcInflow()- calcOutflow();
        int ncf = (int) Math.round(netCashflow);
        netCashFlow.setText("$" + Integer.toString(ncf));
        int remBud = (int) (BudgetPlanner.getTotalBudget() - calcOutflow());
        remainingBudget.setText("$" + Integer.toString(remBud));
        savings.setText("$" + Integer.toString(ncf));
    }

    public static double sigDigRounder(double value, int nSigDig, int dir) {

        double intermediate = value/Math.pow(10,Math.floor(Math.log10(Math.abs(value)))-(nSigDig-1));

        if(dir > 0)      intermediate = Math.ceil(intermediate);
        else if (dir< 0) intermediate = Math.floor(intermediate);
        else             intermediate = Math.round(intermediate);

        double result = intermediate * Math.pow(10,Math.floor(Math.log10(Math.abs(value)))-(nSigDig-1));

        return(result);

    }
    public double roundDoubleTwoDP(double value){
        return Math.round(value*100)/100.0;
    }
    String roundOffTo2DecPlaces(float val)
    {
        return String.format("%.2f", val);
    }

    public void openRevenuesExpenses(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.close(revenuesExpensesButton);
        m.changeScene("revenuesExpenses.fxml");
    }
    public void openStatistics(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.close(statisticsButton);
        m.changeScene("statistics.fxml");
    }
    public void openLoan(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.close(loanButton);
        m.changeScene("loan.fxml");
    }
    public void openRecurring(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.close(recurringButton);
        m.changeScene("recurring.fxml");
    }
    public void openBudget(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.close(budgetButton);
        m.changeScene("budget.fxml");
    }
    public void openInvestment(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.close(investmentButton);
        m.changeScene("investment.fxml");
    }


}
