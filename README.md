# RollingDraw

<img src="RollingDraw.png" width="300" height="300" alt="image">

## Plugin Functions
Administrators enter commands, and then the plugin records the list of currently online players and starts the lottery. During the lottery, a 10-second countdown will be displayed in the chat box, and then the names of the drawn players will be shown on the public screen. Meanwhile, the ID of the lottery initiator, the IDs of the players participating in the lottery, the IDs of the winning players, etc. will be recorded in a log file.

## Plugin Information
**Tested Version**: 1.20.1
**Server Software**: Spigot, Paper

## Function Demonstration
The administrator (with operator (op) permissions) enters the command: /rollingdraw create 2 Elytra

(The following information is the output by the plugin to the server's chat public screen)
```
A lottery was initiated by xxx. The prize is: Elytra. The number of winners to be drawn is 2, and the number of participants in the lottery is: 13. The list is as follows:
Emily、William、James、John、Robert、Michael、David、Sarah、Jessica、Daniel、Olivia、Luna、Harper
Now start the 10-second countdown for the lottery:
The lottery will be drawn in 10 seconds. Maybe Olivia will win!
The lottery will be drawn in 8 seconds. Maybe Robert will win!
The lottery will be drawn in 5 seconds. Maybe Sarah will win!
The lottery will be drawn in 3 seconds. Maybe William will win!
The lottery will be drawn in 2 seconds. Maybe John will win!
The lottery will be drawn in 1 second. Maybe Emily will win!
The winners are: David、William. Congratulations!!!
```
After the lottery ends, the ID of the lottery initiator, the IDs of the players participating in the lottery, the IDs of the winning players, etc. will be recorded in a log file.

PS:
The plugin is only responsible for drawing the names of the players and is not responsible for distributing the prizes.
The Elytra mentioned in the example above is just to tell the players that the prize for this lottery is Elytra.
The distribution of prizes needs to be done manually by the administrator.

## Plugin Commands
The command to start a lottery:
```yaml
/rollingdraw create <number of winners to be drawn> <name of the prize to be drawn>
# Example:
# /rollingdraw create 3 Enchanted Golden Apple
```

## Plugin Configuration

### config.yml 

#### Default Configuration File
```yaml
ROLL_START: "@(#CCFF33)Lottery starts!"
TIME_LEFT: "There are {time} seconds left before the lottery is drawn. Maybe {randomName} will win!"
CREATE_ROLL: "{creator} has created a lottery. The prize is: {prize}. The number of participants: {count}. The number of winners: {winnerCount}"
PARTNER: "The list of participants is as follows: {names}. There are {count} participants in total."
TIME_START: "Now start the 10-second countdown for the lottery:"
CONGRATULATION: "Congratulations to {winners} for winning!"
COMMA: "、"
NAME_COLOR : "(#46a1a2)"
```

#### Configuration File with Gradient Colors
config.yml
```yaml
ROLL_START: "@(#CCFF33)Lottery @(#CCFF56)starts!"
TIME_LEFT: "@(#ff9e05)There @(#fd9705)are {time} @(#fb8f05)seconds @(#f98806)left @(#f78106)before @(#f57a06)the @(#f37206)lottery @(#f16b06)is @(#46a1a2)drawn. @(#ef6407)Maybe @(#ed5c07){randomName} @(#eb5507)will @(#e94e07)win @(#e74707)!"
CREATE_ROLL: "@(#FBB708){creator} @(#084cfb)has @(#0d51fb)created @(#1155fb)a @(#165afb)lottery. @(#1a5ffb)The @(#1f63fb)prize @(#2468fb)is: @(#FBB708){prize} @(#62a7fc). @(#6aaffc)The @(#71b6fc)number @(#79befc)of @(#80c5fc)participants: @(#88cdfd){count}. @(#FBB708)The @(#8fd5fd)number @(#97dcfd)of @(#9ee4fd)winners: @(#a6ebfd){winnerCount}."
PARTNER: "@(#05FF49)The @(#05fc53)list @(#06f85d)of @(#06f567)participants @(#06f270)is @(#07ee7a)as @(#07eb84)follows: {names}. @(#07eb84)There @(#07eb84)are {count} @(#07eb84)participants @(#07eb84)in @(#07eb84)total."
TIME_START: "@(#0EEB07)Now start the 10-second countdown for the lottery:"
CONGRATULATION: "@(#FF2805)Congratulations to {winners} @(#FF2805)for winning. @(#fb8f05)The prizes will be distributed later."
COMMA: "、"
NAME_COLOR : "@(#46a1a2)"
```

### log.txt
```yaml
Time: Thu Feb 01 14:49:01 CST 2024
Lottery Initiator: op
List: player1、player2、player3、player4、player5、player6、player7、player8
Prize: 1 Elytra
Number of Participants: 3
Winners: player1、player6、player4
``` 
