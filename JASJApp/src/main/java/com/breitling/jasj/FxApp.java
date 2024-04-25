package com.breitling.jasj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.breitling.jasj.connector.JavaConnector;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class FxApp extends Application 
{	
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(FxApp.class);

//	private JSObject javascriptConnector;
	private JavaConnector javaConnector = new JavaConnector();

	public static void main(String... args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) 
	{
		primaryStage.setTitle("JASJ");

		Browser b = new Browser();
		Scene scene = new Scene(b, 980, 660, Color.web("#666970"));

		b.webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
			if (Worker.State.SUCCEEDED == newValue) {
				JSObject window = (JSObject) b.webEngine.executeScript("window");
				window.setMember("javaConnector", javaConnector);
//				javascriptConnector = (JSObject) b.webEngine.executeScript("getJsConnector()");
//				javaConnector.setJavascriptConnector(javascriptConnector);
			}
		});

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	//INNER CLASS

	class Browser extends Region
	{
		final WebView browser = new WebView();
		final WebEngine webEngine = browser.getEngine();

		final String url = "http://localhost:8080/index.html";
		
		public Browser()
		{
			getStyleClass().add("browser");
			webEngine.load(url);
			getChildren().add(browser);
// TODO?
//			WebConsoleListener.setDefaultListener((webView, message, lineNumber, sourceId) -> {
//				if (message instanceof String) {
//					Timestamp t = new Timestamp(new Date().getTime());
//					System.out.println(t.toString().substring(11) + " [JS Console Log] DEBUG - " + message + "[at " + lineNumber + "]");
//				}
//			});
		}

		@Override 
		protected void layoutChildren() 
		{
			double w = getWidth();
			double h = getHeight();
			layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
		}

		@Override 
		protected double computePrefWidth(double height) {
			return 450;
		}

		@Override 
		protected double computePrefHeight(double width) {
			return 200;
		}
	}
}
