# Chutes And Ladders Simulator with Java

![The Software's UI](http://i.imgur.com/4CNVhJ9.png)

This Project is a simulation of the game “Chutes and Ladders”, it is designed to automatically generate a game board, add a specific number of players and run the simulation by automatically taking turns and moving the players’ tokens on the board until a player reaches the last tile on the board.

The game we are trying to simulate is based on “Chutes and snakes” with a number of changes in the obstacles and aids encountered by the players as follow :

To move, the players roll a dice (with values between 1 and a specified number). The player then moves his token by that number of steps. If his landing spot is any one of the aids/obstacles he should be affected as follows:

A treasure pot of type A will give him a fixed number of coins as long as the treasure pot still has enough coins to give away

A treasure pot of type B will give him a number of coins proportional to his dice roll as long as the treasure pot still has enough coins to give away.

A “hold” cell will deprive the player from advancing unless he rolls the same value he entered with, in which case the player is released and is advanced or moved back by a number of steps proportional to the dice roll and a multiplier that characterizes the cell

A “priority hold” cell will deprive the player from advancing unless he rolls the same value he entered with AND he is the head of the queue. The player’s position on the queue depends on the dice roll he entered the priority hold cell with. When the player is released, he is advanced or moved back by a number of steps proportional to the dice roll and a multiplier that characterizes the cell.

A “hold queue” cell will deprive the player from advancing unless he rolls the same value he entered with AND he is the head of the queue. The player’s position on the queue depends on how many players entered the cell after him. When the player is released, he is advanced or moved back by a number of steps proportional to the dice roll and a multiplier that characterizes the cell.

The program will be used in two ways : Either manually move forward one turn or run an automatically advancing simulation of a complete game. In both cases, the user will specify a number of parameters including  the configuration of the board map, the length of the board (length and height must be equal), the number of players and the configuration of the dice (maximum number it can return).

The Project has both a Graphical Interface and a Command-Line Interface :

![The Software's UI](http://i.imgur.com/4CNVhJ9.png)

![Command-Line Interface](http://i.imgur.com/qUfdTcl.png)

To understand how classes work and relate to each other, please take a look at this class diagram :

![Classes Diagram from BlueJ](http://i.imgur.com/6BtcVGN.png)

