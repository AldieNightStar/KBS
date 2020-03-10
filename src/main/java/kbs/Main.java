package kbs;

import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        // Present program itself
        System.out.println("KBS by HaxiDenti");

        // Read config
        String json = new String(Files.readAllBytes(Paths.get("./config.json")), StandardCharsets.UTF_8);
        Config config = new Gson().fromJson(json, Config.class);

        // Create KBS object
        KBS kbs = new KBS(config);

        // Audio effects
        Sound click = new Sound(config, false);
        Sound tap = new Sound(config, true);
        kbs.setClickAction(click::play);
        kbs.setTapAction(tap::play);

        // Start
        kbs.serve();
        System.out.println("It's now working! Do not close this window until you done! :)");

        // Just to keep app in life :)
        while (true) {
            Thread.sleep(1000);
        }
    }
}
