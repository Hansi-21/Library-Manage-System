package controller;

import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeFormController implements Initializable {

    public LineChart chart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setChart();
    }

    public void  setChart(){
        XYChart.Series  BookSeries = new XYChart.Series<>();
        BookSeries.setName("Action");

        BookSeries.getData().add(new XYChart.Data<>("1",2));
        BookSeries.getData().add(new XYChart.Data<>("2",11));
        BookSeries.getData().add(new XYChart.Data<>("3",8));
        BookSeries.getData().add(new XYChart.Data<>("4",4));
        BookSeries.getData().add(new XYChart.Data<>("5",10));
        BookSeries.getData().add(new XYChart.Data<>("6",12));
        BookSeries.getData().add(new XYChart.Data<>("7",5));



        XYChart.Series  BookSeries1 = new XYChart.Series<>();
        BookSeries1.setName("Classic");

        BookSeries1.getData().add(new XYChart.Data<>("1",5));
        BookSeries1.getData().add(new XYChart.Data<>("2",2));
        BookSeries1.getData().add(new XYChart.Data<>("3",10));
        BookSeries1.getData().add(new XYChart.Data<>("4",9));
        BookSeries1.getData().add(new XYChart.Data<>("5",3));
        BookSeries1.getData().add(new XYChart.Data<>("6",11));
        BookSeries1.getData().add(new XYChart.Data<>("7",7));



        XYChart.Series  BookSeries2 = new XYChart.Series<>();
        BookSeries2.setName("Horror");

        BookSeries2.getData().add(new XYChart.Data<>("1",9));
        BookSeries2.getData().add(new XYChart.Data<>("2",2));
        BookSeries2.getData().add(new XYChart.Data<>("3",1));
        BookSeries2.getData().add(new XYChart.Data<>("4",12));
        BookSeries2.getData().add(new XYChart.Data<>("5",3));
        BookSeries2.getData().add(new XYChart.Data<>("6",11));
        BookSeries2.getData().add(new XYChart.Data<>("7",7));



        XYChart.Series  BookSeries3 = new XYChart.Series<>();
        BookSeries3.setName("Novel");

        BookSeries3.getData().add(new XYChart.Data<>("1",1));
        BookSeries3.getData().add(new XYChart.Data<>("2",5));
        BookSeries3.getData().add(new XYChart.Data<>("3",10));
        BookSeries3.getData().add(new XYChart.Data<>("4",9));
        BookSeries3.getData().add(new XYChart.Data<>("5",3));
        BookSeries3.getData().add(new XYChart.Data<>("6",11));
        BookSeries3.getData().add(new XYChart.Data<>("7",9));



        XYChart.Series  BookSeries4 = new XYChart.Series<>();
        BookSeries4.setName("Comic");

        BookSeries4.getData().add(new XYChart.Data<>("1",13));
        BookSeries4.getData().add(new XYChart.Data<>("2",9));
        BookSeries4.getData().add(new XYChart.Data<>("3",10));
        BookSeries4.getData().add(new XYChart.Data<>("4",12));
        BookSeries4.getData().add(new XYChart.Data<>("5",3));
        BookSeries4.getData().add(new XYChart.Data<>("6",7));
        BookSeries4.getData().add(new XYChart.Data<>("7",4));

        chart.getData().addAll(BookSeries,BookSeries1,BookSeries2,BookSeries3,BookSeries4);


    }
}
