import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;


/**
 * represents a player
 * @author Brandon Lee
 */
public class Player{
  
  /**number of this player*/
  private int playernum;
  
  /**dominos in this player's hand*/
  private LinkedList<Domino> hand;
  
  /**this players train*/
  private DominoTrain train;
  
  /**the HBox that displays this players hand and holds the button for each domino*/
  private HBox box;
  
  /**stage that for this player*/
  private Stage stage;
  
  /**
* creates a new Player with playernum, hand, and train
* @param num this players number
*/
  public Player(int num){
    playernum = num;
    hand = new LinkedList<Domino>();
    train = new DominoTrain(9, this);
  }
  
  /**
* displays stage for this player showing the hand and draw button
* @param stag input stage
*/
  public void show(Stage stag){
    stage = stag;
    //sets x and y coordinate of each players stage
    if((playernum % 2) == 0 ){                          //even players
      stage.setX(900);
      stage.setY( 20 + 100* (playernum - 2));
    }
    else{                                                           //odd players
      stage.setX(0);
      stage.setY(20 + 100* (playernum - 1));
    }
    
    //set display for player 
    //draw button
    Button draw = new Button("draw");
    draw.setOnAction(e ->{ 
      //checks this players turn and that player has not drawn on this turn
      if((MexicanTrainGame.getTurn() == playernum) && (!MexicanTrainGame.drawn)){
        //dominos left in drawpile
        if(!MexicanTrainGame.getDraw().isEmpty()){
          MexicanTrainGame.setSelected(null);
          //adds first domino in draw pile to player hand
          Domino add = MexicanTrainGame.getDraw().getFirstNode().getElement();
          hand.addToFront(add);
          //assigns new domino to be in this player's hand
          add.setPlayer(this);
          //removes domino from draw pile
          MexicanTrainGame.getDraw().removeFromFront();
          //add domino to hand display
          Button newButton = new Button(add.toString());
          box.getChildren().add(0, newButton);
          //makes new domino functional
          newButton.setOnAction(n->{
            if(MexicanTrainGame.getTurn() == playernum){
          MexicanTrainGame.setSelected(add);
          System.out.println(add.toString());
        }});
          //adjust stage to fit new hand
          getStage().sizeToScene();
          //print hand
          LLNode<Domino> index = hand.getFirstNode();
          while(index != null){
            System.out.println(index.getElement());
            index = index.getNext();
          }
          //print hand display
          System.out.println("Dislpay");
          System.out.println(box.getChildren());
          
          //check if new domino is able to be played
          boolean cantPlay = true;                                            //variable storing whether player cant play- assumed true
          for(Player p : MexicanTrainGame.getPlayerList()){
            //check to see if new domino can be added to any player trains
            if(p.getTrain().canAdd(add) && (p.getTrain().isOpen() || (this == p))){
              cantPlay = false;
            }
          }
          //cantplay still true and unable to add to mexicantrain
          if(cantPlay && !MexicanTrainGame.getMexicanTrain().canAdd(add)){
            MexicanTrainGame.nextTurn();                                                             //next turn
            train.setOpen(true);                                                                               //set this players train to open
            Label open = new Label("Player " + getPlayerNum() + " Train:");     
            open.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));  // highlight open train
            MexicanTrainGame.borders[getPlayerNum()].setLeft(open);
            System.out.println("cant play turn over");
          }
          //able to play this domino, must play this domino
          else if(!cantPlay || MexicanTrainGame.getMexicanTrain().canAdd(add)){
            MexicanTrainGame.setSelected(add);                          //selects domino
            MexicanTrainGame.drawn = true;                                // drawn equals true, unable to select any other domino or draw again
          }
        }
        //no dominos left in drawpile; let players know, give option to skip turn
        else{
          Stage out = new Stage();
          BorderPane drawpane = new BorderPane();
          Button skip = new Button("skip turn");
          TextArea outmessage = new TextArea("drawpile empty:(");
          skip.setOnAction(b-> {
            MexicanTrainGame.nextTurn();
          });
          Button end = new Button("click if no one can play");
          end.setOnAction(m->{
            Player lowest = MexicanTrainGame.getPlayerList().getFirstNode().getElement();
            for(Player p : MexicanTrainGame.getPlayerList()){
              if(p.getScore() < lowest.getScore()){
                lowest = p;
              }
            }
            outmessage.setText("Player " + lowest.getPlayerNum() + " wins: " + lowest.getScore());
          });
          
          drawpane.setTop(outmessage);
          drawpane.setLeft(skip);
          drawpane.setRight(end);
          Scene sceneout = new Scene(drawpane);
          out.setScene(sceneout);
          out.show();
        }
      }
    });
    
    //sets up main layout
    BorderPane pane = new BorderPane();
    box = new HBox();
    //adds players hand to display
    for(Domino d : hand){
      //each domino in hand added to display
      Button button = new Button(d.toString());
      box.getChildren().add(button);
      button.setOnAction( e -> {
        if(MexicanTrainGame.getTurn() == playernum){
          if(!MexicanTrainGame.drawn){
            MexicanTrainGame.setSelected(d);
          }
        }
      });
    }
    //adds hand to display
    pane.setLeft(box);
    //adds draw button to display
    pane.setRight(draw);
    Scene scene = new Scene(pane);
    stage.setScene(scene);
    //title
    stage.setTitle("Player " + playernum);
    stage.sizeToScene();
    stage.show();
  }
  
  /**
* returns player's hand
* @return hand
*/
  public LinkedList<Domino> getHand(){
    return hand;
  }
  
  /**
* returns player number
* @return playernum
*/
  public int getPlayerNum(){
    return playernum;
  }
  
  /**
* returns HBox used to display hand
* @return box
*/
  public HBox getBox(){
    return box;
  }
  
  /**
* returns this player's train
* @return train
*/
  public DominoTrain getTrain(){
    return train;
  }
  
  /**
* returns this player stage
* @return stage
*/
  public Stage getStage(){
    return stage;
  }
  
  public int getScore(){
    int score = 0;
    for(Domino d : hand){
      score+= d.getFirst();
      score+= d.getSecond();
    }
    return score;
  }
  
}//file




