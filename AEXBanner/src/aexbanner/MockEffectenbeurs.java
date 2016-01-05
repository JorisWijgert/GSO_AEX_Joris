/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner;

import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jvdwi
 */
public class MockEffectenbeurs implements IEffectenBeurs {

    List<IFonds> fondsen;

    public MockEffectenbeurs() {
        fondsen = new ArrayList<>();
        fondsen.add(new Fonds("Philips", random() * 100));
        fondsen.add(new Fonds("LG", random() * 100));
        fondsen.add(new Fonds("TomTom", random() * 100));
        fondsen.add(new Fonds("HEMA", random() * 100));
        fondsen.add(new Fonds("Ahold", random() * 100));
        fondsen.add(new Fonds("Sennheiser", random() * 100));
        fondsen.add(new Fonds("Apple", random()));
        fondsen.add(new Fonds("Microsoft", random() * 100));
    }
    
    public void setKoersen(){
        for(IFonds f : fondsen){
            Fonds fonds = (Fonds) f;
            fonds.setKoers(random() * 100);
        }
    }

    @Override
    public List<IFonds> getKoersen() {

        return fondsen;
    }

}
