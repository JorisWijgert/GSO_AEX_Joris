/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner;

import static java.lang.Math.round;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author jvdwi
 */
public class BannerController extends TimerTask {

    private AEXBanner banner;
    private IEffectenBeurs effectenbeurs;
    private Timer pollingTimer;

    public BannerController(AEXBanner banner) {

        this.banner = banner;
        this.effectenbeurs = new MockEffectenbeurs();

        // Start polling timer: update banner every two seconds
        pollingTimer = new Timer();
        pollingTimer.scheduleAtFixedRate(this, 0, 2000);
        // TODO
    }

    // Stop banner controller
    public void stop() {
        pollingTimer.cancel();
        // Stop simulation timer of effectenbeurs
        // TODO
    }

    @Override
    public void run() {
        List<IFonds> fondsen = effectenbeurs.getKoersen();
        String text = "";
        for(IFonds fonds : fondsen){
            text = text + "   " + fonds.getNaam();
            DecimalFormat df = new DecimalFormat("#.##");
            text = text + ": " + df.format(fonds.getKoers());
        }
        banner.setKoersen(text);
    }
}
