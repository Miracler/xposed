package com.zx.xposed;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import android.graphics.Color;
import android.widget.TextView;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodHook.MethodHookParam;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class XposedTutorial implements IXposedHookLoadPackage {

	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		// TODO Auto-generated method stub
		if (!lpparam.packageName.equals("com.android.systemui")) {
			return;
		}
		XposedBridge.log("We are in SystemUI");
		findAndHookMethod("com.android.systemui.statusbar.policy.Clock", lpparam.classLoader, "updateClock", new XC_MethodHook() {

					@Override
					protected void afterHookedMethod(MethodHookParam param)
							throws Throwable {
						// TODO Auto-generated method stub
						TextView tv = (TextView) param.thisObject;
						String text = tv.getText().toString();
						XposedBridge.log("current time: " + text);
						tv.setText(text + ":)");
						tv.setTextColor(Color.RED);
					}

				});
	}

}
