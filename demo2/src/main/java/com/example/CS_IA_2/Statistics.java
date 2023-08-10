package com.example.CS_IA_2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

import java.io.IOException;

public class Statistics extends Home {

    @FXML
    private Button backButton;
    @FXML
    private PieChart pieChart;
    @FXML
    private PieChart pieChart2;

    public void back(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.close(backButton);
        m.changeScene("home.fxml");

    }
    
    public void setPieChart() {

        String categories[] = Revenues_Expenses.expenses;
        int values[] = new int[categories.length];
        int i = 0;
        // nested for loop to loop through a standard Array and an ArrayList simultaneously
        for(String category : categories){
            for (Expense expense : Revenues_Expenses.expList){
                if (category.equals(expense.getCategory())){
                    values[i] += expense.getAmount();
                }
            } i++;
        }

        PieChart.Data data[] = new PieChart.Data[categories.length];
        for(int j = 0; j< categories.length;j++){
            data[j] = new PieChart.Data(categories[j], values[j]);
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(data);
        pieChart.setData(pieChartData);
    }

    public void setPieChart2() {
        String categories[] = Revenues_Expenses.revenues;
        int values[] = new int[categories.length];
        int i = 0;
        for(String category : categories){
            for (Revenue revenue : Revenues_Expenses.revList){
                if (category.equals(revenue.getCategory())){
                    values[i] += revenue.getAmount();
                }
            } i++;
        }

        PieChart.Data data[] = new PieChart.Data[categories.length];
        for(int j = 0; j< categories.length;j++){
            data[j] = new PieChart.Data(categories[j], values[j]);
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(data);
        pieChart2.setData(pieChartData);
    }
}
