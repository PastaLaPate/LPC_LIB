package com.github.PastaLaPate.LPC_LIB.test;

import com.github.PastaLaPate.LPC_LIB.Interface.LaunchpadListener;
import com.github.PastaLaPate.LPC_LIB.Launchpad;
import com.github.PastaLaPate.LPC_LIB.Exceptions.BuilderFailedException;
import com.github.PastaLaPate.LPC_LIB.util.Pad;

import javax.sound.midi.*;
import javax.sound.midi.spi.SoundbankReader;

public class Main {
    public static void main(String[] args) throws MidiUnavailableException, BuilderFailedException {
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
                System.out.println("x : " + pad.getNote() / 16 / 2);
                System.out.println("y : " + pad.getNote() % 16 / 4);
            }

            @Override
            public void PadReleased(Pad pad) {

            }
        });

    }
}
