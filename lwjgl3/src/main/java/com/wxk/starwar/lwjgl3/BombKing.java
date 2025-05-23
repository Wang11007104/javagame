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
    public static int stageEvent=0;
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
    private int countTimer=0;
    private int Mode = 0;
    private ShapeRenderer shapeRenderer1;
    public boolean showImage=true;
    public static ArrayList<BombKingObj> maps = new ArrayList<>();
    public static ArrayList<BombKingObj> bombs = new ArrayList<>();
    public static ArrayList<BombKingObj> allObjs = new ArrayList<>();
    public static int countPoint=0;
    public static int firstRender=0;
    public boolean isWin;
    public static Map m;
    private Texture heartTexture;
    private Texture explodeBomb;
    private ImageButton starButton3;
    private ImageButton starButton4;
    public float oriX;
    public float oriY;

    

    public static void prints(Object s,int x,int y){
        batch.begin();
            font.getData().setScale(2f);
            font.setColor(Color.WHITE);
            font.draw(batch, (String)s, (float)x, (float)y);
        batch.end();
    }


    public void bombfire(BombKingObj p){
        oriX=p.x;
        oriY=p.y;
        autoMonster wizardBullet = new autoMonster("fire", p.x, p.y, 48, 48, 2222,true);
        autoMonster explodeBomb = new autoMonster("bomb",  oriX, oriY, 48, 48, 222223,true);
        autoMonster explodeBomb1 = new autoMonster("bomb",  oriX+1, oriY, 48, 48, 222223,true);
        autoMonster explodeBomb2 = new autoMonster("bomb",  oriX-1, oriY, 48, 48, 222223,true);
        autoMonster explodeBomb3 = new autoMonster("bomb",  oriX, oriY+1, 48, 48, 222223,true);
        autoMonster explodeBomb4 = new autoMonster("bomb",  oriX, oriY-1, 48, 48, 222223,true);
        allObjs.add(0, wizardBullet);


            float delaySeconds = 3f; // 3 秒後執行

            Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                m.bomb((int)wizardBullet.x,(int) wizardBullet.y);


                Sound clickSound = Gdx.audio.newSound(Gdx.files.internal("fire.mp3"));
                
                clickSound.play();
                
                allObjs.remove(wizardBullet);
                
                allObjs.add(0, explodeBomb);
                allObjs.add(0, explodeBomb1);
                allObjs.add(0, explodeBomb2);
                allObjs.add(0, explodeBomb3);
                allObjs.add(0, explodeBomb4);


                Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                 m.bomb((int)explodeBomb.x,(int) explodeBomb.y);
                allObjs.remove(explodeBomb);   
                allObjs.remove(explodeBomb1);
                allObjs.remove(explodeBomb2); 
                allObjs.remove(explodeBomb3);
                allObjs.remove(explodeBomb4);

                
            }
            }, (float) 0.5);


                
                
                
            }
            }, delaySeconds);


    }



    private void keyClicked(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            bombPlayer1.moveLeft();  //面向左
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            bombPlayer1.moveRight();  //面右
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            bombPlayer1.moveUp();   //面上
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            bombPlayer1.moveDown();   //面下
        }
        
        


        if (Gdx.input.isKeyJustPressed(Input.Keys.A)){
            bombPlayer2.moveLeft();
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.D)){
            bombPlayer2.moveRight();
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.W)){
            bombPlayer2.moveUp();
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.S)){
            bombPlayer2.moveDown();
        }
       




        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
           bombfire(bombPlayer2);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_0)){
            bombfire(bombPlayer1);
        }



    }

    private  ImageButton addButton(String picPath,int x,int y,int w,int h, String buttonName){
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

        switch(clickedButton){
        case "buttonPlay":
            stageEvent=1;
            break;
        case "map1":
            stageEvent=21;
            break;
        case "map2":
            stageEvent=22;
            break;
        case "map3":
            stageEvent=23;
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
    
    @Override
    public void create() {

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        starButton1 = addButton("play.png", 640-250, 360-150, 500, 500, "buttonPlay");
        starButton2 = addButton("map1.png", 320-100, 50, 100, 100, "map1");
        starButton3 = addButton("map2.png", 640-100, 50, 100, 100, "map2");
        starButton4 = addButton("map3.png", 960-100, 50, 100, 100, "map3");


        heartTexture=new Texture("health.png");
        explodeBomb= new Texture("bomb.png");


         backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("bgm.mp3"));
        
        // 設置循環播放
        backgroundMusic.setLooping(true);
        
        // 設置音量（範圍 0.0 ~ 1.0）
        backgroundMusic.setVolume(0.5f);
        
        // 開始播放
        backgroundMusic.play();

        backgroundMusic.setLooping(true);

        

        m=new Map("bombobj3.png");
        batch = new SpriteBatch();
        font = new BitmapFont(); // 預設字體
        font.getData().setScale(2f);

        bombPlayer1 = new BombKingObj("player", 1, 1, 48, 48, 9991);
        bombPlayer2 = new BombKingObj("player2", 13, 13, 48, 48, 9992);
        monster1 = new autoMonster("enemy", 4, 4, 48, 48, Mode,true);
        monster2 = new autoMonster("enemy", 8, 10, 48, 48, Mode,true);
        monster3 = new autoMonster("enemy", 12, 8, 48, 48, Mode,true);
        monster1.bloodCount=1;
        monster2.bloodCount=1;
        monster3.bloodCount=1;

        allObjs.add(bombPlayer1);
        allObjs.add(bombPlayer2);
        allObjs.add(monster1);
        allObjs.add(monster2);
        allObjs.add(monster3);
    }

    @Override
    public void render() {

        
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // 清除畫面

        if(stageEvent == 21){
           m=new Map("bombobj1.png");
           stageEvent=0; 
        }

        if(stageEvent == 22){
           m=new Map("bombobj2.png");
           stageEvent=0; 
        }

        if(stageEvent == 23){
           m=new Map("bombobj3.png");
           stageEvent=0; 
        }
        



        if (stageEvent == 0) { // 開始畫面
            starButton1.setVisible(true);
            starButton2.setVisible(true);
        

            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();
        }

        if(stageEvent==1){
            starButton1.setVisible(false);
            starButton2.setVisible(false);


        countTimer = (countTimer + 1) % 1000000000;
        if(countTimer % 60 == 0){
            monster1.monMode = MathUtils.random(0, 3);
            monster2.monMode = MathUtils.random(0, 3);
            monster3.monMode = MathUtils.random(0, 3);
            if(monster1.bloodCount<=0){
                allObjs.remove(monster1);
                monster1.allRestore();
            }
            if(monster2.bloodCount<=0){
                allObjs.remove(monster2);
                monster2.allRestore();
            }
            if(monster3.bloodCount<=0){
                allObjs.remove(monster3);
                monster3.allRestore();
            }
        }

        



        

       // prints("Player1 Lives:\n"+"O".repeat(Math.max(bombPlayer1.bloodCount,0)),0,600);
       // prints("Player2 Lives:\n"+"O".repeat(Math.max(bombPlayer2.bloodCount,0)),0,500);

        

        
        m.draw(batch);


        for(int i=0;i<allObjs.size();i++){
            BombKingObj obj =allObjs.get(i);
            //obj.update();
            //System.out.println(obj.showImage);

            if(obj instanceof autoMonster && countTimer % 60 == 0){
                obj.update();
            }

            if(obj.showImage){  //利用是否顯示方式關掉圖片
            obj.draw(batch);
            }
        }

        keyClicked();

        //System.out.println("1:"+bombPlayer1.bloodCount);
        //System.out.println(bombPlayer2.bloodCount);
        if(bombPlayer1.bloodCount<=0 || bombPlayer2.bloodCount<=0){
            stageEvent=200;
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
    if (stageEvent == 200) {  // 結算畫面
            batch.begin();
            font.getData().setScale(3f);
            font.setColor(Color.WHITE);
        
            font.draw(batch, "GAME OVER", 400, 600);
        
            font.getData().setScale(2f);
            font.draw(batch, "Remaining player1 BLOOD: " + bombPlayer1.bloodCount, 400, 500);
            font.draw(batch, "Remaining player2 BLOOD: " + bombPlayer2.bloodCount, 400, 400);
            font.draw(batch, "Use mouse to click anywhere to return", 400, 200);
            batch.end();

        if (Gdx.input.justTouched() ) {
            bombPlayer1.allRestore();
            bombPlayer2.allRestore();
            allObjs.add(monster1);
            allObjs.add(monster2);
            allObjs.add(monster3);

            firstRender=0;
            stageEvent=0;
            countPoint=0;

            
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