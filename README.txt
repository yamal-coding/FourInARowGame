This Java desktop application implements the next "four in a row" game variations:

1. Classic four in a row.
2. Complica: in this variation of the game the columns can not get totally filled, I mean,
   if the column is full of chips and you put another one on the top, the chips of the column
   move one position down and the one of the bottom goes away.
3. Gravity: when you put a chip on the board it goes to the nearest wall. You can choose the dimensions
   of the board.
4. Reversi: actually this is not a four in a row variation. The game begins with a board filled in the center
   with fourchips (two white and two black). You can put the chip of your color only next to a chip of the opposite
   color that is next to a chip of your color, then the opposite color chip obtains your color. The game
   ends when the board is full of chips and the winner is the player with more chips of his color.
   
It is necessary CommonsCli Apache library to compile the source. This library is used to parse arguments
if you want to use this application from the command line.
You can get the binary here: https://commons.apache.org/proper/commons-cli/
