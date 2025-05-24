# 2025 JAVA 期末專案範例
- 學號：B11007104 姓名：王星凱、- 學號：B11207142 姓名：許育銘
- 分工說明：
- 王星凱:BombKing 基礎功能列表:1、2、5、6、8、10
- 許育銘:BombKing 基礎功能列表:3、4、7、9
- [報告影片連結]()
- [jar 檔](https://github.com/Wang11007104/javagame/tree/main/lwjgl3/build/libs)
- [javadoc文件](https://github.com/Wang11007104/javagame/tree/main/lwjgl3/build/docs/javadoc)
- [commit 歷史紀錄](https://github.com/Wang11007104/javagame/blob/main/gitlog.txt)
- [報告簡報]()


## 遊戲說明
-目標: 玩家控制角色與對方對決，當玩家將對方血量歸零，遊戲便會結束。
 
-關卡進展: 
-防止敵人的攻擊。
-炸死對方。

-互動元素: 
-玩家通過鍵盤移動角色並可放置炸彈
-破壞障礙物，創造可行走的路。
-敵人會隨機攻擊，玩家需要躲避炸彈以免損失血量。

### 1. 遊戲流程
- 擊敗對方至血量歸零。

### 2. 操作方式
- 玩家移動：WASD、方向鍵。
- 放置炸彈攻擊：空格鍵、小鍵盤數字0。

## 指令及執行檔說明
### 下載完整repo後開啟Terminal執行以下指令
```
cd (exe資料夾存放路徑)
java -jar bombKing-1.0.0.jar
```

## 檔案結構
```
javagame\lwjgl3/
 ┣ 📂build
 ┃ ┣ 📂libs
 ┃ ┃ ┗ 📜bombKing-1.0.0.jar
 ┣ 📂src
 ┃ ┗ 📂main
 ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┃ ┗ 📂wxk
 ┃ ┃ ┃ ┃ ┃ ┗ 📂bombKing
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂lwjgl3
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜autoMonster.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DesktopLauncher.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BombKing.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BombKingObj.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Map.java
 ┃ ┣ 📂docs
 ┃ ┃ ┗ 📂javadoc
 ┃ ┃ ┃ ┣ 📂com
 ┃ ┃ ┃ ┃ ┗ 📂wxk
 ┃ ┃ ┃ ┃ ┃ ┗ 📂bombKing
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂lwjgl3
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜autoMonster.html
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DesktopLauncher.html
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BombKing.html
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BombKingObj.html
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜package-tree.html
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜package-summary.html
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Map.html

javagame\assets/
 ┣ 📜assets.txt
 ┣ 📜bgm.mp3
 ┣ 📜fire.mp3
 ┣ 📜bomb.png
 ┣ 📜bombB.png
 ┣ 📜bombF.png
 ┣ 📜bombL.png
 ┣ 📜bombR.png
 ┣ 📜bombobj1.ini
 ┣ 📜bombobj2.png
 ┣ 📜bombobj3.png
 ┣ 📜box.png
 ┣ 📜enemyB.mp3
 ┣ 📜enemyF.png
 ┣ 📜enemyL.png
 ┣ 📜enemyR.png
 ┣ 📜explode.png
 ┣ 📜fireR.png
 ┣ 📜health.png
 ┣ 📜house.png
 ┣ 📜road.png
 ┣ 📜tree.png
 ┣ 📜wall.png
 ┣ 📜map1.png
 ┣ 📜map2.png
 ┣ 📜map3.png
 ┣ 📜play.png
 ┣ 📜playerB.png
 ┣ 📜playerF.png
 ┣ 📜playerL.png
 ┣ 📜playerR.png
 ┣ 📜player2B.png
 ┣ 📜player2F.png
 ┣ 📜player2L.png
 ┣ 📜player2R.png
```

## 一、BombKing 基礎功能列表

此文件列出實作 BombKing 遊戲的 12 項基本功能模組，供遊戲開發規劃與專案分工參考。

---

 ✅ 1. 遊戲初始化與畫面佈局
- 建立遊戲主窗口（設定大小、標題、關閉操作）。
- 載入遊戲素材（玩家角色、敵人、炸彈、背景圖片、音效等資源）。
- 初始化遊戲狀態（生命值、關卡等）。

---

 ✅ 2. 玩家控制（移動、攻擊）
- 通過WASD、方向鍵移動控制玩家飛船位置。
- 按下空格鍵、小鍵盤數字0放置炸彈擊殺敵人。
---

 ✅ 3. 敵人生成與管理
- 生成敵人，固定位置刷新。
- 根據關卡難度調整敵人生成頻率與數量。
---

 ✅ 4. 敵人移動與行為
- 敵人隨機方向移動，設定在橘色外牆內。
- 每隔一段時間敵人放置炸彈。

---

 ✅ 5. 敵人攻擊
- 敵人放置炸彈攻擊玩家，炸彈爆炸會使玩家損失一顆心。

---

 ✅ 6.碰撞偵測（玩家、敵人攻擊）
- 玩家炸彈擊中敵人：敵人死亡，觸發爆炸效果。
- 玩家炸彈擊中障礙物：障礙物消失，形成道路。

---

 ✅ 7. 生命值與遊戲結束條件
- 玩家初始生命值為5顆心，敵人相同。
- 玩家生命值為 0 時進入 Game Over 畫面。

---

 ✅ 8.關卡系統
- 根據遊戲啟動介面選擇關卡（Level 1~3）。
- 根據關卡不同，顯示不同地圖。

---

 ✅ 9. 音效與背景音樂
- 包含爆炸音效、背景音樂。

---

 ✅ 10. 主選單與重新開始功能
- 顯示封面畫面，提供開始遊戲、關卡選項。
- Game Over 顯示相應畫面，支援重新開始或返回主選單。
## 二、UML 類別圖 (Class Diagram)

```mermaid
classDiagram
direction TB
    class DesktopLauncher {
	    +main(String[] args)
    }

    class BombKing {
	    +static BombKingObj bombPlayer1
	    +static BombKingObj bombPlayer2
	    +static autoMonster monster1
	    +static autoMonster monster2
	    +static autoMonster monster3
	    +static ArrayList~BombKingObj~ allObjs
    }

    class BombKingObj {
	    +float x
	    +float y
	    +int monMode
	    +int bloodCount
	    +Texture textureF
	    +void moveRight()
	    +void moveLeft()
	    +void moveUp()
	    +void moveDown()
    }

    class autoMonster {
	    +update()
    }

    class Map {
	    +draw(SpriteBatch batch)
	    +isWalkable(int x, int y)
	    +isBombable(int i)
	    +bomb(int x, int y)
	    +getTexture(int x, int y)
	    +static xyToI(int x, int y) int
	    +static realXY(int x, int y) Point
    }

    DesktopLauncher-->BombKing
    BombKing --> BombKingObj
    BombKingObj --> autoMonster
    BombKing --> Map


```
## 三、流程圖 (Flow Chart)

```mermaid
flowchart TD
    Start["遊戲開始（render()）"] --> ClearScreen["清除畫面"]

    ClearScreen --> ShowMenu["顯示開始畫面與選擇地圖按鈕"]

    ShowMenu --> ClickPlay["點擊『開始遊戲』按鈕"]
    ShowMenu --> ClickMap1["點擊『地圖1』按鈕"]
    ShowMenu --> ClickMap2["點擊『地圖2』按鈕"]
    ShowMenu --> ClickMap3["點擊『地圖3』按鈕"]

    ClickMap1 --> LoadMap1["載入 bombobj1.png 地圖"]
    ClickMap2 --> LoadMap2["載入 bombobj2.png 地圖"]
    ClickMap3 --> LoadMap3["載入 bombobj3.png 地圖"]

    LoadMap1 --> BackToMenu
    LoadMap2 --> BackToMenu
    LoadMap3 --> BackToMenu
    BackToMenu --> ShowMenu

    ClickPlay --> GameStart["進入遊戲進行畫面"]

    GameStart --> HideButtons["隱藏所有選單按鈕"]
    HideButtons --> UpdateTimer["計時器每幀累加"]

    UpdateTimer --> Check60Frames{"每 60 幀"}
    Check60Frames -- 是 --> MonsterAction["怪物隨機變更行為並放炸彈"]
    Check60Frames -- 否 --> 跳過怪物行為更新

    MonsterAction --> 跳過怪物行為更新
    跳過怪物行為更新 --> UpdateAllObjs["更新與繪製所有物件"]
    UpdateAllObjs --> HandleInput["處理玩家鍵盤操作"]

    HandleInput --> CheckPlayerDead{"任一玩家死亡？"}
    CheckPlayerDead -- 是 --> GameOver
    CheckPlayerDead -- 否 --> ShowUI["顯示玩家生命值與資訊"]
    ShowUI --> NextFrame["進入下一幀"]
    NextFrame --> GameStart

    GameOver --> ShowResult["顯示 GAME OVER 與剩餘血量"]
    ShowResult --> WaitForTouch{"玩家點擊螢幕？"}

    WaitForTouch -- 是 --> ResetGame["重置玩家、怪物與計分"]
    ResetGame --> ShowMenu

    WaitForTouch -- 否 --> StayGameOver["維持結算畫面"]
    StayGameOver --> WaitForTouch


```
## 四、序列圖 (Sequence Diagram)

```mermaid
sequenceDiagram
    participant Game as Game (render)
    participant Map as Map
    participant Stage as Stage
    participant Batch as SpriteBatch
    participant Player1 as bombPlayer1
    participant Player2 as bombPlayer2
    participant Monster1 as monster1
    participant Monster2 as monster2
    participant Monster3 as monster3
    participant Input as Gdx.input

    Game->>Game: clear screen
    alt stageEvent == 21 or 22 or 23
        Game->>Map: new Map("bombobjX.png")
        Game->>Game: stageEvent = 0
    end

    alt stageEvent == 0
        Game->>Stage: set starButton visible
        Game->>Stage: stage.act(deltaTime)
        Game->>Stage: stage.draw()
    else stageEvent == 1
        Game->>Stage: set starButton invisible
        loop every frame (countTimer)
            Monster1->>Monster1: change monMode randomly
            Monster2->>Monster2: change monMode randomly
            Monster3->>Monster3: change monMode randomly

            Monster1->>Monster1: bombfire()
            Monster2->>Monster2: bombfire()
            Monster3->>Monster3: bombfire()

            alt monster1.bloodCount <= 0
                Game->>Game: remove monster1
            end
            alt monster2.bloodCount <= 0
                Game->>Game: remove monster2
            end
            alt monster3.bloodCount <= 0
                Game->>Game: remove monster3
            end
        end
        Game->>Map: draw(batch)
        loop allObjs
            obj->>obj: update() if autoMonster and countTimer % 60 == 0
            obj->>obj: draw(batch) if showImage
        end
        Game->>Game: keyClicked()
        alt player bloodCount <= 0
            Game->>Game: stageEvent = 200
        end
        Game->>Batch: draw hearts for players
    else stageEvent == 200
        Game->>Batch: draw GAME OVER screen
        Input->>Game: check justTouched()
        alt touched
            Game->>Game: restore players and monsters
            Game->>Game: reset counters and stageEvent
        end
    end



```

