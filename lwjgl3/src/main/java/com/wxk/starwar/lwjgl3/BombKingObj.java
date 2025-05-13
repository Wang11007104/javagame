package com.wxk.starwar.lwjgl3;
import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BombKingObj {
    public Texture texture;
    public float x, y;//即時的位置
    public float w,h;
    public float vx,vy;
   // public boolean offImage=false;
    public int monMode;
    public boolean showImage=true;
    public int bloodCount=10,oriBlood=10;//wizaed.blood
    public float oriX,oriY;
    public static float explodeX,explodeY,explodeCount;


    

    public BombKingObj(String texturePath, float startX, float startY,float width,float height,int monMode) {
        this.texture = new Texture(texturePath);  // 加載飛船圖片
        this.x = startX;  // 設定飛船的 X 座標
        this.y = startY;  // 設定飛船的 Y 座標
        oriX=startX;
        oriY=startY;
        this.w=width;
        this.h=height;
        this.monMode=monMode;
        vx=0;
        vy=0;

    }

    // 更新飛船位置
    
    public void update() {
        float unmovX,unmovY;
        unmovX=x;
        unmovY=y;



        float deltaTime = Gdx.graphics.getDeltaTime();
        x += vx * deltaTime;
        y += vy * deltaTime;
      


        // 限制邊界
        if(monMode!=0 &&monMode!=1){ //給超出範圍後刪掉mode0需要出邊界
        x = Math.max(0, Math.min(x, Gdx.graphics.getWidth()+80 - texture.getWidth()));
        y = Math.max(0, Math.min(y, Gdx.graphics.getHeight()+100 - texture.getHeight()));
        }

           int index = BombKing.allObjs.indexOf(this);
           //System.out.println(index);
          //  System.err.println();
           for(int i=index;i<BombKing.allObjs.size();i++){
           // System.err.print(i);
            BombKingObj obj =BombKing.allObjs.get(i);


            if(this!=obj){  //自己不跟自己碰撞
                boolean b=collide(this,obj);
                //System.out.println(b);
                if(b){

                    boolean areBullets = (this.monMode<2 && obj.monMode<2);
                    boolean areMonsters = (this.monMode>=2 && this.monMode<100 && obj.monMode>=2 && obj.monMode<100);
                   
                   
                    if(!(areMonsters)){
                   
                    if(  !( areBullets )  ){// 碰撞扣血
                    this.bloodCount-=1;
                    obj.bloodCount--;
                    if(this.monMode==1 ){
                         explodeX=this.x;
                         explodeY=this.y;
                         explodeCount=5;
                    }
                    if(obj.monMode==1 ){
                         explodeX=obj.x;
                         explodeY=obj.y;
                         explodeCount=5;
                    }
                    
                    
                    }    
            
                    if(obj!= BombKing.bombPlayer1  && obj.bloodCount<=0){
                    BombKing.allObjs.remove(obj);
                    BombKing.countPoint++;
                    }
                    if(this!= BombKing.bombPlayer1 && this.bloodCount<=0){
                    BombKing.allObjs.remove(this);
                    BombKing.countPoint++;
                    }

                    }
                    
                    
                    
                    
                    
                    if(BombKing.bombPlayer1.bloodCount==0){
                        BombKing.stageEvent=100;
                    }



                    /* 不要動
                    x=unmovX;
                    y=unmovY;
                    */
                }
            }
        }





    }

    // 繪製飛船
    public void draw(SpriteBatch batch) {
        batch.begin();
        Point p= Map.realXY((int)x,(int)y);
        batch.draw(texture, p.x, p.y,w,h);
        batch.end();
    }

    
    public boolean collide(BombKingObj obj1,BombKingObj obj2){

        //System.out.println("x="+obj1.x+" x2="+obj2.x);

        if((obj2.x-obj1.x<=obj1.w && obj2.x-obj1.x>=-obj2.w) && 
        (obj2.y-obj1.y<=obj1.h && obj2.y-obj1.y>=-obj2.h+20)){
            return true;
        }
        else{
            return false;
        }
    }



    public void allRestore(){
        x=oriX;
        y=oriY;
        bloodCount=oriBlood;
        BombKing.firstRender=0;
        BombKing.stageEvent=0;
        BombKing.countPoint=0;
    }

   






    // 釋放資源
    public void dispose() {
        texture.dispose();
    }







}
