package com.jellybeanci;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;


public class Controller
{

    private static Timeline update;

    @FXML
    private BorderPane borderPane;
    @FXML
    private LineChart<?, ?> lineChart;
    @FXML
    private ScatterChart<?, ?> scatterChart;
    @FXML
    private AreaChart<?, ?> areaChart;
    @FXML
    private BarChart<?, ?> barChart;
    @FXML
    private StackedAreaChart<?, ?> StackedAreaChart;
    @FXML
    private LineChart<?, ?> LineChart;

    @FXML
    private CategoryAxis x;
    @FXML
    private CategoryAxis x2;
    @FXML
    private CategoryAxis x3;
    @FXML
    private CategoryAxis x4;
    @FXML
    private CategoryAxis x5;
    @FXML
    private CategoryAxis x6;

    @FXML
    private TextField textBox;

    @FXML
    private Button btnGetData;

    @FXML
    protected void onGetDataClick()
    {
        btnGetData.setDisable(true);
        Runnable r = () -> {
            String input = textBox.getText().trim();
            if (input.length() >= 1)
            {
                if (input.toLowerCase().equals("execute order 66"))
                {
                    showMessage("Execute", "ok!", Alert.AlertType.CONFIRMATION);
                } else if (input.toLowerCase().equals("done"))
                {
                    update.play();
                }
                else
                {
                    ArrayList<String> contList;
                    try
                    {
                        buttonChangeText(btnGetData, "Getting Data...");
                        contList = GetData.getDataFromAnywhere(input);
                        buttonChangeText(btnGetData, "Data Found Loading...");
                        Record.parse(contList);
                        tableView.setItems(Country.getObservableList());
                        countryListView1.setItems(Country.getObservableList().sorted());
                        countryListView2.setItems(Country.getObservableList().sorted());
                        countryListView3.setItems(Country.getObservableList().sorted());
                        countryListView4.setItems(Country.getObservableList().sorted());
                        countryListView5.setItems(Country.getObservableList().sorted());
                        countryListView6.setItems(Country.getObservableList().sorted());
                    }
                    catch (IOException e)
                    {
                        showMessage("ERROR!", "URL or File Not Found!", Alert.AlertType.ERROR);
                    }
                }
            } else
            {
                showMessage("Warning!", "Text Field can not be Empty!", Alert.AlertType.WARNING);
            }
            buttonChangeText(btnGetData, "Get Data");
            btnGetData.setDisable(false);
        };
        new Thread(r).start();
    }

    protected void buttonChangeText(Button button, String text)
    {
        Platform.runLater(() -> button.setText(text));
    }

    private void roll(double degree)
    {
        Platform.runLater(() -> {
            // Update UI here.
            this.borderPane.setRotate(borderPane.getRotate() + degree);
        });
    }


