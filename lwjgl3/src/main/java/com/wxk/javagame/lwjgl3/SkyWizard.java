package com.wxk.javagame.lwjgl3;



import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import java.util.ArrayList;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class SkyWizard extends ApplicationAdapter {
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture firstscreen;
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
    private autoMonster monster4;
    private autoMonster monster5;
    private autoMonster monster6;
    private autoMonster monster7;
    private autoMonster monster8;
    private autoMonster monster9;
    private autoMonster monster10;
    private autoMonster monster11;
    private autoMonster monster12;
    private Music backgroundMusic;


    public boolean showImage=true;
    //public  static movingObj[] allObjs=new movingObj[4];
    public static ArrayList<movingObj> allObjs = new ArrayList<>();
    
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
            var wizardBullet = new autoMonster("fire.png", wizardPlayer.x+20, wizardPlayer.y+wizardPlayer.h+10, 45, 50, 1,true);
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
        case "buttonCheat":
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





        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.dispose();
        batch = new SpriteBatch();
        font = new BitmapFont(); // 預設字體
        //font.getData().setScale(6); // 額外放大
        firstscreen = new Texture(Gdx.files.internal("firstscreen.png"));  // 加載圖片
       





        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        starButton1=addButton("buttonplay.png", 200, 330,375,100,"buttonPlay");
       // starButton1.setVisible(false);

        starButton2=addButton("buttonIns.png", 230, 185,375,100,"buttonIns");
       // starButton2.setVisible(false);

        starButton3=addButton("buttonCheat.png", 200, 50,375,100,"buttonCheat");
       // starButton3.setVisible(false);

        pageButton = addButton("insSpace1.png", 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), "insSpace1");
        pageButton.setVisible(false);

        pageButton1 = addButton("insUDLR1.png", 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), "insUDLR1");
        pageButton1.setVisible(false);


        shapeRenderer = new ShapeRenderer();


       // batch = new SpriteBatch();
        //wizardPlayer = new movingObj("wizard.png", 260, 0, 75,100,200);
        wizardPlayer = new movingObj("wizard.png", 0, 0, 75,100,200);



        
        monster1 = new autoMonster("wizard.png", 300, 350, 75, 100, 2,true);
        monster2 = new autoMonster("ghost.png", 400, 350, 75, 100, 3,true);
        monster3 = new autoMonster("dragon1.png", 300, 500, 300, 300, 99,true);

        monster4 = new autoMonster("wizard.png", 500, 800, 75, 100, 0,true);
        monster5 = new autoMonster("wizard.png", 500, 800, 75, 100, 0,true);
        monster6 = new autoMonster("wizard.png", 500, 800, 75, 100, 0,true);
        monster7 = new autoMonster("wizard.png", 500, 800, 75, 100, 0,true);
        monster8 = new autoMonster("wizard.png", 500, 800, 75, 100, 0,true);
        monster9 = new autoMonster("wizard.png", 500, 800, 75, 100, 0,true);
        monster10 = new autoMonster("wizard.png", 500, 800, 75, 100, 0,true);
        monster11 = new autoMonster("wizard.png", 500, 800, 75, 100, 0,true);
        monster12 = new autoMonster("wizard.png", 500, 800, 75, 100, 0,true);



        allObjs.add(wizardPlayer);
        allObjs.add(monster1);
        allObjs.add(monster2);
        allObjs.add(monster3);
        
       
        
        
        




    }

    @Override
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // 清除畫面


       

              
               
      //  System.out.println(stageEvent);
        if(stageEvent==0){  //  開始畫面
            starButton1.setVisible(true);
            starButton2.setVisible(true);
            starButton3.setVisible(true);
            
               batch.begin();
               batch.setColor(0.53f, 0.81f, 0.92f, 1f);
               batch.draw(firstscreen, 0, 0, 600, 800);
               batch.end();


               stage.act(Gdx.graphics.getDeltaTime());
               stage.draw();
        }


        if(stageEvent==1){  //  遊戲畫面
            starButton1.setVisible(false);
            starButton2.setVisible(false);
            starButton3.setVisible(false);


        
        keyClicked();

 
        for(int i=0;i<allObjs.size();i++){
            movingObj obj =allObjs.get(i);

            //System.out.println(obj.monMode);
            //System.out.println("s= "+allObjs.size());

            obj.update();
            if(obj.showImage){  //利用是否顯示方式關掉圖片
            obj.draw(batch);
            }
        }


        if(allObjs.size()==1){
            stageEvent=11;
        }


        /* 
        wizardPlayer.update(); // 更新飛船
        wizardPlayer.draw(batch); // 繪製飛船

        //System.out.println("x="+wizardPlayer.x+ "y="+wizardPlayer.y);
        //System.out.println(Gdx.graphics.getWidth());

        monster1.update();
        if(monster1.showImage){  //利用是否顯示方式關掉圖片
        monster1.draw(batch);
        }
        



        monster2.update();
        if(monster2.showImage){  //利用是否顯示方式關掉圖片
        monster2.draw(batch);
        }



        monster3.update();
        monster3.draw(batch);
        */
          


       

        }


        if(stageEvent==11){
            
        }

        if(stageEvent==2){  //   點擊介紹後畫面
            //addButton("insSpace.png", 100, 100, 100, 100, "insSpace");
            pageButton.setVisible(true);


            shapeRenderer.begin((ShapeRenderer.ShapeType.Filled));
            shapeRenderer.setColor(0.53f, 0.81f, 0.92f, 1f);
            shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

            shapeRenderer.end();


            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();
            starButton1.setVisible(false);
            starButton2.setVisible(false);
            starButton3.setVisible(false);



        }




        if(stageEvent==21){  //  介紹畫面下第一張
            //addButton("insSpace.png", 100, 100, 100, 100, "insSpace");
            pageButton1.setVisible(true);


            shapeRenderer.begin((ShapeRenderer.ShapeType.Filled));
            shapeRenderer.setColor(0.53f, 0.81f, 0.92f, 1f);
            shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

            shapeRenderer.end();


            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();

            

        }






        if(stageEvent==3){
            starButton1.setVisible(false);
            starButton2.setVisible(false);
            starButton3.setVisible(false);

        }
        
        
       
       // System.out.println("hi");
     
    }

    @Override
    public void dispose() {
        backgroundMusic.dispose();
        batch.dispose();
        font.dispose();
        if (whiteTexture != null) {
            whiteTexture.dispose();  // 釋放資源
        }
        stage.dispose();

        if (wizardPlayer != null) {
            wizardPlayer.dispose();  // 釋放資源
        }
        
    }
}