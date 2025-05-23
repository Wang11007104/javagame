package com.wxk.starwar.lwjgl3;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import java.util.ArrayList;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BombKing extends ApplicationAdapter {
    public static SpriteBatch batch;
    private static BitmapFont font;
    private Texture firstscreen;
    public static Texture explode;
    private Texture whiteTexture;
    public static int stageEvent = 0;
    private Stage stage;
    private ImageButton pageButton;
    private ImageButton pageButton1;
    private ImageButton pageButton2;
    private ImageButton starButton1;
    private ImageButton starButton2;
    private ShapeRenderer shapeRenderer;
    // private SpriteBatch batch1;
    public static BombKingObj bombPlayer1;
    public static BombKingObj bombPlayer2;
    public static autoMonster monster1;
    public static autoMonster monster2;
    public static autoMonster monster3;
    private Music backgroundMusic;
    private int countTimer = 0;
    private int Mode = 0;
    private ShapeRenderer shapeRenderer1;
    public boolean showImage = true;
    public static ArrayList<BombKingObj> maps = new ArrayList<>();
    public static ArrayList<BombKingObj> bombs = new ArrayList<>();
    public static ArrayList<BombKingObj> allObjs = new ArrayList<>();
    public static int countPoint = 0;
    public static int firstRender = 0;
    public boolean isWin;
    public static Map m;
    private Texture heartTexture;
    private Texture explodeBomb;
    private ImageButton starButton3;
    private ImageButton starButton4;
    public static float oriX;
    public static float oriY;
    public static float oriX1;
    public static float oriY1;

    /**
     * 在指定的螢幕座標 (x, y) 位置顯示文字。
     * 
     * 使用 LibGDX 的 SpriteBatch 與 BitmapFont 將文字繪製到畫面上。
     * 字型大小被放大為原來的 2 倍，文字顏色設為白色。
     * 
     * @param s 要顯示的文字內容，會轉為字串處理。
     * @param x 文字顯示的 X 座標（以畫面像素為單位）。
     * @param y 文字顯示的 Y 座標（以畫面像素為單位）。
     */
    public static void prints(Object s, int x, int y) {
        batch.begin();
        font.getData().setScale(2f);
        font.setColor(Color.WHITE);
        font.draw(batch, (String) s, (float) x, (float) y);
        batch.end();
    }


    /**
     * 處理鍵盤輸入事件，控制兩位玩家的移動與放置炸彈。
     * 
     * 玩家一（bombPlayer1）使用方向鍵（←↑↓→）控制移動，
     * 玩家二（bombPlayer2）使用 WASD 鍵控制移動。
     * 
     * 空白鍵（SPACE）控制玩家二放置炸彈。
     * 數字鍵盤 0（NUMPAD_0）控制玩家一放置炸彈。
     * 
     * 每次按鍵觸發一次動作，不會持續觸發。
     */
    public void keyClicked() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            bombPlayer1.moveLeft(); // 面向左
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            bombPlayer1.moveRight(); // 面右
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            bombPlayer1.moveUp(); // 面上
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            bombPlayer1.moveDown(); // 面下
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            bombPlayer2.moveLeft();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            bombPlayer2.moveRight();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            bombPlayer2.moveUp();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            bombPlayer2.moveDown();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            // bombfire(bombPlayer2);
            bombPlayer2.bombfire(bombPlayer2.x, bombPlayer2.y);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_0)) {
            // bombfire(bombPlayer1);
            bombPlayer1.bombfire(bombPlayer1.x, bombPlayer2.y);
        }

    }


    /**
     * 建立一個圖像按鈕並加入舞台，同時綁定點擊事件以改變遊戲狀態。
     *
     * 此方法會根據提供的圖片路徑建立一個 ImageButton，設定其大小與位置，
     * 並根據名稱指定按下後要觸發的事件（改變 stageEvent）。
     *
     * 支援的按鈕名稱包括：
     * - "buttonPlay"：設定 stageEvent 為 1，開始遊戲。
     * - "map1"：設定 stageEvent 為 21，載入地圖 1。
     * - "map2"：設定 stageEvent 為 22，載入地圖 2。
     * - "map3"：設定 stageEvent 為 23，載入地圖 3。
     *
     * @param picPath    按鈕使用的圖片檔案路徑
     * @param x          按鈕的 X 座標
     * @param y          按鈕的 Y 座標
     * @param w          按鈕寬度
     * @param h          按鈕高度
     * @param buttonName 指定按鈕的識別名稱（對應點擊後的事件）
     * @return 建立並加入舞台的 ImageButton 實例
     */
    public ImageButton addButton(String picPath, int x, int y, int w, int h, String buttonName) {
        ImageButton imageButton;
        // 載入圖片
        Texture buttonTexture = new Texture(Gdx.files.internal(picPath));
        TextureRegionDrawable buttonDrawable = new TextureRegionDrawable(buttonTexture);

        // 創建圖片按鈕
        imageButton = new ImageButton(buttonDrawable);
        imageButton.setPosition(x, y);
        imageButton.setSize(w, h);

        // 設定按鈕名稱，方便識別
        imageButton.setName(buttonName);

        // 加入點擊事件
        imageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // if(x>=79 && x<=269 &&y>=16 &&y<=83){

                String clickedButton = event.getListenerActor().getName(); // 取得按鈕名稱

                switch (clickedButton) {
                    case "buttonPlay":
                        stageEvent = 1;
                        break;
                    case "map1":
                        stageEvent = 21;
                        break;
                    case "map2":
                        stageEvent = 22;
                        break;
                    case "map3":
                        stageEvent = 23;
                        break;

                    default:
                        System.out.println("未知按鈕");

                }

                // }
            }
        });

        stage.addActor(imageButton);
        return imageButton;

    }

    /**
     * 初始化遊戲的主要元件與畫面元素。
     *
     * 此方法會在遊戲啟動時自動執行，負責以下內容：
     * <ul>
     * <li>建立舞台（Stage）並設定輸入控制。</li>
     * <li>建立開始按鈕與三張地圖選擇按鈕，並加入點擊事件。</li>
     * <li>載入心形圖案與炸彈圖案作為 UI 素材。</li>
     * <li>播放背景音樂，設定為循環播放與音量。</li>
     * <li>載入地圖資源。</li>
     * <li>初始化字型與 SpriteBatch。</li>
     * <li>建立玩家與怪物物件並加入遊戲物件清單中。</li>
     * </ul>
     *
     * 此方法應僅在遊戲初始化階段呼叫一次。
     */
    @Override
    public void create() {

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        starButton1 = addButton("play.png", 640 - 250, 360 - 150, 500, 500, "buttonPlay");
        starButton2 = addButton("map1.png", 320 - 100, 50, 100, 100, "map1");
        starButton3 = addButton("map2.png", 640 - 100, 50, 100, 100, "map2");
        starButton4 = addButton("map3.png", 960 - 100, 50, 100, 100, "map3");

        heartTexture = new Texture("health.png");
        explodeBomb = new Texture("bomb.png");

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("bgm.mp3"));

        // 設置循環播放
        backgroundMusic.setLooping(true);

        // 設置音量（範圍 0.0 ~ 1.0）
        backgroundMusic.setVolume(0.5f);

        // 開始播放
        backgroundMusic.play();

        backgroundMusic.setLooping(true);

        m = new Map("bombobj3.png");
        batch = new SpriteBatch();
        font = new BitmapFont(); // 預設字體
        font.getData().setScale(2f);

        bombPlayer1 = new BombKingObj("player", 1, 1, 48, 48, 9991);
        bombPlayer2 = new BombKingObj("player2", 13, 13, 48, 48, 9992);
        monster1 = new autoMonster("enemy", 4, 4, 48, 48, Mode, true);
        monster2 = new autoMonster("enemy", 8, 10, 48, 48, Mode, true);
        monster3 = new autoMonster("enemy", 12, 8, 48, 48, Mode, true);
        monster1.bloodCount = 10;
        monster2.bloodCount = 10;
        monster3.bloodCount = 10;

        allObjs.add(bombPlayer1);
        allObjs.add(bombPlayer2);
        allObjs.add(monster1);
        allObjs.add(monster2);
        allObjs.add(monster3);
    }

    /**
     * 每一幀呼叫一次，用來更新並繪製畫面內容。
     * 
     * 根據 `stageEvent` 狀態來決定目前顯示的畫面內容：
     * <ul>
     * <li>21、22、23：切換不同地圖。</li>
     * <li>0：顯示開始畫面，顯示按鈕。</li>
     * <li>1：進行遊戲邏輯更新與繪製，包括怪物行為、玩家狀態、生命值顯示等。</li>
     * <li>200：顯示結算畫面，並可點擊畫面回到主選單。</li>
     * </ul>
     * 
     * 同時執行以下任務：
     * <ul>
     * <li>清除畫面。</li>
     * <li>控制怪物自動移動與攻擊。</li>
     * <li>依據 `allObjs` 更新與繪製遊戲角色。</li>
     * <li>顯示雙方玩家的生命值與文字資訊。</li>
     * <li>處理 GAME OVER 結束條件與畫面回復。</li>
     * </ul>
     */
    @Override
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // 清除畫面

        if (stageEvent == 21) {
            m = new Map("bombobj1.png");
            stageEvent = 0;
        }

        if (stageEvent == 22) {
            m = new Map("bombobj2.png");
            stageEvent = 0;
        }

        if (stageEvent == 23) {
            m = new Map("bombobj3.png");
            stageEvent = 0;
        }

        if (stageEvent == 0) { // 開始畫面
            starButton1.setVisible(true);
            starButton2.setVisible(true);

            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();
        }

        if (stageEvent == 1) {
            starButton1.setVisible(false);
            starButton2.setVisible(false);

            countTimer = (countTimer + 1) % 1000000000;
            if (countTimer % 60 == 0) {
                monster1.monMode = MathUtils.random(0, 3);
                monster2.monMode = MathUtils.random(0, 3);
                monster3.monMode = MathUtils.random(0, 3);
                if (allObjs.contains(monster1)) {
                    monster1.bombfire(monster1.x, monster1.y);
                }
                if (allObjs.contains(monster2)) {
                    monster2.bombfire(monster2.x, monster2.y);
                }
                if (allObjs.contains(monster3)) {
                    monster3.bombfire(monster3.x, monster3.y);
                }
                if (monster1.bloodCount <= 0) {
                    allObjs.remove(monster1);
                    monster1.allRestore();
                }
                if (monster2.bloodCount <= 0) {
                    allObjs.remove(monster2);
                    monster2.allRestore();
                }
                if (monster3.bloodCount <= 0) {
                    allObjs.remove(monster3);
                    monster3.allRestore();
                }
            }

            m.draw(batch);

            for (int i = 0; i < allObjs.size(); i++) {
                BombKingObj obj = allObjs.get(i);

                if (obj instanceof autoMonster && countTimer % 60 == 0) {
                    obj.update();
                }

                if (obj.showImage) { // 利用是否顯示方式關掉圖片
                    obj.draw(batch);
                }
            }

            keyClicked();

            if (bombPlayer1.bloodCount <= 0 || bombPlayer2.bloodCount <= 0) {
                stageEvent = 200;
            }

            prints("player1", 20, 700);
            prints("player2", 1025, 700);

            batch.begin();
            for (int i = 0; i < bombPlayer1.bloodCount; i++) {
                batch.draw(heartTexture, 20 + i * 40, 600, 32, 32); // x, y, width, height
            }
            for (int i = 0; i < bombPlayer2.bloodCount; i++) {
                batch.draw(heartTexture, 1025 + i * 40, 600, 32, 32); // y 改 550 讓兩排不重疊
            }
            batch.end();
        }
        if (stageEvent == 200) { // 結算畫面
            batch.begin();
            font.getData().setScale(3f);
            font.setColor(Color.WHITE);

            font.draw(batch, "GAME OVER", 400, 600);

            font.getData().setScale(2f);
            font.draw(batch, "Remaining player1 BLOOD: " + bombPlayer1.bloodCount, 400, 500);
            font.draw(batch, "Remaining player2 BLOOD: " + bombPlayer2.bloodCount, 400, 400);
            font.draw(batch, "Use mouse to click anywhere to return", 400, 200);
            batch.end();

            if (Gdx.input.justTouched()) {
                bombPlayer1.allRestore();
                bombPlayer2.allRestore();
                allObjs.add(monster1);
                allObjs.add(monster2);
                allObjs.add(monster3);

                firstRender = 0;
                stageEvent = 0;
                countPoint = 0;

            }
            return;
        }

    }

    @Override
    public void dispose() {
        backgroundMusic.dispose();
        batch.dispose();
        for (Texture tex : m.tileTextures.values()) {
            tex.dispose();
        }
    }
}