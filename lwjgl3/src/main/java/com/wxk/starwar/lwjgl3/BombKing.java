package com.wxk.starwar.lwjgl3;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import java.util.ArrayList;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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
    private ImageButton starButton3;
    private ShapeRenderer shapeRenderer;
   // private SpriteBatch batch1;
    public static BombKingObj bombPlayer1;
    public static BombKingObj bombPlayer2;
    private autoMonster monster1;
    private autoMonster monster2;
    private autoMonster monster3;
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
    private Timer.Task timerHandle;
    private int previousBloodCount=15;
    private float bloodLine;
    private  int monster3OriBlood;
    public boolean isWin;
    public static Map m;

    

    public static void prints(Object s,int x,int y){
        batch.begin();
            font.getData().setScale(2f);
            font.setColor(Color.WHITE);
            font.draw(batch, (String)s, (float)x, (float)y);
        batch.end();
    }


    public void bombfire(BombKingObj p){
        autoMonster wizardBullet = new autoMonster("fire", p.x, p.y, 48, 48, 2222,true);
        
        allObjs.add(0, wizardBullet);


            float delaySeconds = 3f; // 3 秒後執行

            Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                //System.out.println("3 秒到了！執行任務！"+wizardBullet.x);
                m.bomb((int)wizardBullet.x,(int) wizardBullet.y);


                Sound clickSound = Gdx.audio.newSound(Gdx.files.internal("fire.mp3"));
                clickSound.play();
                allObjs.remove(wizardBullet);
                
                
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
       // System.out.println("Play" );
        stageEvent=1;
        break;
        case "buttonIns":
       // System.out.println(" Ins");
        stageEvent=2;
        break;
        case "insSpace1":
       // System.out.println(" InsSpace1");
        stageEvent=21;
        break;
        case "insUDLR1":
       // System.out.println(" insUDLR1");
        stageEvent=0;
        pageButton.setVisible(false);
        pageButton1.setVisible(false);
        break;
        case "buttonLevel":
      //  System.out.println(" Cheat");
        stageEvent=3;
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


         backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("bgm.mp3"));
        
        // 設置循環播放
        backgroundMusic.setLooping(true);
        
        // 設置音量（範圍 0.0 ~ 1.0）
        backgroundMusic.setVolume(0.5f);
        
        // 開始播放
        backgroundMusic.play();

        backgroundMusic.setLooping(true);

        

        m=new Map("bombobj.png");
        batch = new SpriteBatch();
        font = new BitmapFont(); // 預設字體
        font.getData().setScale(2f);

        bombPlayer1 = new BombKingObj("player", 1, 1, 48, 48, 9991);
        bombPlayer2 = new BombKingObj("player2", 2, 2, 48, 48, 9992);
        monster1 = new autoMonster("enemy", 5, 5, 48, 48, Mode,true);

        
        allObjs.add(bombPlayer1);
        allObjs.add(bombPlayer2);
        allObjs.add(monster1);
        
        
    }

    @Override
    public void render() {
        countTimer = (countTimer + 1) % 1000000000;
        if(countTimer % 60 == 0){
            monster1.monMode = MathUtils.random(0, 3);
            System.out.println("hello");
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // 清除畫面

        prints("Player1 Lives:\n"+"O".repeat(Math.max(bombPlayer1.bloodCount,0)),0,600);
        prints("Player2 Lives:\n"+"O".repeat(Math.max(bombPlayer2.bloodCount,0)),0,500);

        if (stageEvent == 200) {  // 結算畫面
            batch.begin();
            font.getData().setScale(3f);
            font.setColor(Color.WHITE);
        
            font.draw(batch, "GAME OVER", 180, 600);
        
            font.getData().setScale(2f);
            font.draw(batch, "Remaining player1 BLOOD: " + bombPlayer1.bloodCount, 200, 500);
            font.draw(batch, "Remaining player2 BLOOD: " + bombPlayer2.bloodCount, 200, 400);
            font.draw(batch, "Use mouse to click anywhere to return", 60, 200);
            batch.end();

        if (Gdx.input.justTouched() ) {
            bombPlayer1.allRestore();
            bombPlayer2.allRestore();


            firstRender=0;
            stageEvent=0;
            countPoint=0;

            
        }
        return;  
        }

        
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
        if(bombPlayer1.bloodCount*bombPlayer2.bloodCount==0){
            stageEvent=200;
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