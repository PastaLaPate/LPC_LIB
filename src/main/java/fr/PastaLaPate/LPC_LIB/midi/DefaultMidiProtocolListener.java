package fr.PastaLaPate.LPC_LIB.midi;

import fr.PastaLaPate.LPC_LIB.Interface.LaunchpadListener;
import fr.PastaLaPate.LPC_LIB.Interface.MidiProtocolListener;

public class DefaultMidiProtocolListener implements MidiProtocolListener {

    private final LaunchpadListener launchpadListener;

    public DefaultMidiProtocolListener(LaunchpadListener launchpadListener) {
        this.launchpadListener = launchpadListener;
    }

    @Override
    public void onNoteOn(int note, long timestamp) {
        int x = note % 16;
        int y = note / 16;
        launchpadListener.PadPressed(x, y);
    }

    @Override
    public void onNoteOff(int note, long timestamp) {
        int x = note % 16;
        int y = note / 16;
        launchpadListener.PadReleased(x, y);
    }
}
