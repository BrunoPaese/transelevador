package br.com.brunopaese.transelevador.controller;

import br.com.brunopaese.transelevador.DAO.ConnectionFactory;
import br.com.brunopaese.transelevador.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class ProductRegistrationController {
    @FXML
    private TextField cadastroDescricao;
    @FXML
    private TextField cadastroPesoUnitario;
    @FXML
    private TextField cadastroReferencia;
    @FXML
    private TextField editaDescricao;
    @FXML
    private TextField editaPesoUnitario;
    @FXML
    private TextField editaReferencia;
    @FXML
    private CheckBox foraDeLinha;

    @FXML
    private Button editar;
    @FXML
    private Button cadastrar;
    @FXML
    private Button localizar;
    @FXML
    private ImageView lupa;

    private ConnectionFactory connection;

    public ProductRegistrationController() {
        this.connection = new ConnectionFactory();
    }

    @FXML
    private void initialize() {
        cadastroDescricao.setText(null);
        cadastroPesoUnitario.setText(null);
        cadastroReferencia.setText(null);
    }

    @FXML
    private void actionRegister() {
        try {
            new ProductService().register(
                    cadastroReferencia.getText().isEmpty() ? null : Integer.parseInt(cadastroReferencia.getText()),
                    cadastroDescricao.getText().isEmpty() ? null : cadastroDescricao.getText(), //nao lanca exception correta quando campo nulo
                    cadastroPesoUnitario.getText().isEmpty() ? null : Integer.parseInt(cadastroPesoUnitario.getText()));
            buildConfirmationWindow("Registrado com sucesso");
            clearFields();
        } catch (ReferenceException e) {
            buildErrorWindow("Referência inválida", "Referência deve conter cinco dígitos");
        } catch (WeightException e) {
            buildErrorWindow("Peso inválido", "Impossível peso zero");
        } catch (NullPointerException e) {
            buildErrorWindow("Dados insuficientes", "Obrigatório informar todos dados");
        } catch (NumberFormatException e) {
            buildErrorWindow("Caractere inválido", "Revise os dados digitados");
        } catch (RuntimeException e) {
            buildErrorWindow("Referência já cadastrada", "Revise a referência digitada");
            clearFields();
        }
    }

    @FXML
    private void actionSearchByReference() {
       try {
           Product product = new ProductService().searchByReference(Integer.parseInt(editaReferencia.getText()));
           editaDescricao.setText(product.getDescription());
           editaPesoUnitario.setText(String.valueOf(product.getUnitWeight()));
           foraDeLinha.setSelected(!product.isInUse());
           permissionToEdit(true);
       } catch (NumberFormatException e) {
           buildErrorWindow("Referência inválida", "Referência do produto deve conter números");
           clearFields();
           permissionToEdit(false);
       } catch (NullPointerException e) {
           buildErrorWindow("Produto não encontrado", "Confira a referência digitada");
           clearFields();
           permissionToEdit(false);
       }
    }

    @FXML
    private void actionChange() {
        try {
            new ProductService().changes(Integer.parseInt(editaReferencia.getText()), editaDescricao.getText(), Double.parseDouble(editaPesoUnitario.getText()), !foraDeLinha.isSelected());
            buildConfirmationWindow("Alterado com sucesso");
            clearFields();
            permissionToEdit(false);
        } catch (NumberFormatException e) {
            buildErrorWindow("Entrada inválida", "Peso do produto deve conter números" );
        }
    }

    @FXML
    public void actionSearchAnotherProduct(MouseEvent mouseEvent) {
        clearFields();
        permissionToEdit(false);
    }

    private void buildErrorWindow(String title, String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText(title);
        errorAlert.setContentText(message);
        errorAlert.showAndWait();
    }

    private void buildConfirmationWindow(String title) {
        Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
        confirmationAlert.setHeaderText(title);
        confirmationAlert.setTitle("Confirmação");
        confirmationAlert.showAndWait();
    }

    private void clearFields() {
        cadastroReferencia.setText(null);
        cadastroDescricao.setText(null);
        cadastroPesoUnitario.setText(null);
        editaReferencia.setText(null);
        editaDescricao.setText(null);
        editaPesoUnitario.setText(null);
        foraDeLinha.setSelected(false);
    }

    private void permissionToEdit(Boolean permission) {
        foraDeLinha.setDisable(!permission);
        editaDescricao.setDisable(!permission);
        editaPesoUnitario.setDisable(!permission);
        editar.setDisable(!permission);
    }
}


