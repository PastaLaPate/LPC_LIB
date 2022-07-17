package com.github.PastaLaPate.LPC_LIB.midi;

import com.github.PastaLaPate.LPC_LIB.Interface.LaunchpadListener;
import com.github.PastaLaPate.LPC_LIB.Interface.MidiProtocolListener;
import com.github.PastaLaPate.LPC_LIB.util.Pad;

public class DefaultMidiProtocolListener implements MidiProtocolListener {

    private final LaunchpadListener launchpadListener;

    public DefaultMidiProtocolListener(LaunchpadListener launchpadListener) {
        this.launchpadListener = launchpadListener;
    }

    @Override
    public void onNoteOn(int note, long timestamp) {
        Pad pad = Pad.getPad(note);
        launchpadListener.PadPressed(pad);
    }

    @Override
    public void onNoteOff(int note, long timestamp) {
        Pad pad = Pad.getPad(note);
        launchpadListener.PadReleased(pad);
    }
}
