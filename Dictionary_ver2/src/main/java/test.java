import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class test extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Fade Transition Example");

        // Tạo một nút
        Button button = new Button("Click me!");

        // Tạo đối tượng FadeTransition với thời gian mờ là 1 giây
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), button);

        // Đặt giá trị opacity ban đầu là 1 (hoàn toàn rõ)
        button.setOpacity(1.0);


        // Thiết lập giá trị opacity cuối cùng là 0 (hoàn toàn mờ)
        fadeTransition.setToValue(0.0);

        // Thiết lập sự kiện khi hoàn thành chuyển động
        fadeTransition.setOnFinished(event -> {
            System.out.println("Transition Finished");
        });

        // Thiết lập sự kiện khi nút được nhấn
        button.setOnAction(event -> {
            // Bắt đầu chuyển động khi nút được nhấn
            fadeTransition.play();
        });

        // Tạo layout và thêm nút vào đó
        StackPane root = new StackPane();
        root.getChildren().add(button);

        // Tạo scene và đặt nó vào primaryStage
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setScene(scene);

        // Hiển thị primaryStage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
