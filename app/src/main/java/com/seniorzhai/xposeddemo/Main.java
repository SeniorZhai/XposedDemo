package com.seniorzhai.xposeddemo;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by zhai on 16/2/19.
 */
public class Main implements IXposedHookLoadPackage {
    // 包加载的时候回调
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (!loadPackageParam.packageName.equals("com.seniorzhai.myapplication")) {
            return;
        }
        XposedBridge.log("Loaded app:" + "com.seniorzhai.myapplication");
        XposedHelpers.findAndHookMethod("com.seniorzhai.myapplication.MainActivity", loadPackageParam.classLoader, "isCorrectInfo", String.class, String.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("开始劫持了~");
                XposedBridge.log("参数1 = " + param.args[0]);
                XposedBridge.log("参数2 = " + param.args[1]);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("劫持结束了~");
                XposedBridge.log("参数1 = " + param.args[0]);
                XposedBridge.log("参数2 = " + param.args[1]);
            }
        });
    }
}
