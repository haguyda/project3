
import db.DatabaseManager;


public class App {
 public static void main(String[] args) {
DatabaseManager.startDatabase();
DatabaseManager.endDatabase();
}

}