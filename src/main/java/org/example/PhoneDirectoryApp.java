package org.example;

/**
 *
 * Реализуйте простой графический интерфейс для вашего телефонного справочника из предыдущего задания с использованием JavaFX или Swing.
 * Создать справочник сотрудников
 * Необходимо:
 * Создать класс справочник сотрудников, который содержит внутри
 * коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:
 * Табельный номер
 * Номер телефона
 * Имя
 * Стаж
 * Добавить метод, который ищет сотрудника по стажу (может быть список)
 * Добавить метод, который возвращает номер телефона сотрудника по имени (может быть список)
 * Добавить метод, который ищет сотрудника по табельному номеру
 * Добавить метод добавления нового сотрудника в справочник
 *
 */


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class PhoneDirectoryApp extends Application {
    private EmployeeDirectory directory = new EmployeeDirectory();
    private TextArea outputArea = new TextArea();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Телефонный справочник");

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label idLabel = new Label("ID:");
        TextField idField = new TextField();
        Label nameLabel = new Label("Имя:");
        TextField nameField = new TextField();
        Label phoneLabel = new Label("Телефон:");
        TextField phoneField = new TextField();
        Label experienceLabel = new Label("Стаж:");
        TextField experienceField = new TextField();
        Button addButton = new Button("Добавить сотрудника");
        addButton.setOnAction(e -> addEmployee(idField, nameField, phoneField, experienceField));

        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(nameLabel, 0, 1);
        gridPane.add(nameField, 1, 1);
        gridPane.add(phoneLabel, 0, 2);
        gridPane.add(phoneField, 1, 2);
        gridPane.add(experienceLabel, 0, 3);
        gridPane.add(experienceField, 1, 3);
        gridPane.add(addButton, 1, 4);

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(gridPane, createSearchSection(), outputArea);

        Scene scene = new Scene(vBox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createSearchSection() {
        VBox vBox = new VBox(10);

        Label searchLabel = new Label("Поиск по стажу:");
        TextField searchField = new TextField();
        Button searchButton = new Button("Поиск");
        searchButton.setOnAction(e -> searchByExperience(searchField));

        Label phoneSearchLabel = new Label("Поиск номера по имени:");
        TextField phoneSearchField = new TextField();
        Button phoneSearchButton = new Button("Поиск");
        phoneSearchButton.setOnAction(e -> searchPhoneByName(phoneSearchField));

        Label idSearchLabel = new Label("Поиск по табельному номеру:");
        TextField idSearchField = new TextField();
        Button idSearchButton = new Button("Поиск");
        idSearchButton.setOnAction(e -> searchById(idSearchField));

        vBox.getChildren().addAll(searchLabel, searchField, searchButton, phoneSearchLabel, phoneSearchField, phoneSearchButton, idSearchLabel, idSearchField, idSearchButton);
        return vBox;
    }

    private void addEmployee(TextField idField, TextField nameField, TextField phoneField, TextField experienceField) {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String phone = phoneField.getText();
        int experience = Integer.parseInt(experienceField.getText());

        Employee employee = new Employee(id, phone, name, experience);
        directory.addEmployee(employee);
        outputArea.appendText("Сотрудник добавлен: " + employee + "\n");
    }

    private void searchByExperience(TextField searchField) {
        int experience = Integer.parseInt(searchField.getText());
        List<Employee> employees = directory.findEmployeesByExperience(experience);
        outputArea.appendText("Сотрудники со стажем " + experience + " лет: " + employees + "\n");
    }

    private void searchPhoneByName(TextField searchField) {
        List<String> phoneNumbers = directory.findPhoneNumbersByName(searchField.getText());
        outputArea.appendText("Номера телефонов: " + phoneNumbers + "\n");
    }

    private void searchById(TextField searchField) {
        int id = Integer.parseInt(searchField.getText());
        Employee employee = directory.findEmployeeById(id);
        outputArea.appendText("Сотрудник с ID " + id + ": " + employee + "\n");
    }
}
