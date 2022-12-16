package io.github.keddnyo.bootuefi;

import android.app.Activity;

public class MainActivity extends Activity {
    @Override
    protected void onStart() {
        super.onStart();
        try {
            Runtime.getRuntime().exec("su -c printf \"\\x07\\x00\\x00\\x00\\x01\\x00\\x00\\x00\\x00\\x00\\x00\\x00\" > /sys/firmware/efi/efivars/OsIndications-8be4df61-93ca-11d2-aa0d-00e098032b8c && reboot");
        } catch (Exception ignored) {
        }
    }
}