    @FXML
    protected void getSelectedCountries()
    {
        Platform.runLater(() -> {
            lineChart.getData().clear();
            ObservableList<Country> selectedCountries = countryListView1.getSelectionModel().getSelectedItems();
            SortedList<String> categories = getCategories(selectedCountries);
            x.invalidateRange(categories);
            x.autosize();
            for (Country country : selectedCountries)
            {
                XYChart.Series dataSeries = new XYChart.Series();
                dataSeries.setName(country.getCode());
                ObservableList<Record> recs = country.getRecordList();
                int total = 0;
                for (Record record : recs)
                {
                    total += record.getCases();
                    dataSeries.getData().add(new XYChart.Data<>(record.getDateString(), total));
                }
                lineChart.getData().add(dataSeries);
            }
        });
    }
    @FXML
    protected void getSelectedCountries2()
    {
        Platform.runLater(() -> {
            areaChart.getData().clear();
            ObservableList<Country> selectedCountries = countryListView2.getSelectionModel().getSelectedItems();
            SortedList<String> categories = getCategories(selectedCountries);
            x2.invalidateRange(categories);
            x2.autosize();
            for (Country country : selectedCountries)
            {
                XYChart.Series dataSeries = new XYChart.Series();
                dataSeries.setName(country.getCode());
                ObservableList<Record> recs = country.getRecordList();
                int total = 0;
                for (Record record : recs)
                {
                    total += record.getDeaths();
                    dataSeries.getData().add(new XYChart.Data<>(record.getDateString(), total));
                }
                areaChart.getData().add(dataSeries);
            }
        });
    }
    @FXML
    protected void getSelectedCountries3()
    {
        Platform.runLater(() -> {
            scatterChart.getData().clear();
            ObservableList<Country> selectedCountries = countryListView3.getSelectionModel().getSelectedItems();
            SortedList<String> categories = getCategories(selectedCountries);
            x3.invalidateRange(categories);
            x3.autosize();
            for (Country country : selectedCountries)
            {
                XYChart.Series dataSeries = new XYChart.Series();
                dataSeries.setName(country.getCode());
                ObservableList<Record> recs = country.getRecordList();
                int total = 0;
                for (Record record : recs)
                {
                    total += record.getDeaths();
                    dataSeries.getData().add(new XYChart.Data<>(record.getDateString(), total));
                }
                scatterChart.getData().add(dataSeries);
            }
        });
    }
    @FXML
    protected void getSelectedCountries4()
    {
        Platform.runLater(() -> {
            barChart.getData().clear();
            ObservableList<Country> selectedCountries = countryListView4.getSelectionModel().getSelectedItems();
            SortedList<String> categories = getCategories(selectedCountries);
            x4.invalidateRange(categories);
            x4.autosize();
            for (Country country : selectedCountries)
            {
                XYChart.Series dataSeries = new XYChart.Series();
                dataSeries.setName(country.getCode());
                ObservableList<Record> recs = country.getRecordList();
                int total = 0;
                for (Record record : recs)
                {
                    total += record.getCases();
                    dataSeries.getData().add(new XYChart.Data<>(record.getDateString(), total));
                }

                barChart.getData().add(dataSeries);
            }
        });
    }
    @FXML
    protected void getSelectedCountries5()
    {
        Platform.runLater(() -> {
            StackedAreaChart.getData();
            ObservableList<Country> selectedCountries = countryListView5.getSelectionModel().getSelectedItems();
            SortedList<String> categories = getCategories(selectedCountries);
            x5.invalidateRange(categories);
            x5.autosize();
            for (Country country : selectedCountries)
            {
                XYChart.Series dataSeries = new XYChart.Series();
                dataSeries.setName(country.getCode());
                ObservableList<Record> recs = country.getRecordList();
                int total = 0;
                for (Record record : recs)
                {
                    total += record.getDeaths();
                    dataSeries.getData().add(new XYChart.Data<>(record.getDateString(), total));
                }
                StackedAreaChart.getData().add(dataSeries);
            }
        });
    }
    @FXML
    protected void getSelectedCountries6()
    {
        Platform.runLater(() -> {
            LineChart.getData();
            ObservableList<Country> selectedCountries = countryListView6.getSelectionModel().getSelectedItems();
            SortedList<String> categories = getCategories(selectedCountries);
            x6.invalidateRange(categories);
            x6.autosize();
            for (Country country : selectedCountries)
            {
                XYChart.Series dataSeries = new XYChart.Series();
                dataSeries.setName(country.getCode());
                ObservableList<Record> recs = country.getRecordList();
                int total = 0;
                for (Record record : recs)
                {
                    total += record.getDeaths();
                    dataSeries.getData().add(new XYChart.Data<>(record.getDateString(), total));
                }
                LineChart.getData().add(dataSeries);
            }
        });
    }
    private SortedList<String> getCategories(ObservableList<Country> selectedCountries2)
    {

        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (Country country : selectedCountries2)
        {
            ObservableList<Record> records = country.getRecordList();
            for (Record record : records)
            {
                if (!observableList.contains(record.getDateString()))
                {
                    observableList.add(record.getDateString());
                }
            }
        }
        return observableList.sorted();
    }

    //
    // List View
    //
    @FXML
    private ListView<Country> countryListView1;
    @FXML
    private ListView<Country> countryListView2;
    @FXML
    private ListView<Country> countryListView3;
    @FXML
    private ListView<Country> countryListView4;
    @FXML
    private ListView<Country> countryListView5;
    @FXML
    private ListView<Country> countryListView6;
    //
    // Table View
    //
    @FXML
    private TableView<Country> tableView;

    @FXML
    private TableColumn<Country, String> countryName;

    @FXML
    private TableColumn<Country, Integer> totalCases;

    @FXML
    private TableColumn<Country, Integer> newCases;

    @FXML
    private TableColumn<Country, Integer> totalDeaths;

    @FXML
    private TableColumn<Country, Integer> newDeaths;

    @FXML
    private TableColumn<Country, Integer> population;

