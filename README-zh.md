# RollingDraw

<img src="RollingDraw.png" width="300" height="300" alt="image">

## 插件功能

管理员输入指令，然后插件记录下当前在线的玩家名单，然后开始进行抽奖

抽奖的时候，聊天框会进行10秒倒计时，然后将抽到的玩家名字显示在公屏中

同时抽奖发起人id，抽奖参与的玩家id，获奖的玩家id等....这些会被记录至一个log文件中



## 插件信息

已测试版本：   1.20.1

服务端：  Spigot、Paper



## 功能展示

管理员op输入指令 :  /rollingdraw create 2 鞘翅  

（下面信息为插件向服务器聊天公屏中输出的信息）

```
由xxx发起了一项抽奖，奖品为：鞘翅。抽取获奖人数为2人，参与抽奖人数为：13人，名单如下：
Emily、William、James、John、Robert、Michael、David、Sarah、Jessica、Daniel、Olivia、Luna、Harper
现在开始倒计时10秒抽奖：
10秒后开奖，也许是：Olivia获奖噢！
8秒后开奖，也许是：Robert获奖哦！
5秒后开奖，也许是Sarah获奖噢！
3秒后开奖，也许是William获奖噢！
2秒后开奖，也许是John获奖噢！
1秒后开奖，也许是Emily获奖噢！
获奖者是：David、William 恭喜！！！
```

抽奖结束后，抽奖发起人id，抽奖参与的玩家id，获奖的玩家id等....这些会被记录至一个log文件中

ps：

插件只负责抽取玩家的名字，不用负责奖品的发放，

上面举例的 鞘翅，只是告诉玩家这个抽奖抽的奖品是鞘翅

奖品的发放需要由管理员进行手动发放



## 插件指令

开始进行一个抽奖的指令：

```yaml
/rollingdraw create <抽取获奖的人数> <抽取的奖品名字>
#举例：
#/rollingdraw create 3 附魔金苹果
```



## 插件配置

### config.yml 

#### 默认配置文件

```yaml
ROLL_START: "@(#CCFF33)抽奖开始!"
TIME_LEFT: "剩余{time}秒开奖，可能是：{randomName}中奖哦!"
CREATE_ROLL: "{creator}创建了一个抽奖，奖品是：{prize}，参与人数：{count}，中奖人数：{winnerCount}"
PARTNER: "参与者名单如下: {names}，共{count}人"
TIME_START: "现在开始倒计时10秒抽奖："
CONGRATULATION: "恭喜{winners}中奖"
COMMA: "、"
NAME_COLOR : "(#46a1a2)"
```



#### 带渐变颜色的配置文件：

config.yml

```yaml
ROLL_START: "@(#CCFF33)抽@(#CCFF56)奖开始!"
TIME_LEFT: "@(#ff9e05)剩@(#fd9705)余{time}@(#fb8f05)秒@(#f98806)开@(#f78106)奖，@(#f57a06)可@(#f37206)能@(#f16b06)是：@(#46a1a2){randomName}@(#ef6407)中@(#ed5c07)奖@(#eb5507)哦!"
CREATE_ROLL: "@(#FBB708){creator}@(#084cfb)创@(#0d51fb)建@(#1155fb)了@(#165afb)一@(#1a5ffb)个@(#1f63fb)抽@(#2468fb)奖@(#286cfb)，@(#2d71fb)奖@(#3176fc)品@(#367afc)是@(#3a7ffc)：@(#FBB708){prize}@(#62a7fc)，@(#6aaffc)参@(#71b6fc)与@(#79befc)人@(#80c5fc)数@(#88cdfd)：@(#FBB708){count}@(#8fd5fd)，拟@(#97dcfd)中@(#9ee4fd)奖@(#a6ebfd)人@(#adf3fd)数：@(#FBB708){winnerCount}"
PARTNER: "@(#05FF49)参@(#05fc53)与@(#06f85d)者@(#06f567)名@(#06f270)单@(#07ee7a)如@(#07eb84)下: {names}，@(#07eb84)共{count}人"
TIME_START: "@(#0EEB07)现在开始倒计时10秒抽奖："
CONGRATULATION: "@(#FF2805)恭喜{winners}@(#FF2805)中奖，@(#fb8f05)奖品稍后发放"
COMMA: "、"
NAME_COLOR : "@(#46a1a2)"
```





### log.txt

```yaml
时间：Thu Feb 01 14:49:01 CST 2024
抽奖者：op
名单：player1、player2、player3、player4、player5、player6、player7、player8
奖品：1个鞘翅
人数：3
中奖者：player1、player6、player4
```







​            
