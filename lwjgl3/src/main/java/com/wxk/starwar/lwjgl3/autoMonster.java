package com.wxk.starwar.lwjgl3;

import com.badlogic.gdx.Gdx;

public class autoMonster extends BombKingObj{
    public autoMonster(String texturePath, float startX, float startY,float width,float height,int monMode,boolean showImage){
        super(texturePath, startX, startY, width, height,monMode);
        this.monMode=monMode;
        this.showImage=showImage;
        oriX=startX;
        oriY=startY;
        
        switch (monMode) {
            case 0:
                bloodCount=1;
                break;
            case 1:
                bloodCount=1;
                break;  
            case 2:
                bloodCount=3;
                break;  
            case 3:
                bloodCount=2;
                break;
            default:
                break;
        }
        oriBlood=bloodCount;
        //if(monMode==1){
       // vx=150;}

    }
    public void update(){
       
       float bx = Gdx.graphics.getWidth() - textureF.getWidth();
       float by = Gdx.graphics.getHeight() - textureF.getHeight();


      //  System.out.println(SkyWizard.allObjs.size());

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