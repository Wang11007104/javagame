package com.wxk.starwar.lwjgl3;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

public class test {
    public static void main(String[] args) {
        Map m=new Map("C:\\Users\\User\\OneDrive\\桌面\\image\\bombobj.png");
        for(int i=0; i<m.mapArray.length;i++){
           System.out.println(m.mapArray[i]);
        }
        
    }

    public static void main1(String[] args) {
        try {
            // 讀取圖片檔案（路徑可換成你自己的圖片）
            File imageFile = new File("C:\\Users\\User\\OneDrive\\桌面\\image\\bombobj.png");
            BufferedImage image = ImageIO.read(imageFile);
            int w=image.getWidth();
            int h=image.getHeight();
            

            int[] mapArray = new int[w * h];

            // 指定要取得的座標
            int x = 14;
            int y = 14;

            // 取得該座標的 RGB 顏色

            image.getRGB(0,0,w,h,mapArray,0,w);
            for (int i = 0; i < mapArray.length; i++) {
                int rgb = mapArray[i];
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;
            
                System.out.printf("Pixel %d: R=%d, G=%d, B=%d%n", i, r, g, b);
            }

        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
