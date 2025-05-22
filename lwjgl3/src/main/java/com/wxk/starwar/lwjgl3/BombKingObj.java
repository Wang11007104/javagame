package com.wxk.starwar.lwjgl3;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BombKingObj {
    public Texture textureL, textureR, textureF, textureB;
    public float x, y;// 即時的位置
    public float w, h;
    public float vx, vy;
    // public boolean offImage=false;
    public int monMode;
    public boolean showImage = true;
    public int bloodCount = 5, oriBlood = 5;// wizaed.blood
    public float oriX, oriY;
    public static float explodeX, explodeY, explodeCount;

    /**
     * 建立一個 BombKingObj 物件，初始化其位置、大小、貼圖與模式。
     *
     * @param texturePath 貼圖的基礎路徑（不含方向與副檔名），例如 "wizard" 將載入 "wizardF.png" 等。
     * @param startX      物件起始的 X 座標（以像素為單位）
     * @param startY      物件起始的 Y 座標（以像素為單位）
     * @param width       物件的寬度
     * @param height      物件的高度
     * @param monMode     物件模式，用於決定物件行為
     */
    public BombKingObj(String texturePath, float startX, float startY, float width, float height, int monMode) {
        this.textureF = new Texture(texturePath + "F.png"); // 加載飛船圖片
        this.textureR = new Texture(texturePath + "R.png");
        this.textureB = new Texture(texturePath + "B.png");
        this.textureL = new Texture(texturePath + "L.png");

        this.x = startX; // 設定人物的 X 座標
        this.y = startY; // 設定人物的 Y 座標
        oriX = startX;
        oriY = startY;
        this.w = width;
        this.h = height;
        this.monMode = monMode;
        vx = 0;
        vy = 0;

    }

    // 更新飛船位置
    /**
     * 更新角色位置、處理碰撞、傷害、爆炸與刪除等邏輯。
     * 呼叫頻率通常為每幀一次。
     */
    /**
     * 更新角色位置、處理碰撞、傷害、爆炸與刪除等邏輯。
     * 呼叫頻率通常為每幀一次。
     */
    public void update() {
        float unmovX, unmovY;
        unmovX = x;
        unmovY = y;

        float deltaTime = Gdx.graphics.getDeltaTime();
        x += vx * deltaTime;
        y += vy * deltaTime;

        // 限制邊界
        if (monMode != 0 && monMode != 1) { // 給超出範圍後刪掉mode0需要出邊界
            x = Math.max(0, Math.min(x, Gdx.graphics.getWidth() + 80 - textureB.getWidth()));
            y = Math.max(0, Math.min(y, Gdx.graphics.getHeight() + 100 - textureB.getHeight()));
        }

        int index = BombKing.allObjs.indexOf(this);
        // System.out.println(index);
        // System.err.println();
        for (int i = index; i < BombKing.allObjs.size(); i++) {
            // System.err.print(i);
            BombKingObj obj = BombKing.allObjs.get(i);

            if (this != obj) { // 自己不跟自己碰撞
                boolean b = collide(this, obj);
                // System.out.println(b);
                if (b) {

                    boolean areBullets = (this.monMode < 2 && obj.monMode < 2);
                    boolean areMonsters = (this.monMode >= 2 && this.monMode < 100 && obj.monMode >= 2
                            && obj.monMode < 100);

                    if (!(areMonsters)) {

                        if (!(areBullets)) {// 碰撞扣血
                            this.bloodCount -= 1;
                            obj.bloodCount--;
                            if (this.monMode == 1) {
                                explodeX = this.x;
                                explodeY = this.y;
                                explodeCount = 5;
                            }
                            if (obj.monMode == 1) {
                                explodeX = obj.x;
                                explodeY = obj.y;
                                explodeCount = 5;
                            }

                        }

                        if (obj != BombKing.bombPlayer1 && obj.bloodCount <= 0) {
                            BombKing.allObjs.remove(obj);
                            BombKing.countPoint++;
                        }
                        if (this != BombKing.bombPlayer1 && this.bloodCount <= 0) {
                            BombKing.allObjs.remove(this);
                            BombKing.countPoint++;
                        }

                    }

                    if (BombKing.bombPlayer1.bloodCount == 0) {
                        BombKing.stageEvent = 100;
                    }

                    /*
                     * 不要動
                     * x=unmovX;
                     * y=unmovY;
                     */
                }
            }
        }

    }

    /**
     * 在畫面上繪製物件的當前方向貼圖。
     * 
     * @param batch 用於繪製的 SpriteBatch 實例，必須已經呼叫過 batch.begin()。
     * 
     *              根據物件的速度 vx 判斷方向並繪製對應的貼圖。
     *              假設 vx 分別對應方向如下：
     *              0 - 右 (Right)
     *              1 - 下 (Back)
     *              2 - 左 (Left)
     *              3 - 上 (Front)
     * 
     *              繪製位置由 Map.realXY 計算轉換遊戲座標到畫面座標。
     */
    public void draw(SpriteBatch batch) {
        batch.begin();
        Point p = Map.realXY((int) x, (int) y);
        if (vx == 0) {
            batch.draw(textureR, p.x, p.y, w, h);
        } else if (vx == 1) {
            batch.draw(textureB, p.x, p.y, w, h);
        } else if (vx == 2) {
            batch.draw(textureL, p.x, p.y, w, h);
        } else if (vx == 3) {
            batch.draw(textureF, p.x, p.y, w, h);
        }
        batch.end();
    }

    /**
     * 判斷兩個 BombKingObj 物件是否發生碰撞。
     * 
     * 碰撞判定依據是兩物件的座標與尺寸是否有重疊區域。
     * 
     * @param obj1 第一個物件
     * @param obj2 第二個物件
     * @return 如果兩物件矩形範圍重疊，回傳 true，否則回傳 false
     */
    public boolean collide(BombKingObj obj1, BombKingObj obj2) {

        // System.out.println("x="+obj1.x+" x2="+obj2.x);

        if ((obj2.x - obj1.x <= obj1.w && obj2.x - obj1.x >= -obj2.w) &&
                (obj2.y - obj1.y <= obj1.h && obj2.y - obj1.y >= -obj2.h + 20)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 將物件的位置與血量恢復到初始狀態。
     * 
     * 此方法會將物件的座標重置為最初的起始座標，
     * 並將血量重置為原始血量值。
     */
    public void allRestore() {
        x = oriX;
        y = oriY;
        bloodCount = oriBlood;
    }

    /**
     * 嘗試讓物件向左移動一格。
     * 
     * 先將水平速度 vx 設為 2（表示向左移動方向），
     * 接著檢查目標格子是否可行走（透過地圖的 isWalkable 方法）。
     * 
     * 如果目標位置可走，x 座標減一，物件向左移動一格並回傳 true。
     * 如果不可走，物件位置不變，回傳 false。
     * 
     * @return boolean 是否成功移動（true = 移動成功，false = 無法移動）
     */
    public boolean moveLeft() {
        vx = 2;
        if (BombKing.m.isWalkable((int) (x - 1), (int) y)) {
            x--;

            return true;
        }
        return false;
    }

    /**
     * 嘗試讓物件向右移動一格。
     * 
     * 先將水平速度 vx 設為 0（表示向右移動方向），
     * 接著檢查目標格子是否可行走（透過地圖的 isWalkable 方法）。
     * 
     * 如果目標位置可走，x 座標加一，物件向右移動一格並回傳 true。
     * 如果不可走，物件位置不變，回傳 false。
     * 
     * @return boolean 是否成功移動（true = 移動成功，false = 無法移動）
     */
    public boolean moveRight() {
        vx = 0;
        if (BombKing.m.isWalkable((int) (x + 1), (int) y)) {
            x++;
            // vx=0;
            return true;
        }

        return false;

    }

    /**
     * 嘗試讓物件向上移動一格。
     * 
     * 設定垂直速度 vy 為 -1（向上方向），
     * 接著檢查目標格子是否可行走（透過地圖的 isWalkable 方法）。
     * 
     * 如果目標位置可走，y 座標減一，物件向上移動一格並回傳 true。
     * 如果不可走，物件位置不變，回傳 false。
     * 
     * @return boolean 是否成功移動（true = 移動成功，false = 無法移動）
     */
    public boolean moveUp() {
        vx = 1;
        if (BombKing.m.isWalkable((int) (x), (int) (y - 1))) {
            y--;
            // vx=1;
            return true;
        }
        return false;

    }

    /**
     * 嘗試讓物件向下移動一格。
     * 
     * 設定垂直速度 vy 為 1（向下方向），
     * 接著檢查目標格子是否可行走。
     * 
     * 如果目標位置可走，y 座標加一，物件向下移動一格並回傳 true。
     * 如果不可走，物件位置不變，回傳 false。
     * 
     * @return boolean 是否成功移動（true = 移動成功，false = 無法移動）
     */
    public boolean moveDown() {
        vx = 3;
        if (BombKing.m.isWalkable((int) (x), (int) (y + 1))) {
            y++;

            return true;
        }
        return false;

    }

    // 釋放資源
    public void dispose() {
        textureB.dispose();
    }

}
