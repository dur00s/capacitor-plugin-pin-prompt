package com.artisantechgroup.plugins.capacitor_plugin_pin_prompt;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import static androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

@CapacitorPlugin(name = "PinPrompt")
public class PinPromptPlugin extends Plugin {

    @PluginMethod
    public void prompt(PluginCall call) {
        getActivity().runOnUiThread(() -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), PinPromptActivity.class);
            startActivityForResult(call, intent, 1);
        });
    }
}
