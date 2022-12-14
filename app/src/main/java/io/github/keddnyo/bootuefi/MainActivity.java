package io.github.keddnyo.bootuefi;

import android.app.Activity;
import android.os.Bundle;
import java.io.IOException;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Runtime runtime = Runtime.getRuntime();
        String[] firstCommand = new String[]{
                "su",
                "-c",
                "mount -t efivars efivars"
        };
        String[] secondCommand = new String[]{
                "su",
                "-c",
                "printf \"\\x07\\x00\\x00\\x00\\x01\\x00\\x00\\x00\\x00\\x00\\x00\\x00\" > /sys/firmware/efi/efivars/OsIndications-8be4df61-93ca-11d2-aa0d-00e098032b8c"
        };
        try {
            runtime.exec(firstCommand);
            runtime.exec(secondCommand).waitFor();
            runtime.exec("reboot");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}