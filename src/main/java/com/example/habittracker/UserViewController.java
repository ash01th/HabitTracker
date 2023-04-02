package com.example.habittracker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserViewController implements Initializable {
    @FXML
    private TableView<HabitDataModel> HabitDataTableView;
    @FXML
    private TableColumn<HabitDataModel,String> UserNameTableColumn;
    @FXML
    private TableColumn<HabitDataModel,String> HabitNameTableColumn;
    @FXML
    private TableColumn<HabitDataModel,Integer> DaysDoneTableColumn;
    @FXML
    private TableColumn<HabitDataModel,Integer> DaysMissedTableColumn;
    @FXML
    private TableColumn<HabitDataModel,Integer>CurrentStreakTableColumn;
    @FXML
    private TableColumn<HabitDataModel,Integer>LongestStreakTableColumn;
    @FXML
    private TextField KeyWordsTextField;
    @FXML
    private Button GetLogButton;

    ObservableList<HabitDataModel> HabitDataModelObservableList= FXCollections.observableArrayList();
    public void  ExitButtonAction(ActionEvent e) {
        MyLogger LogGenerator=new MyLogger();
        try{
            LogGenerator.main();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }
    NameHolder N1=new NameHolder();
    String UserName1=N1.UserName;
    @Override
    public void initialize(URL url, ResourceBundle resource){
        DataBaseConnection connectNow= new DataBaseConnection();
        Connection ConnectDB=connectNow.getDBConnection();
        String DataQuery="SELECT UserName,HabitName,DaysDone,DaysMissed,CurrentStreak,LongestStreak FROM data where Username='"+UserName1+"';";
        try{
            Statement statement= ConnectDB.createStatement();
            ResultSet QueryOutput=statement.executeQuery(DataQuery);
            while (QueryOutput.next()){
                String QueryUserName=QueryOutput.getString("UserName");
                String QueryHabitName=QueryOutput.getString("HabitName");
                Integer QueryDaysDone=QueryOutput.getInt("DaysDone");
                Integer QueryDaysMissed=QueryOutput.getInt("DaysMissed");
                Integer QueryCurrentStreak=QueryOutput.getInt("CurrentStreak");
                Integer QueryLongestStreak=QueryOutput.getInt("LongestStreak");
                HabitDataModelObservableList.add(new HabitDataModel(QueryUserName,QueryHabitName,QueryDaysDone,QueryDaysMissed,QueryCurrentStreak,QueryLongestStreak));
            }
            UserNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("UserName"));
            HabitNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("HabitName"));
            DaysDoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("DaysDone"));
            DaysMissedTableColumn.setCellValueFactory(new PropertyValueFactory<>("DaysMissed"));
            CurrentStreakTableColumn.setCellValueFactory(new PropertyValueFactory<>("CurrentStreak"));
            LongestStreakTableColumn.setCellValueFactory(new PropertyValueFactory<>("LongestStreak"));

            HabitDataTableView.setItems(HabitDataModelObservableList);

            FilteredList<HabitDataModel> FilteredData= new FilteredList<>(HabitDataModelObservableList,b->true);
            KeyWordsTextField.textProperty().addListener((observableValue,oldValue,newValue) ->{
                FilteredData.setPredicate(HabitDataModel->{

                    if(newValue.isEmpty() || newValue.isBlank() || newValue==null){
                        return true;
                    }

                    String SearchKeyword=newValue.toLowerCase();
                    if(HabitDataModel.getUserName().toLowerCase().indexOf(SearchKeyword)>-1){
                        return true;

                    } else if (HabitDataModel.getHabitName().toLowerCase().indexOf(SearchKeyword)>-1) {
                        return true;
                    } else if (HabitDataModel.getDaysDone().toString().indexOf(SearchKeyword)>-1) {
                        return true;
                    } else if (HabitDataModel.getDaysMissed().toString().indexOf(SearchKeyword)>-1) {
                        return true;
                    } else if (HabitDataModel.getCurrentStreak().toString().indexOf(SearchKeyword)>-1) {
                        return true;
                    } else if (HabitDataModel.getLongestStreak().toString().indexOf(SearchKeyword)>-1) {
                        return true;
                    }
                    else{
                        return false;
                    }

                });
            } );
            SortedList<HabitDataModel> SortedData=new SortedList<>(FilteredData);
            SortedData.comparatorProperty().bind(HabitDataTableView.comparatorProperty());
            HabitDataTableView.setItems(SortedData);
        }
        catch(SQLException e)
        {
            Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE,null,e);

        }
    }
}
