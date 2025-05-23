package com.wxk.starwar.lwjgl3;

import com.badlogic.gdx.Gdx;

public class autoMonster extends BombKingObj{
    public autoMonster(String texturePath, float startX, float startY,float width,float height,int monMode,boolean showImage){
        super(texturePath, startX, startY, width, height,monMode);
        this.monMode=monMode;
        this.showImage=showImage;
        oriX=startX;
        oriY=startY;
        
        oriBlood=bloodCount;
        //if(monMode==1){
       // vx=150;}

    }
    /**
 * 根據物件的 monMode 屬性自動移動角色。
 * 
 * - monMode 為 0：向右移動（moveRight）
 * - monMode 為 1：向下移動（moveDown）
 * - monMode 為 2：向左移動（moveLeft）
 * - monMode 為 3：向上移動（moveUp）
 * 
 * 此方法根據目前的 monMode 呼叫對應的移動方法，
 * 達成簡單的自動移動邏輯。
 */
    public void update(){
       
       float bx = Gdx.graphics.getWidth() - textureF.getWidth();
       float by = Gdx.graphics.getHeight() - textureF.getHeight();



        if(monMode==0){
            moveRight();
        }

        if(monMode==1){
            moveDown();
        }

        if(monMode==2){
            moveLeft();
        }

        if(monMode==3){
            moveUp();
          
            
        }
    }
}