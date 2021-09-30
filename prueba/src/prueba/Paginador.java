package prueba;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;


public class Paginador extends Application {
	
	private Pagination pagination;
    
    public static void main(String[] args) throws Exception { //se lanza
        launch(args);
    }
 
    public int itemsPerPage() { // define los items que se muestra en cada p�gina del paginador
        return 8;
    }
 
    public VBox createPage(int pageIndex) { //crea los items que se ven en la p�gina, Vbox + mensajes con links EL M�TODO DEBUELVE EL VBOX COMPLETO
        VBox box = new VBox(5);
        int page = pageIndex * itemsPerPage();
        for (int i = page; i < page + itemsPerPage(); i++) {
            VBox element = new VBox();
            Hyperlink link = new Hyperlink("Item " + (i+1));
            link.setVisited(true);
            Label text = new Label("Search results\nfor "+ link.getText());
            element.getChildren().addAll(link, text);
            box.getChildren().add(element);
        }
        return box;
    }
 
    public void start(final Stage stage) throws Exception { //Se crea la escena y el paginador llamando al m�todo anterior con un callback desde el m�todo PageFactory
        pagination = new Pagination(28, 0);
        pagination.setStyle("-fx-border-color:red;");
        pagination.setPageFactory((Callback<Integer, Node>) new Callback<Integer, Node>() {
            public Node call(Integer pageIndex) {
                return createPage(pageIndex);
            }
        });
       
        AnchorPane anchor = new AnchorPane();
        AnchorPane.setTopAnchor(pagination, 10.0);
        AnchorPane.setRightAnchor(pagination, 10.0);
        AnchorPane.setBottomAnchor(pagination, 10.0);
        AnchorPane.setLeftAnchor(pagination, 10.0);
        anchor.getChildren().addAll(pagination);
        Scene scene = new Scene(anchor);
        stage.setScene(scene);
        stage.setTitle("PaginationSample");
        stage.show();
    }
	
}
