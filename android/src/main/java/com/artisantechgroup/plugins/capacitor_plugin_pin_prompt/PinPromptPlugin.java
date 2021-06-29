package com.artisantechgroup.plugins.capacitor_plugin_pin_prompt;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.Executor;
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
