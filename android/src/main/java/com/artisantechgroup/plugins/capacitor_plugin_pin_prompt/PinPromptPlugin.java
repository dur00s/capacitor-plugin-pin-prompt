package com.artisantechgroup.plugins.capacitor_plugin_pin_prompt;

import android.app.Activity;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.ActivityCallback;
import androidx.activity.result.ActivityResult;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

@CapacitorPlugin(name = "PinPrompt")
public class PinPromptPlugin extends Plugin {

    @PluginMethod
    public void prompt(PluginCall call) {
        getActivity().runOnUiThread(() -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), PinPromptActivity.class);
            startActivityForResult(call, intent, "returnBiometricResults");
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
}
