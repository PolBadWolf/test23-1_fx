package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Controller_Sample controllerSample = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = (Parent) loader.load();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        controllerSample = loader.getController();
        primaryStage.show();
        (new Thread(new MyNit())).start();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private static class MyNit implements Runnable {
        private Thread thread = null;

        public void start(Thread thread) {
            this.thread = thread;
            this.thread.start();
        }
        @Override
        public void run() {
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(()->
                    internalFn1());
            Platform.runLater( ()-> controllerSample.label2.setText("lyambda label2") );
            try {
                Thread.sleep(3_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }

        private void internalFn1() {
            controllerSample.label1.setText("wait 3 second and app off");
        }
    }
}
