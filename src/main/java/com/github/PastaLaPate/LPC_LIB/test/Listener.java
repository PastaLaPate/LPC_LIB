package com.github.PastaLaPate.LPC_LIB.test;

import com.github.PastaLaPate.LPC_LIB.Interface.Launchpad;
import com.github.PastaLaPate.LPC_LIB.Interface.LaunchpadListener;
import com.github.PastaLaPate.LPC_LIB.util.Pad;

import javax.sound.midi.InvalidMidiDataException;
import java.util.ArrayList;
import java.util.List;

public class Listener implements LaunchpadListener {

    private final Launchpad launchpad;
    private ArrayList<Integer> list = new ArrayList<>();

    public Listener(Launchpad lp) {
        this.launchpad = lp;
    }

    @Override
    public void PadPressed(Pad pad) {
        try {
            if (!list.contains(pad.getNote())) {
                pad.setColor(3);
                list.add(pad.getNote());
            } else {
                pad.offLight();
                list.remove(Integer.valueOf(pad.getNote()));
            }
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }
        System.out.println("X : " + pad.getNote());
    }

    @Override
    public void PadReleased(Pad pad) {

    }
}
