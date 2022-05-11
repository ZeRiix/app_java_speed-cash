package com.example.apployaltyfx;

//import java.awt.*;
import java.sql.*;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GridPane grid0 = new GridPane();
        grid0.setAlignment(Pos.CENTER);
        grid0.setHgap(10);
        grid0.setVgap(10);
        grid0.setPadding(new Insets(25, 25, 25, 25));

        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(grid0, 600, 500);

        stage.setScene(scene);

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid0.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid0.add(userName, 0, 1);
        TextField userTextField = new TextField();
        grid0.add(userTextField, 1, 1);
        Label pw = new Label("Password:");
        grid0.add(pw, 0, 2);
        PasswordField pwBox = new PasswordField();
        grid0.add(pwBox, 1, 2);

        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid0.add(hbBtn, 1, 4);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String id = userTextField.getText();
                String pwd = pwBox.getText();
                printUserResults(id,pwd,grid0);
            }
        });



        stage.show();
    }

    public static void printUserResults(String IdInput, String PasswordInput, GridPane grid) {

        Stage window = new Stage();
        GridPane grid1 = new GridPane();
        for (int i = 0; i < 10; i++) {
            ColumnConstraints column = new ColumnConstraints(200);
            grid1.getColumnConstraints().add(column);
        }
        grid1.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid1, 1500, 1200);

        //window.setFullScreen(true);
        window.setScene(scene);
        window.show();

        Label actionres = new Label();
        actionres.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid1.add(actionres, 0,9);

        // Promo Insertion

        TextField codefield = new TextField("Code");
        grid1.add(codefield, 0,2);

        TextField reduxField = new TextField("Reduction");
        grid1.add(reduxField, 1,2);

        TextField expField = new TextField("YYYY-MM-DD");
        grid1.add(expField, 2,2);

        TextField deletionField = new TextField("ID Code Promo");
        grid1.add(deletionField, 0,3);

        Button createPromo = new Button("Ajouter");
        grid1.add(createPromo, 3,2);

        Button deletePromo = new Button("Supprimer");
        grid1.add(deletePromo, 3,3);

        final Text code = new Text("Ajout et suppression de code promo : ");
        grid1.add(code,0,1);

        /*
        final Text target = new Text("Reduction");
        grid1.add(target,1,1);

        final Text expiry = new Text("Expiration format YYYY-MM-DD");
        grid1.add(expiry, 2,1);
        */

        deletePromo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String delCode = new String(deletionField.getText());
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con=DriverManager.getConnection(
                            "jdbc:mysql://192.168.160.26:3306/speed-cash","user2","bddSrv1234@");
                    String deletePromoQuery = "delete from code_promo where id = '"+ delCode + "' ";
                    Statement stmt=con.prepareStatement(deletePromoQuery);
                    //ResultSet insertion=stmt.executeQuery(insertOfferQuery);
                    stmt.executeUpdate(deletePromoQuery);
                    System.out.println("Connected");
                }
                catch (Exception cr){
                    System.err.println(cr);
                    cr.printStackTrace();
                }
                actionres.setText("Promo supprimée");
            }
        });

        createPromo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String codeVal = new String(codefield.getText());
                String reduxVal = new String(reduxField.getText());
                String expiryVal = new String(expField.getText());
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con=DriverManager.getConnection(
                            "jdbc:mysql://192.168.160.26:3306/speed-cash","user2","bddSrv1234@");
                    String insertOfferQuery = "insert into code_promo(code_name, reduction, date_expiry) values('" + codeVal + "', '" + reduxVal + "', '" + expiryVal + "')";
                    Statement stmt=con.prepareStatement(insertOfferQuery);
                    //ResultSet insertion=stmt.executeQuery(insertOfferQuery);
                    stmt.executeUpdate(insertOfferQuery);
                    System.out.println("Connected");
                }
                catch (Exception cr){
                    System.err.println(cr);
                    cr.printStackTrace();
                }
                actionres.setText("Promo créée");
            }
        });



        // Product Insertion

        TextField name = new TextField("Nom");
        grid1.add(name, 0,5);

        TextField brand = new TextField("Marque");
        grid1.add(brand, 1,5);

        TextField cat = new TextField("Categorie");
        grid1.add(cat, 2,5);

        TextField price = new TextField("Prix");
        grid1.add(price, 3,5);

        TextArea desc = new TextArea();
        grid1.add(desc, 4,5);

        Button createProduct = new Button("Ajouter");
        grid1.add(createProduct, 5,5);

        TextField prodDeletion = new TextField("ID Produit");
        grid1.add(prodDeletion, 0, 6);

        Button deleteProduct = new Button("Supprimer");
        grid1.add(deleteProduct, 5,6);

        createProduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String nameVal = new String(name.getText());
                String brandVal = new String(brand.getText());
                String catVal = new String(cat.getText());
                String priceVal = new String(price.getText());
                String descVal = new String(desc.getText());
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con=DriverManager.getConnection(
                            "jdbc:mysql://192.168.160.26:3306/speed-cash","user2","bddSrv1234@");
                    String insertOfferQuery = "insert into produit(id, nom, marque, categorie, prix, description, id_fiche_tech, quantite) values('1', '" + nameVal + " ', '" + brandVal + "', '" + catVal + "', '"+ priceVal +"', '" + descVal +"', '0', '100')";
                    Statement stmt=con.prepareStatement(insertOfferQuery);
                    //ResultSet insertion=stmt.executeQuery(insertOfferQuery);
                    stmt.executeUpdate(insertOfferQuery);
                    System.out.println("Connected");
                }
                catch (Exception co){
                    System.err.println(co);
                    co.printStackTrace();
                }
                actionres.setText("Produit ajouté");
            }
        });

        deleteProduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String delProd = new String(prodDeletion.getText());
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con=DriverManager.getConnection(
                            "jdbc:mysql://192.168.160.26:3306/speed-cash","user2","bddSrv1234@");
                    String deleteProdQuery = "delete from produit where id = '"+ delProd + "' ";
                    Statement stmt=con.prepareStatement(deleteProdQuery);
                    //ResultSet insertion=stmt.executeQuery(insertOfferQuery);
                    stmt.executeUpdate(deleteProdQuery);
                    System.out.println("Connected");
                }
                catch (Exception cp){
                    System.err.println(cp);
                    cp.printStackTrace();
                }
                actionres.setText("Produit supprimé");
            }
        });


        final Text prodBanner = new Text("Ajout et suppression de produits/prestations : ");
        grid1.add(prodBanner,0,4);

        /*
        final Text pbrand = new Text("Marque");
        grid1.add(pbrand,1,3);

        final Text pcat = new Text("Categorie");
        grid1.add(pcat,2,3);

        final Text pprice = new Text("Prix");
        grid1.add(pprice,3,3);
        */

        //

        //Button viewPromos = new Button();
        //grid1.add(viewPromos, 1,8);

        final Text resbox = new Text();
        resbox.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid1.add(resbox, 6, 1);

        if (!IdInput.isEmpty() || !PasswordInput.isEmpty()) {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con=DriverManager.getConnection(
                        "jdbc:mysql://192.168.160.26:3306/speed-cash","user2","bddSrv1234@");

                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery("select * from client where user = '" + IdInput + "'" + "and password = '" + PasswordInput + "'");

                Statement stmt1=con.createStatement();
                ResultSet rs1=stmt1.executeQuery("select * from produit order by id DESC");
                ResultSetMetaData rs1md = rs1.getMetaData();
                Integer rs1cols = rs1md.getColumnCount();

                Statement stmt2=con.createStatement();
                ResultSet rs2=stmt2.executeQuery("select * from code_promo order by id DESC ");
                ResultSetMetaData rs2md = rs2.getMetaData();
                Integer rs2cols = rs2md.getColumnCount();

                System.out.println("Connected");

                if(rs.next()) {
                    //System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
                    resbox.setText(("Bienvenue " + rs.getString("user")));
                    //con.close();

                    /*
                    Tab tab1 = new Tab(rs1.getString("id"));
                    TableView tableView1 = new TableView();
                    tabPane.getTabs().add(tab1);
                    tab.setContent(tableView);
                     */
                    TableView<ObservableList> products = new TableView<>();
                    grid1.add(products, 5,9);
                    ObservableList<ObservableList> data1 = FXCollections.observableArrayList();
                    for(int i=0 ; i<rs1.getMetaData().getColumnCount(); i++){
                        //We are using non property style for making dynamic table
                        final int j = i;
                        TableColumn col = new TableColumn(rs1.getMetaData().getColumnName(i+1));
                        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                                return new SimpleStringProperty(param.getValue().get(j).toString());
                            }
                        });

                        products.getColumns().addAll(col);
                        System.out.println("Column ["+i+"] ");
                    }

                    while (rs1.next()) {
                        ObservableList<String> row = FXCollections.observableArrayList();
                        for(int i=1 ; i<=rs1.getMetaData().getColumnCount(); i++){
                            //Iterate Column
                            row.add(rs1.getString(i));
                        }
                        System.out.println("Row [1] added "+row );
                        data1.add(row);
                    }
                    products.setItems(data1);

                    TableView<ObservableList> promos = new TableView<>();
                    grid1.add(promos, 6,9);
                    ObservableList<ObservableList> data2 = FXCollections.observableArrayList();
                    for(int i=0 ; i<rs2.getMetaData().getColumnCount(); i++){
                        //We are using non property style for making dynamic table
                        final int j = i;
                        TableColumn col = new TableColumn(rs2.getMetaData().getColumnName(i+1));
                        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                                return new SimpleStringProperty(param.getValue().get(j).toString());
                            }
                        });

                        promos.getColumns().addAll(col);
                        System.out.println("Column ["+i+"] ");
                    }

                    while (rs2.next()) {
                        ObservableList<String> row = FXCollections.observableArrayList();
                        for(int i=1 ; i<=rs2.getMetaData().getColumnCount(); i++){
                            //Iterate Column
                            row.add(rs2.getString(i));
                        }
                        System.out.println("Row [1] added "+row );
                        data2.add(row);
                    }
                    promos.setItems(data2);

                }else{
                    window.close();
                }

            }
            catch(Exception ex)
            {
                System.err.println(ex);
                ex.printStackTrace();
            }
        }else{
            //JOptionPane.showMessageDialog(null, "Please enter a user name");
            resbox.setText("Wrong Username or Password");
            window.close();
            System.out.println("Username insertion error");
        }

    }

    public static void main(String[] args) {
        launch();
    }
}