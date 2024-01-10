# MexicanTrainGame
Java classes used to implement a variation of the domino-based game "The Mexican Train Game"

A domino is a tile with two numbers, one number on each side. A train is a line of connected dominos where dominos can be connected when the each have an identical side. (5 | 3) can connect to (3 | 7) because each domino has a 3. 

Each player has their own train of dominos that (usually) only they can build on and there is also a Mexican train of dominos that anyone can build on.

The game starts by shuffling all the dominos and every player picks a hand of dominos. The "start" of the board is the single "double" domino (9 | 9) That indicates the number that all trains must start with. The players then take turns.

At each turn, a player can play their domino on their train, the Mexican train, or any other player's train if that train is "open". By default, all trains are "closed" so the players can only play on their train or the Mexican train.

If a player cannot play any domino because they hand does not have a domino that matches the end domino of any train they can play, then their train is marked as "open", and they draw a domino from the remaining pile. If they can play that domino, they may do so, but if not then it goes in their hand and their turn is over.

A player's train remains "open" until they add a new domino to it (including a just drawn domino). At that point, the train is "closed". While the train is "open" all other players can play on the train during their turn.

If a player plays a "double" domino (same number on both sides), they get another play, and that play must be to put another domino after the double (matching the double number). If they fail, their train becomes "open". Each subsequent player must play on the double, and if they fail, their train is "open". That continues until some player is able to play on the double, and then play resumes as normal.

The round of play ends when somebody runs out of dominos in their hand. Then all players add up the points on the dominos still in their hand, and they add that sum to their score.

The game continues in "rounds" where a different "double" domino is used as the starting value. Once every double domino has been used, the game ends and the winner is the player with the least total score.
