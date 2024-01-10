import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import java.lang.ArrayIndexOutOfBoundsException;
import javafx.stage.StageStyle;

/**
 * main class running the MexicanTrainGame
 * @author Brandon Lee
 */
public class MexicanTrainGame extends Application{
  
  /**stage for the initial homepage of this program*/
  private Stage homePage;
  
  /**main stage that for mexican train game shows all trains*/
  private Stage primaryStage;
  
  /**start button, setsup game and closes homepage*/
  private static Button start;
  
  /**closes the program*/
  private static Button close;
  
  /**number of players*/
  private static int players;
  
  /**list of players*/
  private static LinkedList<Player> playerlist;
  
  /**set of 55 dominos*/
  private ArrayList<Domino> dominos;
  
  /**the mexican train*/
  private static DominoTrain mexicanTrain;
  
  /**draw pile*/
  private static LinkedList<Domino> drawPile;
  
  /**main layout that holds the display for each train on a separate line*/
  private VBox vlayout;
  
  /**domino that is currently selected*/
  private static Domino selectedDomino;
  
  /**number of the player whose turn it is*/
  private static int turn;
  
  /**array of borderpanes holding the borderpanes put in each row of vbox for each player*/
  public static BorderPane[] borders;
  
  /**stores whether current player has drawn this turn*/
  public static boolean drawn;

  
  /**
* main method runs program
* @param args input string array
*/
  public static void main(String[] args){
    try{                                                                  //set initial input to main to be number of players
      players = Integer.parseInt(args[0]);
      if((players < 2) || (players> 4)){
        players = 2;
        System.out.println("Can't have less than 2 players or more than 4!");
      }
    }catch(ArrayIndexOutOfBoundsException e){//if no input players set to 2
      players =2;
    } 
    
    Application.launch(args);
    System.out.println("Hello");
    drawn = false;                                                   //initialize false
  }
  
