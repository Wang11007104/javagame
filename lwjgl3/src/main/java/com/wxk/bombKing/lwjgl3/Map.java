package com.wxk.starwar.lwjgl3;

import com.badlogic.gdx.utils.IntIntMap;
import com.badlogic.gdx.utils.ObjectMap;
import javax.imageio.ImageIO;

import org.lwjgl.system.Pointer;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.IntMap;

public class Map {
    private IntIntMap items = new IntIntMap();
    public int[] mapArray;
    public IntMap<Texture> tileTextures;// 磚塊材料

    /**
     * 根據指定的圖檔建立地圖，並依據圖像的像素顏色對應地圖中的不同地形類型。
     * <p>
     * 建構函式會完成以下工作：
     * <ul>
     * <li>將指定顏色值對應為地圖格子中的地形類型（例如：白色代表道路，土色代表牆壁等）。</li>
     * <li>載入對應地形所需的圖片材質。</li>
     * <li>讀取指定的圖檔，根據像素顏色建立一維地圖陣列。</li>
     * <li>圖檔的每個像素被轉換為整數型別的地形編號儲存於 {@code mapArray} 陣列中，供後續判斷及繪製使用。</li>
     * </ul>
     *
     * @param fileName 圖檔的路徑（位於 classpath 中），該圖檔為彩色 PNG，每種顏色代表一種地形。
     */
    public Map(String fileName) {

        items.put(0xFFFFFF, 1); // 白色
        items.put(0xFF7F27, 2); // 土色
        items.put(0xFFF200, 3); // 黃色
        items.put(0x3F48CC, 4); // 藍色
        items.put(0x22B14C, 5); // 綠色

        tileTextures = new IntMap<>();

        tileTextures.put(1, new Texture("road.png"));
        tileTextures.put(2, new Texture("wall.png"));
        tileTextures.put(3, new Texture("box.png"));
        tileTextures.put(4, new Texture("house.png"));
        tileTextures.put(5, new Texture("tree.png"));

        try {
            // 讀取圖片檔案（路徑可換成你自己的圖片）

            FileHandle imageFile = (Gdx.files.classpath(fileName));
            BufferedImage image = ImageIO.read(imageFile.read());
            int w = image.getWidth();
            int h = image.getHeight();

            mapArray = new int[w * h];

            // 取得該座標的 RGB 顏色

            image.getRGB(0, 0, w, h, mapArray, 0, w);
            for (int i = 0; i < mapArray.length; i++) {
                int rgb = mapArray[i];
                
                rgb = rgb & 0xffffff;//限定需要的RGB
                mapArray[i] = items.get(rgb, 0);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 將地圖中的所有圖塊繪製到畫面上。
     * <p>
     * 這個方法會根據 {@code mapArray} 中儲存的地形編號，
     * 使用對應的 {@code tileTextures} 貼圖資料將每一格繪製到指定位置。
     * 每個圖塊的大小為 48x48 像素，並以固定偏移量（+280 px）對齊左邊界，
     * 座標的計算方式可對應圖檔的像素位置。
     * </p>
     *
     * @param batch 用於繪製的 SpriteBatch 物件，需由呼叫者提供並管理其生命週期。
     */
    public void draw(SpriteBatch batch) {
        batch.begin();

        for (int i = 0; i < mapArray.length; i++) {
            int x = 0, y = 0;
            x = i % 15;
            y = i / 15;

            batch.draw(tileTextures.get(1), x * 48 + 280, 720 - y * 48 - 48, 48, 48);

            batch.draw(tileTextures.get(mapArray[i]), x * 48 + 280, 720 - y * 48 - 48, 48, 48);

        }

        batch.end();
    }

    /**
     * 將二維地圖座標 (x, y) 轉換為一維陣列的索引值。
     * <p>
     * 此方法假設地圖每列有 15 格（固定寬度），因此可以透過公式 {@code y * 15 + x}
     * 將二維座標線性映射到陣列索引中，用來存取 {@code mapArray} 內對應的格子。
     * 請注意：傳入的 {@code x}, {@code y} 應該已經是地圖格子座標（非像素座標）。
     * </p>
     *
     * @param x 地圖格子的 X 座標（橫向）
     * @param y 地圖格子的 Y 座標（縱向）
     * @return 對應在一維陣列中的索引值
     */
    public static int xyToI(int x, int y) {
        // x=(x-280)/48;
        // y=((720-y)-48)/48;
        return y * 15 + x;
    }

    /**
     * 將地圖格子座標 (x, y) 轉換為實際的像素座標，用來在畫面上繪製物件。
     * <p>
     * 地圖的每個格子為 48x48 像素，畫面上的地圖有一個水平偏移量（+280 像素），
     * 並且從畫面頂部往下排列（所以 Y 方向需要反轉）。
     * 此方法會將地圖的格子座標轉成畫面上的像素位置，方便用於 Sprite 繪製。
     * </p>
     *
     * @param x 地圖格子的 X 座標（橫向）
     * @param y 地圖格子的 Y 座標（縱向）
     * @return 對應的畫面像素座標，回傳為 {@code java.awt.Point}
     */
    public static Point realXY(int x, int y) {
        return new Point(x * 48 + 280, 720 - y * 48 - 48);
    }

    /**
     * 判斷指定索引位置上的地圖格子是否可以被炸毀（破壞）。
     * <p>
     * 根據地圖數值定義：
     * <ul>
     * <li>1 表示道路，可被炸毀（雖然邏輯上應該是空地，但可能允許炸出特效）</li>
     * <li>3 表示箱子，可被炸毀</li>
     * </ul>
     * 此方法會在投擲炸彈時檢查相鄰格子是否能夠被炸掉或變更為其他狀態。
     * </p>
     *
     * @param i 地圖陣列中該格子的索引位置（通常由 xyToI(x, y) 轉換取得）
     * @return 如果該格子可以被炸毀則回傳 {@code true}，否則回傳 {@code false}
     */
    public boolean isBombable(int i) {

        int t = mapArray[i];
        if (t == 1 || t == 3) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 判斷指定的地圖座標 (x, y) 是否可供角色行走。
     * <p>
     * 可行走的條件如下：
     * <ul>
     * <li>該地圖格子必須為可行走的類型（目前僅限於 tile 值為 1）</li>
     * <li>該座標上不得有佔據型物件，例如 monMode 為 2222 的物件（如炸彈）</li>
     * </ul>
     * </p>
     *
     * @param x 地圖上的 X 座標（以 tile 為單位）
     * @param y 地圖上的 Y 座標（以 tile 為單位）
     * @return 如果該格子可行走則回傳 {@code true}，否則回傳 {@code false}
     */
    public boolean isWalkable(int x, int y) {
        var walkable = true;
        int ii = xyToI(x, y);

        int t = mapArray[ii];
        if (t == 1) {
            walkable = true;
        } else {
            walkable = false;
        }

        if (!walkable)
            return false;

        for (int i = 0; i < BombKing.allObjs.size(); i++) {
            BombKingObj obj = BombKing.allObjs.get(i);
            // obj.update();
            // System.out.println(obj.showImage);
            if (obj.monMode == 2222 && obj.x == x && obj.y == y) {
                walkable = false;
            }
        }

        return walkable;
    }

    /**
     * 取得指定座標 (x, y) 對應的地圖貼圖編號。
     * <p>
     * 此方法會根據 tile 座標 (不是像素座標) 將其轉換為一維索引後，
     * 回傳該位置對應的地圖格值（用於識別地形類型，如道路、牆壁等）。
     * </p>
     *
     * @param x 地圖上的 X 座標（以 tile 為單位）
     * @param y 地圖上的 Y 座標（以 tile 為單位）
     * @return 該格子的 tile 類型整數（例如 1 代表道路，2 代表牆壁等）
     */
    public int getTexture(int x, int y) {
        return mapArray[xyToI(x, y)];
    }

    /**
     * 模擬在指定地圖位置 (x, y) 引爆炸彈的行為。
     * <p>
     * 此方法會檢查炸彈中心是否傷害到玩家，
     * 並嘗試對炸彈四個方向（上、下、左、右）一格內的區塊造成破壞，
     * 若該區塊為可破壞物件（如箱子），會被轉為可通行的道路。
     * </p>
     *
     * @param x 爆炸中心的 tile X 座標
     * @param y 爆炸中心的 tile Y 座標
     */
    public void bomb(int x, int y) {

        double distance1 = new Point(x, y).distance(BombKing.bombPlayer1.x, BombKing.bombPlayer1.y);
        if (distance1 <= 1) {
            BombKing.bombPlayer1.bloodCount--;
        }

        double distance2 = new Point(x, y).distance(BombKing.bombPlayer2.x, BombKing.bombPlayer2.y);
        if (distance2 <= 1) {
            BombKing.bombPlayer2.bloodCount--;
        }

        double distance3 = new Point(x, y).distance(BombKing.monster1.x, BombKing.monster1.y);
        if (distance3 <= 1) {
            BombKing.monster1.bloodCount--;
        }

        double distance4 = new Point(x, y).distance(BombKing.monster2.x, BombKing.monster2.y);
        if (distance4 <= 1) {
            BombKing.monster2.bloodCount--;
        }

        double distance5 = new Point(x, y).distance(BombKing.monster3.x, BombKing.monster3.y);
        if (distance5 <= 1) {
            BombKing.monster3.bloodCount--;
        }


        int i1 = xyToI(x - 1, y);
        int i2 = xyToI(x + 1, y);
        int i3 = xyToI(x, y - 1);
        int i4 = xyToI(x, y + 1);
        if (isBombable(i1)) {
            mapArray[i1] = 1;
        }
        if (isBombable(i2)) {
            mapArray[i2] = 1;
        }
        if (isBombable(i3)) {
            mapArray[i3] = 1;
        }
        if (isBombable(i4)) {
            mapArray[i4] = 1;
        }
    }

}