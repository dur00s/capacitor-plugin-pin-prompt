package com.artisantechgroup.plugins.capacitor_plugin_pin_prompt;

import android.app.Activity;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.ActivityCallback;

import androidx.activity.result.ActivityResult;

import android.app.KeyguardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

@CapacitorPlugin(name = "PinPrompt")
public class PinPromptPlugin extends Plugin {

    @PluginMethod
    public void prompt(PluginCall call) {
        getActivity().runOnUiThread(() -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), PinPromptActivity.class);
            startActivityForResult(call, intent, "returnBiometricResults");
        });
    }

    @PluginMethod
    public void isAvailable(PluginCall call) {
        getActivity().runOnUiThread(() -> {
            Context ctx = getContext();
            boolean isAvailable = doesDeviceHaveSecuritySetup(ctx);
            JSObject ret = new JSObject();
            ret.put("isAvailable", isAvailable);
            call.resolve(ret);
        });
    }

    @ActivityCallback
    public void returnBiometricResults(PluginCall call, ActivityResult result) {
        if (result.getResultCode() == Activity.RESULT_CANCELED || call == null) {
            call.reject(result.getData().getStringExtra("message"));
        } else {
            call.resolve();
        }
    }

    public static boolean doesDeviceHaveSecuritySetup(Context context) {
        return isPatternSet(context) || isPassOrPinSet(context);
    }

    /**
     * @param context
     * @return true if pattern set, false if not (or if an issue when checking)
     */
    private static boolean isPatternSet(Context context) {
        ContentResolver cr = context.getContentResolver();
        try {
            int lockPatternEnable = Settings.Secure.getInt(cr, Settings.Secure.LOCK_PATTERN_ENABLED);
            return lockPatternEnable == 1;
        } catch (Settings.SettingNotFoundException e) {
            return false;
        }
    }

    /**
     * @param context
     * @return true if pass or pin set
     */
    private static boolean isPassOrPinSet(Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE); //api 16+
        return keyguardManager.isKeyguardSecure();
    }
}
