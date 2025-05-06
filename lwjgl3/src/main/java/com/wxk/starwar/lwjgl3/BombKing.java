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
    private BitmapFont font;
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
    public static movingObj wizardPlayer;
    private autoMonster monster1;
    private autoMonster monster2;
    private autoMonster monster3;
    private Music backgroundMusic;
    private int countTimer=0;
    private ShapeRenderer shapeRenderer1;
    public boolean showImage=true;
    public static ArrayList<movingObj> maps = new ArrayList<>();
    public static ArrayList<movingObj> bombs = new ArrayList<>();
    public static ArrayList<movingObj> allObjs = new ArrayList<>();
    public static int countPoint=0;
    public static int firstRender=0;
    private Timer.Task timerHandle;
    private int previousBloodCount=15;
    private float bloodLine;
    private  int monster3OriBlood;
    private Map m;

    


    private void keyClicked(){
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            wizardPlayer.vx=-300;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            wizardPlayer.vx=300;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            wizardPlayer.vy=300;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            wizardPlayer.vy=-300;
        }
        else{
            wizardPlayer.vx=0;
            wizardPlayer.vy=0;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            autoMonster wizardBullet = new autoMonster("fire.png", wizardPlayer.x+20, wizardPlayer.y+wizardPlayer.h+10, 45, 50, 1,true);
            allObjs.add(wizardBullet);

            Sound clickSound = Gdx.audio.newSound(Gdx.files.internal("fire.mp3"));
            clickSound.play();
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


        
        m=new Map("C:\\Users\\User\\OneDrive\\桌面\\image\\bombobj.png");
        batch = new SpriteBatch();



        
    }

    @Override
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // 清除畫面

        m.draw(batch);
     
    }

    @Override
    public void dispose() {
        batch.dispose();
        for (Texture tex : m.tileTextures.values()) {
            tex.dispose();
        }
        
    }
}