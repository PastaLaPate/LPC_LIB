package com.github.PastaLaPate.LPC_LIB.test;

import com.github.PastaLaPate.LPC_LIB.Interface.LaunchpadListener;
import com.github.PastaLaPate.LPC_LIB.Launchpad;
import com.github.PastaLaPate.LPC_LIB.Exceptions.BuilderFailedException;
import com.github.PastaLaPate.LPC_LIB.util.Pad;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;

public class Main {
    public static void main(String[] args) throws MidiUnavailableException, BuilderFailedException, InvalidMidiDataException {
        Launchpad.LaunchpadBuilder launchpadBuilder = new Launchpad.LaunchpadBuilder();

        MidiDevice inputDevice = Launchpad.LaunchpadBuilder.autodetect("MIDIIN2 (LPX MIDI)");
        MidiDevice outputDevice = Launchpad.LaunchpadBuilder.autodetect("MIDIOUT2 (LPX MIDI)");

        Launchpad lp = launchpadBuilder
                .withInputMidiDevice(inputDevice)
                .withOutputMidiDevice(outputDevice)
                .build();

        lp.setListener(new LaunchpadListener() {
            @Override
            public void PadPressed(Pad pad) {
                System.out.println(pad.getNote());
            }

            @Override
            public void PadReleased(Pad pad) {

            }
        });

    }
}