    @FXML
    private TableColumn<Country, Double> mortality;

    @FXML
    private TableColumn<Country, Double> attackRate;

    @FXML
    private void initialize()
    {
        update = new Timeline(new KeyFrame(Duration.millis(16), e -> {
            //60 fps
            roll(1);
        }));
        update.setCycleCount(360);
        update.setRate(1.2);
        update.setAutoReverse(false);
        lineChart.setCreateSymbols(false);
        LineChart.setCreateSymbols(false);
        areaChart.setCreateSymbols(false);
        StackedAreaChart.setCreateSymbols(false);

        //Table View
        countryName.setCellValueFactory(new PropertyValueFactory< >("name"));
        totalCases.setCellValueFactory(new PropertyValueFactory<>("totalCases"));
        newCases.setCellValueFactory(new PropertyValueFactory<>("newCases"));
        totalDeaths.setCellValueFactory(new PropertyValueFactory<>("totalDeaths"));
        newDeaths.setCellValueFactory(new PropertyValueFactory<>("newDeaths"));
        population.setCellValueFactory(new PropertyValueFactory<>("population"));
        mortality.setCellValueFactory(new PropertyValueFactory<>("mortality"));
        attackRate.setCellValueFactory(new PropertyValueFactory<>("attackRate"));
        //List View
        countryListView1.setCellFactory(new Callback<ListView<Country>, ListCell<Country>>()
        {
            @Override
            public ListCell<Country> call(ListView<Country> lv)
            {
                return new ListCell<Country>()
                {
                    @Override
                    public void updateItem(Country item, boolean empty)
                    {
                        super.updateItem(item, empty);
                        if (item == null)
                        {
                            setText(null);
                        } else
                        {
                            setText(item.getCode());
                        }
                    }
                };
            }
        });
        countryListView1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        countryListView2.setCellFactory(new Callback<ListView<Country>, ListCell<Country>>()
        {
            @Override
            public ListCell<Country> call(ListView<Country> lv)
            {
                return new ListCell<Country>()
                {
                    @Override
                    public void updateItem(Country item, boolean empty)
                    {
                        super.updateItem(item, empty);
                        if (item == null)
                        {
                            setText(null);
                        } else
                        {
                            setText(item.getCode());
                        }
                    }
                };
            }
        });
        countryListView2.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        countryListView3.setCellFactory(new Callback<ListView<Country>, ListCell<Country>>()
        {
            @Override
            public ListCell<Country> call(ListView<Country> lv)
            {
                return new ListCell<Country>()
                {
                    @Override
                    public void updateItem(Country item, boolean empty)
                    {
                        super.updateItem(item, empty);
                        if (item == null)
                        {
                            setText(null);
                        } else
                        {
                            setText(item.getCode());
                        }
                    }
                };
            }
        });
        countryListView3.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        countryListView4.setCellFactory(new Callback<ListView<Country>, ListCell<Country>>()
        {
            @Override
            public ListCell<Country> call(ListView<Country> lv)
            {
                return new ListCell<Country>()
                {
                    @Override
                    public void updateItem(Country item, boolean empty)
                    {
                        super.updateItem(item, empty);
                        if (item == null)
                        {
                            setText(null);
                        } else
                        {
                            setText(item.getCode());
                        }
                    }
                };
            }
        });
        countryListView4.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        countryListView5.setCellFactory(new Callback<ListView<Country>, ListCell<Country>>()
        {
            @Override
            public ListCell<Country> call(ListView<Country> lv)
            {
                return new ListCell<Country>()
                {
                    @Override
                    public void updateItem(Country item, boolean empty)
                    {
                        super.updateItem(item, empty);
                        if (item == null)
                        {
                            setText(null);
                        } else
                        {
                            setText(item.getCode());
                        }
                    }
                };
            }
        });
        countryListView5.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        countryListView6.setCellFactory(new Callback<ListView<Country>, ListCell<Country>>()
        {
            @Override
            public ListCell<Country> call(ListView<Country> lv)
            {
                return new ListCell<Country>()
                {
                    @Override
                    public void updateItem(Country item, boolean empty)
                    {
                        super.updateItem(item, empty);
                        if (item == null)
                        {
                            setText(null);
                        } else
                        {
                            setText(item.getCode());
                        }
                    }
                };
            }
        });
        countryListView6.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    private static void showMessage(String title, String message, Alert.AlertType alertType)
    {
        Platform.runLater(() -> {
            //UI
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
}