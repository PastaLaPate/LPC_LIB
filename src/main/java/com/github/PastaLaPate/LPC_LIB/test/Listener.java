package com.github.PastaLaPate.LPC_LIB.test;

import com.github.PastaLaPate.LPC_LIB.Interface.LaunchpadListener;

public class Listener implements LaunchpadListener {
    @Override
    public void PadPressed(int x, int y) {
        System.out.println("X : " + x + " Y : " + y);
    }

    @Override
    public void PadReleased(int x, int y) {

    }
}
