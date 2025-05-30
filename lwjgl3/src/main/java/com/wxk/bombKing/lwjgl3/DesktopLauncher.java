package com.wxk.bombKing.lwjgl3;

import java.io.File;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
    /**
     * DesktopLauncher.java
     * 
     * 遊戲啟動入口 (Desktop 版本)，使用 LibGDX 與 LWJGL3。
     *
     * 作者:B11007104王星凱
     * 貢獻者:B11207142許育銘
     * 日期: 2025-05-23
     */
    public static void main(String[] arg) {

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(1280, 720); // 設定視窗大小
        config.useVsync(true); // 開啟垂直同步
        config.setForegroundFPS(60); // 設定 FPS 上限
        config.setTitle("Bomb King"); // 設定視窗標題
        new Lwjgl3Application(new BombKing(), config); // 啟動 MainSpace

    }
}