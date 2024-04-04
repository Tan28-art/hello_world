package asuHelloWorldJavaFX;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ASUHelloWorldJavaFX extends Application 
{
    CheckBox eggSandwich;
    CheckBox bagel;
    CheckBox potatoSalad;
    CheckBox chickenSandwich;

    RadioButton blackTea;
    RadioButton greenTea;
    RadioButton coffee;
    RadioButton orangeJuice;

    Label header;
    Label billLabel;
    Label eatLabel;
    Label drinkLabel;
    Label orderLabel;

    double totalCost;
    double totalWithTax;

    String orderSummary;

    public void start(Stage primaryStage) 
    {
        //Header
        header = new Label("Joe's Deli");
        header.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
        header.setPadding(new Insets(10));


        //Eat section
        eatLabel = new Label("Eat:");
        eatLabel.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");
        eatLabel.setPadding(new Insets(10,0,20,0));
        eggSandwich = new CheckBox("Egg Sandwich");
        bagel = new CheckBox("Bagel");
        potatoSalad = new CheckBox("Potato salad");
        chickenSandwich = new CheckBox("Chicken Sandwich");

        VBox eatVbox = new VBox(20,eatLabel, eggSandwich, bagel, potatoSalad, chickenSandwich);
        eatVbox.setPadding(new Insets(10,50,50,50));

        //Drink section
        drinkLabel = new Label("Drink:");
        drinkLabel.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");
        drinkLabel.setPadding(new Insets(10,0,20,0));
        blackTea = new RadioButton("Black Tea");
        greenTea = new RadioButton("Green Tea");
        coffee = new RadioButton("Coffee");
        orangeJuice = new RadioButton("Orange Juice");

        ToggleGroup drinkToggleGroup = new ToggleGroup();
        blackTea.setToggleGroup(drinkToggleGroup);  
        greenTea.setToggleGroup(drinkToggleGroup);
        coffee.setToggleGroup(drinkToggleGroup);
        orangeJuice.setToggleGroup(drinkToggleGroup);

        VBox drinkVbox = new VBox(20, drinkLabel, blackTea, greenTea, coffee, orangeJuice);
        drinkVbox.setPadding(new Insets(10,70,50,50));

        //Bill label
        billLabel = new Label("Bill");
        billLabel.setPadding(new Insets(10));
        billLabel.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");
        orderLabel = new Label();
        //billLabel.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-style: solid; ");
        
        VBox billVBox = new VBox(billLabel, orderLabel);
        billVBox.setPadding(new Insets(10,50,50,50));
        billVBox.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-style: solid; -fx-min-width: 200;");
        billVBox.setAlignment(Pos.TOP_CENTER);

        //Order, Cancel and Confirm buttons
        Button orderButton = new Button("Order");
        Button cancelButton = new Button("Cancel");
        Button confirmButton = new Button("Confirm");

        //Event handlers
        orderButton.setOnAction(e -> handleOrder());
        cancelButton.setOnAction(e -> handleCancel());
        confirmButton.setOnAction(e -> handleConfirm());

        //HBox for buttons
        HBox buttonHbox = new HBox(50, orderButton, cancelButton, confirmButton);
        buttonHbox.setPadding(new Insets(50));

        //EatVbox, drinkVbox, and billLabel to an HBox
        HBox itemsHBox = new HBox(10, eatVbox, drinkVbox, billVBox);
        

        //VBox for vertical alignment
        VBox mainVBox = new VBox(10, header, itemsHBox, buttonHbox);
        mainVBox.setAlignment(Pos.TOP_CENTER);
        mainVBox.setPadding(new Insets(10)); 
        mainVBox.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-style: solid;");


        //Wrap mainVBox in a BorderPane
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(mainVBox);
        borderPane.setPadding(new Insets(20));

        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Joe's Deli");
        primaryStage.show();
    }

    private void handleOrder() 
    {
        // Calculate total cost without tax
        totalCost = calculateTotalCost();
        orderSummary = "";
    
        if (eggSandwich.isSelected()) {
            orderSummary += "Egg Sandwich: $7.99\n";
        }
        if (bagel.isSelected()) {
            orderSummary += "Bagel: $2.50\n";
        }
        if (potatoSalad.isSelected()) {
            orderSummary += "Potato Salad: $4.49\n";
        }
        if (chickenSandwich.isSelected()) {
            orderSummary += "Chicken Sandwich: $9.99\n";
        }
        if (blackTea.isSelected()) {
            orderSummary += "Black Tea: $1.25\n";
        }
        if (greenTea.isSelected()) {
            orderSummary += "Green Tea: $0.99\n";
        }
        if (coffee.isSelected()) {
            orderSummary += "Coffee: $1.99\n";
        }
        if (orangeJuice.isSelected()) {
            orderSummary += "Orange Juice: $2.25\n";
        }
    
        orderSummary += String.format("Total Cost: $%.2f\n", totalCost);
        
        orderLabel.setText(orderSummary);
    }
    

    private void handleCancel() 
    {
        //Clear everthing
        clear();
        orderLabel.setText("");
    }

    private void handleConfirm() 
    {
        totalCost = calculateTotalCost();
        totalWithTax = calculateTotalCost() * 1.07; //tax
        orderSummary += String.format("Final Total: $%.2f",totalWithTax);
        orderLabel.setText(orderSummary);
        clear();

    }

    private double calculateTotalCost() 
    {
        //Calculate total cost
        double totalCost = 0.0;

        if (eggSandwich.isSelected()) {
            totalCost += 7.99;
        }
        if (bagel.isSelected()) {
            totalCost += 2.50;
        }
        if (potatoSalad.isSelected()) {
            totalCost += 4.49;
        }
        if (chickenSandwich.isSelected()) {
            totalCost += 9.99;
        }
        if (blackTea.isSelected()) {
            totalCost += 1.25;
        }
        if (greenTea.isSelected()) {
            totalCost += 0.99;
        }
        if (coffee.isSelected()) {
            totalCost += 1.99;
        }
        if (orangeJuice.isSelected()) {
            totalCost += 2.25;
        }

        return totalCost;
    }

    private void clear() 
    {
        eggSandwich.setSelected(false);
        bagel.setSelected(false);
        potatoSalad.setSelected(false);
        chickenSandwich.setSelected(false);
        blackTea.setSelected(false);
        greenTea.setSelected(false);
        coffee.setSelected(false);
        orangeJuice.setSelected(false);
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
}
