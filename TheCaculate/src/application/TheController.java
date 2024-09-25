package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;

public class TheController implements Initializable {

	@FXML
	private Button EqualBtn;

	@FXML
	private ComboBox<String> FirstCombo;

	@FXML
	private TextField FirstInt;

	@FXML
	private Button ResetBtn;

	@FXML
	private ComboBox<String> SecondCombo;

	private String[] typeList = { "+", "-", "*", "/" };

	public void ShowList() {

		List<String> typeL = new ArrayList<>();

		for (String data : typeList) {
			typeL.add(data);
		}

		ObservableList<String> listData = FXCollections.observableArrayList(typeL);
		FirstCombo.setItems(listData);
		SecondCombo.setItems(listData);
	}

	@FXML
	private TextField SecondInt;

	@FXML
	private TextField TheAns;

	@FXML
	private TextField ThirdInt;

	Pattern validIntegerPattern = Pattern.compile("\\d*");

	// Create a TextFormatter with a filter to allow only integer values
	UnaryOperator<TextFormatter.Change> integerFilter = change -> {
		String newText = change.getControlNewText();
		if (validIntegerPattern.matcher(newText).matches()) {
			return change;
		}
		return null;
	};

	TextFormatter<String> textFormatter01 = new TextFormatter<>(integerFilter);
	TextFormatter<String> textFormatter02 = new TextFormatter<>(integerFilter);
	TextFormatter<String> textFormatter03 = new TextFormatter<>(integerFilter);
	// Set the TextFormatter to the TextField

	@FXML
	void ClickEqualBtn(ActionEvent event) {

		int first = Integer.parseInt(FirstInt.getText());
		int second = Integer.parseInt(SecondInt.getText());
		int third = Integer.parseInt(ThirdInt.getText());

		String firstOperator = FirstCombo.getValue();
		String secondOperator = SecondCombo.getValue();
		int theAnswer = 0;

		switch (firstOperator) {
		case "*":
			first = first * second;
			second = third;
			firstOperator = secondOperator;
			break;
		case "/":
			if (second != 0) {
				first = first / second;
				second = third;
				first = second;
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.showAndWait();
				return;
			}
			break;
		}

		switch (firstOperator) {
		case "+":
			switch (secondOperator) {
			case "*":
				theAnswer = first + (second * third);
				break;
			case "/":
				if (third != 0) {
					theAnswer = first + (second / third);
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("ERROR");
					alert.showAndWait();
					return;
				}
				break;
			default:
				theAnswer = first + second;
				break;
			}
			break;
		case "-":
			switch (secondOperator) {
			case "*":
				theAnswer = first - (second * third);
				break;
			case "/":
				if (third != 0) {
					theAnswer = first - (second / third);
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("ERROR");
					alert.showAndWait();
					return;
				}
				break;
			default:
				theAnswer = first - second;
				break;
			}
			break;
		case "*":
			theAnswer = first * second;
			break;
		case "/":
			if (second != 0) {
				theAnswer = first / second;
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.showAndWait();
				return;
			}
			break;
		default:

			return;
		}

		TheAns.setText(theAnswer + "");
	}

	@FXML
	void ClickResetBtn(ActionEvent event) {

		FirstInt.clear();
		SecondInt.clear();
		ThirdInt.clear();
		FirstCombo.setValue(null);
		SecondCombo.setValue(null);
		TheAns.clear();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ShowList();
		FirstInt.setTextFormatter(textFormatter01);
		SecondInt.setTextFormatter(textFormatter02);
		ThirdInt.setTextFormatter(textFormatter03);

	}

}
