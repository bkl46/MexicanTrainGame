import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;

public class About{
  
  private static Stage stage = new Stage();
  private static String text = "Rules: \n"+
    "every turn may play a domino on your train, an open train, or the mexican train\n"+
    "can only play a domino on a train if that domino's first or second value matches the end value of that train\n"+
    "once a domino is played the player's turn ends\n"+
    "if a player can play no domino on their turn, they must draw a domino\n"+
    "if the drawn domino can be played, they player must play it\n"+
    "if drawn domino can't be played, the players turn is over and their train is open for other players to play on\n "+
    "first player to play all their domino's wins!"
    ;
  private static TextArea area = new TextArea(text);
  
  private static Scene scene = new Scene(area);
  
  
  
  public static void show(){
    area.setEditable(false);
    stage.setScene(scene);
    stage.show();
    
    
  }
  
  
}//file