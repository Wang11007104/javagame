package com.wxk.starwar.lwjgl3;

import com.badlogic.gdx.Gdx;

public class autoMonster extends movingObj{
    
    
    


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
            case 4:
                bloodCount=2;
                break; 
            case 99://boss
                bloodCount=15;
                break;                                              
            case 100:
                bloodCount=10;
                break;        
            default:
                break;
        }
        oriBlood=bloodCount;
        //if(monMode==1){
       // vx=150;}

    }
    public void update(){
       
       float bx = Gdx.graphics.getWidth() - texture.getWidth();
       float by = Gdx.graphics.getHeight() - texture.getHeight();


      //  System.out.println(SkyWizard.allObjs.size());

        if(monMode==0){   //down子彈 
                vx=0;
                vy=-300;
                if(y<-150){
                    vy=0;
                    showImage=false;
                    SkyWizard.allObjs.remove(this);
                    return;
                }
        }




        if(monMode==1){   //up子彈 
            vx=0;
            vy=500;
            if(y>790){
                vy=0;
                showImage=false;
                SkyWizard.allObjs.remove(this);
                return;
            }
    }


        if(monMode==2){  //left monster
            
            
            if(x-oriX>-120 || x==bx ){// 向左120
                vx=-150;

            }
            else if(x-oriX<-250 || x==0){
                vx=150;
            }
            
            
        }


        if(monMode==3){  //right monster
            
            
            if(x-oriX>250 || x==bx ){
                vx=-150;

            }
            else if(x-oriX<120 || x==0){
                vx=150;
            }
            
        }

        if(monMode==4){  //only down 
            vx=0;
            vy=-150;

    }

        


        if(monMode==99){  //for boss
            
            
            if(x-oriX>-1 || x==bx ){
                vx=-150;

            }
            else if(x-oriX<-500 || x==0){
                vx=150;
            }
            
        }


        


        

        super.update();
        //45
       // System.out.println("bx="+ bx + " x="+x+ " oriX="+oriX );
      // System.out.println("by="+ vy + " y="+y+ " oriY="+oriY + showImage);

        
    }



}
