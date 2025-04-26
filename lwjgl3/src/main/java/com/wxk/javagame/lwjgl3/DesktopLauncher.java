package com.wxk.javagame.lwjgl3;



import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
    public static void main (String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("SkyWizard");  // 設定視窗標題
        config.setWindowedMode(600, 800); // 設定視窗大小
        config.useVsync(true); // 開啟垂直同步
        config.setForegroundFPS(60); // 設定 FPS 上限
        new Lwjgl3Application(new SkyWizard(), config); // 啟動 MainSpace
    }
}