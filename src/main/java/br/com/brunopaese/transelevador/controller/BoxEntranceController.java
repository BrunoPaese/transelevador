package br.com.brunopaese.transelevador.controller;

import br.com.brunopaese.transelevador.DAO.ConnectionFactory;
import br.com.brunopaese.transelevador.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class BoxEntranceController {
    private ConnectionFactory connection;
    @FXML
    private TextField codigoSaida;
    @FXML
    private Button guardar;
    @FXML
    private CheckBox obterPeso;
    @FXML
    private TextField produtoEntrada;
    @FXML
    private TextField quantidadeEntrada;
    @FXML
    private TextField quantidadeSaida;
    @FXML
    private TextArea resumo;
    @FXML
    private Button retirar;
    @FXML
    private ChoiceBox<String> selecionaCaixaOuProduto;

    public BoxEntranceController() {
        this.connection = new ConnectionFactory();
    }

    @FXML
    private void initialize() {
        String[] option = {"Produto", "Caixa"};
        this.selecionaCaixaOuProduto.getItems().addAll(option);
        this.selecionaCaixaOuProduto.setOnAction(this::getTypeOption);
    }

    private void getTypeOption(ActionEvent actionEvent) {
        if (selecionaCaixaOuProduto.getValue() == "Caixa") {
            this.quantidadeSaida.setText("1");
            this.quantidadeSaida.setDisable(true);
        } else {
            this.quantidadeSaida.setDisable(false);
            this.quantidadeSaida.setText(null);
        }
    }

    void actionRemoveBox() {


    }

    @FXML
    private void actionSetQuantityOption() {
        if (obterPeso.isSelected()) {
            try {
                quantidadeEntrada.setDisable(true);
                quantidadeEntrada.setText(String.valueOf(definirQuantidadePeloPeso()));
            } catch (RefusedBoxException e) {
                buildErrorWindow("Peso menor do que peso unitário do produto", "Verifique se a caixa está vazia");
                obterPeso.setSelected(false);
            } catch (NumberFormatException e) {
                buildErrorWindow("Produto nulo", "Digite um produto válido");
                obterPeso.setSelected(false);
                quantidadeEntrada.setDisable(false);
            }
        } else {
            quantidadeEntrada.setDisable(false);
        }

    }

    @FXML
    void actionStoreBox() {
        try {
            new WarehouseService().storeBox(Integer.parseInt(produtoEntrada.getText()),Integer.parseInt(quantidadeEntrada.getText()));
            buildConfirmationWindow("Registrado com sucesso");
            clearFields();
            //ALTERAR DE "FREE" PARA INSIDE_THE_WAREHOUSE
        } catch (ReferenceException e) {
            buildErrorWindow("Referência inválida", "Referência deve conter cinco dígitos");
        } catch (WeightException e) {
            buildErrorWindow("Peso inválido", "Impossível peso zero");
        } catch (NullPointerException e) {
            buildErrorWindow("Dados insuficientes", "Obrigatório informar todos dados");
        } catch (NumberFormatException e) {
            buildErrorWindow("Caractere inválido", "Revise os dados digitados");
        }
    }

    @FXML
    public void actionSearchAnotherProduct(MouseEvent mouseEvent) {
        clearFields();
    }

    private int definirQuantidadePeloPeso() {
        Product product = new ProductService().searchByReference(Integer.parseInt(produtoEntrada.getText()));
        double balanca = new Balance().readWeight();
        System.out.println(balanca);
        if (balanca < product.getUnitWeight()) {
            throw new RefusedBoxException("Caixa vazia");
        }
        double quantidade = balanca / product.getUnitWeight();
        return (int) quantidade;
    }

    private void buildConfirmationWindow(String title) {
        Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
        confirmationAlert.setHeaderText(title);
        confirmationAlert.setTitle("Confirmação");
        confirmationAlert.showAndWait();
    }

    private void buildErrorWindow(String title, String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText(title);
        errorAlert.setContentText(message);
        errorAlert.showAndWait();
    }

    private void clearFields() {
        produtoEntrada.setText(null);
        quantidadeEntrada.setText(null);
        obterPeso.setSelected(false);
    }



}


