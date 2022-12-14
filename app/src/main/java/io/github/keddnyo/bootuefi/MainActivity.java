package io.github.keddnyo.bootuefi;

import android.app.Activity;

public class MainActivity extends Activity {

    @Override
    protected void onResume() {
        super.onResume();

        Runtime runtime = Runtime.getRuntime();

        String[] mount = command("mount -t efivars efivars");
        String[] uefi = command("printf \"\\x07\\x00\\x00\\x00\\x01\\x00\\x00\\x00\\x00\\x00\\x00\\x00\" > /sys/firmware/efi/efivars/OsIndications-8be4df61-93ca-11d2-aa0d-00e098032b8c");
        String[] reboot = command("reboot");

        try {
            runtime.exec(mount).waitFor();
            runtime.exec(uefi).waitFor();
            runtime.exec(reboot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String[] command(String command) {
        return new String[]{
                "su",
                "-c",
                command
        };
    }

}