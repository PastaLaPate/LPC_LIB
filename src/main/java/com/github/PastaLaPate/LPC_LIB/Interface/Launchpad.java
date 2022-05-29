package com.github.PastaLaPate.LPC_LIB.Interface;

import javax.sound.midi.InvalidMidiDataException;

public interface Launchpad {
    void setListener(LaunchpadListener listener);

    void setLight(int pad, int color) throws InvalidMidiDataException;

    void offLight(int pad) throws InvalidMidiDataException;
}
