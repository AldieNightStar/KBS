package kbs;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyAdapter;
import org.jnativehook.keyboard.NativeKeyEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.LogManager;

public class KBS {
    private Config config;
    private Runnable clickAction;
    private Runnable tapAction;

    public KBS(Config config) {
        this.config = config;
    }

    public void setClickAction(Runnable r) {
        this.clickAction = r;
    }

    public void setTapAction(Runnable r) {
        this.tapAction = r;
    }

    public void serve() {
        if (config.tapsPerTick > 0) {
            Thread t = new Thread(() -> {
                while (!Thread.interrupted()) {
                    try {
                        Thread.sleep(4000 / config.tapsPerTick);
                        if (tapAction != null) tapAction.run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
        try {
            // Removing logging
            LogManager.getLogManager().reset();

            // Register Listener
            GlobalScreen.addNativeKeyListener(new NativeKeyAdapter() {
                private Map<Integer, Boolean> pressed = new HashMap<>();

                @Override
                public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
                    int code = nativeKeyEvent.getKeyCode();
                    if (pressed.getOrDefault(code, false)) return;

                    pressed.put(code, true);
                    if (clickAction != null) {
                        clickAction.run();
                    }
                }

                @Override
                public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
                    int code = nativeKeyEvent.getKeyCode();
                    pressed.put(code, false);
                }
            });

            GlobalScreen.registerNativeHook();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