  /**
* start method; displays the home page and main page
* @param homePage input stage
*/
  public void start(Stage homepage){
    //initialze fields
    this.homePage = homepage;                                         //home page
    homePage.setMinWidth(300);
    homePage.setMinHeight(300);
    homePage.setTitle("Programming Project 4");
    this.start = new Button("Start");                                    //start button
    start.setMinSize(80,32);
    close = new Button("Close");                                         //close button
    close.setMinSize(60,20);
    turn = 1;                                                                           //tracks which turn
    primaryStage = new Stage();                                          //main stage for mexican train game
    primaryStage.setMinWidth(200);
    primaryStage.setTitle("MexicanTrainGame");
    primaryStage.initStyle(StageStyle.DECORATED);
 
    dominos = Domino.fullStack();                                       //create a full set of 55 dominos
    Collections.shuffle(dominos);                                         //shuffle dominos
    
    Iterator<Domino> dominoIterator = dominos.iterator();
    
    mexicanTrain = new DominoTrain(9,new Player(20));  //creates the main mexican train (train *owned by player 20; not real player)
    mexicanTrain.setOpen(true);                                          //mexican train open to all players
    drawPile = new LinkedList<Domino>();                        //creates draw pile
    borders = new BorderPane[10];                                      //array storing the borderpanes used to display each players train
    playerlist = new LinkedList<Player>();                           //linked list storing all the players
    
    //buttons and gadgets for home page
    Button increasePlayers = new Button("up");                  //increase number of players
    increasePlayers.setMinWidth(60);
    Button decreasePlayers = new Button("down");            //decrease number of players
    decreasePlayers.setMinWidth(60);
    Button about = new Button("Rules");                             //shows rules
    about.setMinSize(60,20);
    TextArea playercount = new TextArea("Players: "+players);       //shows the number of players
    playercount.setEditable(false);
    playercount.setMaxWidth(80);
    playercount.setMaxHeight(30);
    TextArea playercountpage = new TextArea("Players: 2");
    
    //homepage
    BorderPane layout = new BorderPane();                                 //main layout for homepage
    GridPane leftg = new GridPane();
    GridPane centerg = new GridPane();
    GridPane rightg = new GridPane();
    leftg.setGridLinesVisible(false);
    System.out.println(""+leftg.isGridLinesVisible());
    leftg.add(new Label("         "),0,0);
    for(int i = 1; i< 3; i++){
      leftg.add(new Label("          "),i,0);
    }
    for(int i = 1; i< 9; i++){
      leftg.add(new Label("          "),0,i);
    }
    
    centerg.add(new Label("      "),0,0);
    centerg.add(new Label("       "),1,0);
    centerg.add(new Label("       "),2,0);
    Label l = new Label();
    l.setMinSize(60,60);
    centerg.add(l,3,0);
    centerg.add(playercount,3,1);
    centerg.setGridLinesVisible(false);
    rightg.setGridLinesVisible(false);

    leftg.add(start,1,2);
    leftg.add(about,1,4);
    leftg.add(close,1,6);
    
    rightg.add(new Label("      "),0,0);
    rightg.add(new Label("       "),0,1);
    rightg.add(new Label("        "),0,2);
    rightg.add(new Label("        "),0,3);
    rightg.add(new Label("        "),1,2);
    rightg.add(new Label("        "),2,2);
    rightg.add(new Label("        "),3,2);
    rightg.add(increasePlayers,0,3);
    rightg.add(decreasePlayers,0,4);

    TextArea header = new TextArea("\n\t\t\t\t\t\t\tMEXICAN TRAIN GAME");
    header.setMaxHeight(55);
    header.setEditable(false);
    TextArea bottom = new TextArea("\t\t\t\t\t\t\t\tBy: Brandon Lee");
    bottom.setMaxHeight(30);
    bottom.setEditable(false);
    
    layout.setTop(header);
    layout.setBottom(bottom);
    layout.setLeft(leftg);
    layout.setAlignment(leftg, Pos.CENTER);
    layout.setCenter(centerg);
    layout.setRight(rightg);
         
    Scene scene = new Scene(layout);
    homePage.setScene(scene);
    homePage.show();
        
    //startbutton; sets up game
    start.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent e){
        homePage.close();
        vlayout = new VBox(10);
        vlayout.getChildren().add(close);
        //set up players for game
        for(int i = 1; i <= players ; i++){
          playerlist.addToEnd(new Player(i));
        }
        //set up player hands
        int count = 0;
        for(Player p : playerlist){
          for(int i = 0; i < 12; i++){
            Domino d = dominoIterator.next();
            d.setPlayer(p);
            p.getHand().addToEnd(d);
            count++;
          }
          p.show(new Stage());
        }
        //initializes player 1 title
        playerlist.getFirstNode().getElement().getStage().setTitle("Player 1 Turn");
        //adds remaining dominos to the drawpile
        while(count < 55){
          drawPile.addToFront(dominoIterator.next());
          count++;
        }
        //displays player trains
        for(Player p : playerlist){
          BorderPane b = new BorderPane();
          borders[p.getPlayerNum()] = b;
          HBox trainBox = new HBox();
          trainBox.getChildren().add(new Button("Train"));
          b.setLeft(new Label("Player " + p.getPlayerNum() + " Train:"));
          Button add = new Button("add");
          b.setRight(add);
          b.setCenter(trainBox);
          vlayout.getChildren().add(b);
          //add button for each player train
          add.setOnAction(o ->{
          if(selectedDomino != null){                                                                            //domino has been selected
            if((p.getTrain().isOpen()) || (selectedDomino.getPlayer() == p)){               //this train is open or it is the current players train
            if(p.getTrain().canAdd(selectedDomino)){                                                   //domino can be added
              p.getTrain().addToFront(selectedDomino);                                               //adds domino to train
              trainBox.getChildren().add(new Button(selectedDomino.toString()));    //adds button to train display
              primaryStage.sizeToScene();                                                                       //adjust stage 
              
              //set their train to closed
              selectedDomino.getPlayer().getTrain().setOpen(false);                           
              Label closed = new Label("Player " + selectedDomino.getPlayer().getPlayerNum() + " Train:");
              closed.setBackground(new Background(new BackgroundFill(null, null, null)));
              borders[selectedDomino.getPlayer().getPlayerNum()].setLeft(closed);
              
              //removing domino from players hand
              int counter = 0;
              LinkedListIterator<Domino> iterator = selectedDomino.getPlayer().getHand().iterator();
              boolean passed = false;
              while(iterator.hasNext()){
                if(iterator.next() == selectedDomino){
                  selectedDomino.getPlayer().getBox().getChildren().remove(counter);
                  iterator.remove();
                }
                counter++;
              }
              
              //reset for next move
              System.out.println(selectedDomino.toString());
              MexicanTrainGame.setSelected(null); //deselects domino
              MexicanTrainGame.nextTurn();
            }} else{System.out.println("train closed");}
          }
          else{
            System.out.println("null");
          }
          for(Player d : playerlist){                          //check win
            if(d.getHand().isEmpty()){
              Stage winStage = new Stage();
              TextArea winmessage = new TextArea("Player " + d.getPlayerNum() + " Wins!");
              Scene winScene = new Scene(winmessage);
              winStage.setScene(winScene);
              winStage.show();
            }
          }
        });
        }
        //display mexican train
        BorderPane b = new BorderPane();
        HBox trainBox = new HBox();
        trainBox.getChildren().add(new Button("Train"));
        Label mexlabel = new Label("Mexican Train:");
        b.setLeft(mexlabel);
        Button add = new Button("add");
        //add button
        add.setOnAction(a ->{
          if(selectedDomino != null){                                                                       //domino has been selected
            if(mexicanTrain.canAdd(selectedDomino)){                                            //domino will be added
              mexicanTrain.addToFront(selectedDomino);                                        //adds to mexican train domino train
              trainBox.getChildren().add(new Button(selectedDomino.toString()));//adds button to train
              primaryStage.sizeToScene();                                                                  //adjust primary stage
              
              //set their train to closed
              selectedDomino.getPlayer().getTrain().setOpen(false);
              Label closed = new Label("Player " + selectedDomino.getPlayer().getPlayerNum() + " Train:");
              closed.setBackground(new Background(new BackgroundFill(null, null, null)));
              borders[selectedDomino.getPlayer().getPlayerNum()].setLeft(closed);
              
              //removing domino from players hand
              int counter = 0;
              LinkedListIterator<Domino> iterator = selectedDomino.getPlayer().getHand().iterator();
              boolean passed = false;
              while(iterator.hasNext()){
                if(iterator.next() == selectedDomino){
                  selectedDomino.getPlayer().getBox().getChildren().remove(counter);
                  iterator.remove();
                }
                counter++;
              }

              //reset for next move
              System.out.println(selectedDomino.toString());
              MexicanTrainGame.setSelected(null); //deselects domino
              MexicanTrainGame.nextTurn();
            }
          }
          else{
            System.out.println("null");
          }
          for(Player p : playerlist){                    //check win
            if(p.getHand().isEmpty()){
              Stage winStage = new Stage();
              TextArea winmessage = new TextArea("Player " + p.getPlayerNum() + " Wins!");
              Scene winScene = new Scene(winmessage);
              winStage.setScene(winScene);
              winStage.show();
            }
          }
        });
        b.setRight(add);
        b.setCenter(trainBox);
        vlayout.getChildren().add(b);
        Scene mscene = new Scene(vlayout);
        primaryStage.setScene(mscene);
        primaryStage.show();
      }
    });
    
    //sets increase player button
    increasePlayers.setOnAction( e -> {
      if(players< 4){
        players++;
      }
      
      playercount.setText("Players: " + players);
      playercountpage.setText("Players: " + players);
    });
    //sets decrease player button
    decreasePlayers.setOnAction( e -> {
      if(players > 2){
        players--;
      }
      playercount.setText("Players: " + players);
      playercountpage.setText("Players: " + players);
    });
    
    //sets about button
    about.setOnAction(e ->{
      About.show();
    });
    
    //sets close action
    close.setOnAction( e -> {
      System.exit(0);
    });
    
   
    
  }//start method
  
  /**
* sets the currently selected domino
* @param d domino to be selected domino
*/
  public static void setSelected(Domino d){
    if(selectedDomino != null){
      int counter = 0;
      LinkedListIterator<Domino> iterator = selectedDomino.getPlayer().getHand().iterator();
      while(iterator.hasNext()){
        if(iterator.next() == selectedDomino){
          Button b = (Button)selectedDomino.getPlayer().getBox().getChildren().get(counter);
          b.setMinSize(40, 25);
          b.setBackground(close.getBackground());
        }
        counter++;
      }
    }
    selectedDomino = d;
    if(d != null){
      int counter = 0;
      LinkedListIterator<Domino> iterator = selectedDomino.getPlayer().getHand().iterator();
      while(iterator.hasNext()){
        if(iterator.next() == selectedDomino){
          Button b = (Button)selectedDomino.getPlayer().getBox().getChildren().get(counter);
          System.out.println(b.getWidth() + " " +b.getHeight());
          b.setMinSize(48,30);
          selectedDomino.getPlayer().getStage().sizeToScene();
          //
          b.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
          //
        }
        counter++;
      }
      System.out.println(d.toString());
    }
    
  }
  
  /**
* changes turn to the next player
*/
  public static void nextTurn(){
    if(MexicanTrainGame.turn == MexicanTrainGame.players){
      turn = 1;
    }
    else{
      turn++;
    }
    for(Player p : playerlist){
      if(p.getPlayerNum() == getTurn()){
        p.getStage().setTitle("Player " + p.getPlayerNum() + " turn");
      }
      if((p.getPlayerNum() == (getTurn() -1)) || (p.getPlayerNum() ==(getTurn()+players-1))){
        p.getStage().setTitle("Player " + p.getPlayerNum());
      }
    }
    System.out.println("player "+turn + " turn");
    drawn = false;
    setSelected(null);
  }
  
  /**
* returns number of player whose turn it is
* @return turn
*/
  public static int getTurn(){
    return turn;
  }
  
  /**
* returns linkedlist storing dominos in the draw pile
* @return drawPile
*/
  public static LinkedList<Domino> getDraw(){
    return drawPile;
  }
  
  /**
* returns dominotrain storing the mexicantrain
* @return mexicanTrain
*/
  public static DominoTrain getMexicanTrain(){
    return mexicanTrain;
  }
  
  /**
* returns linkedlist storing the players in this game
* @return playerlist
*/
  public static LinkedList<Player> getPlayerList(){
    return playerlist;
  }
  
  /**
   * exits the program
   */
  public static void close(){
    System.exit(0);
  }
    
  

}//file endjjjjj



