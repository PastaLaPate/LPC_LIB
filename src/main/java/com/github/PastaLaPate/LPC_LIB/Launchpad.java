package com.github.PastaLaPate.LPC_LIB;

import com.github.PastaLaPate.LPC_LIB.midi.DefaultMidiProtocolReceiver;
import com.github.PastaLaPate.LPC_LIB.Exceptions.BuilderFailedException;
import com.github.PastaLaPate.LPC_LIB.Interface.LaunchpadListener;
import com.github.PastaLaPate.LPC_LIB.Interface.MidiProtocolListener;
import com.github.PastaLaPate.LPC_LIB.midi.DefaultMidiProtocolListener;

import javax.sound.midi.*;
import java.util.Objects;

public class Launchpad  implements com.github.PastaLaPate.LPC_LIB.Interface.Launchpad {

    private static Launchpad INSTANCE;

    /** The Launchpad's input channel (Device -> LPC_LIB). */
    private final Receiver receiver;
    /** The Launchpad's output channel (LPC_LIB -> Device). */
    private final Transmitter transmitter;
    private Launchpad(LaunchpadBuilder lpB) throws MidiUnavailableException {
        MidiDevice inputMidiDevice = lpB.inputMidiDevice;
        MidiDevice outputMidiDevice = lpB.outputMidiDevice;

        if (!outputMidiDevice.isOpen()) {
            outputMidiDevice.open();
        }

        this.receiver = outputMidiDevice.getReceiver();

        if (!inputMidiDevice.isOpen()) {
            inputMidiDevice.open();
        }

        this.transmitter = inputMidiDevice.getTransmitter();

        INSTANCE = this;
    }

    @Override
    public void setListener(LaunchpadListener launchpadListener) {
        MidiProtocolListener defaultMidiProtocolListener = new DefaultMidiProtocolListener(launchpadListener);
        Receiver midiReceiver = new DefaultMidiProtocolReceiver(defaultMidiProtocolListener);
        transmitter.setReceiver(midiReceiver);

        INSTANCE = this;
    }

    @Override
    public void setLight(int pad, int color) throws InvalidMidiDataException {
            ShortMessage message = new ShortMessage();
            message.setMessage(ShortMessage.NOTE_ON, pad, color);
            send(message);
    }

    @Override
    public void offLight(int pad) throws InvalidMidiDataException {
        ShortMessage message = new ShortMessage();
        message.setMessage(ShortMessage.NOTE_OFF, pad, 0);
        send(message);
    }

    private void send(MidiMessage message) {
        receiver.send(message, -1);
    }

    public static Launchpad getINSTANCE() {
        return INSTANCE;
    }

    public static class LaunchpadBuilder {

        private MidiDevice inputMidiDevice;
        private MidiDevice outputMidiDevice;

        public LaunchpadBuilder withInputMidiDevice(MidiDevice inputMidiDevice) {
            this.inputMidiDevice = inputMidiDevice;
            return this;
        }

        public LaunchpadBuilder withOutputMidiDevice(MidiDevice outputMidiDevice) {
            this.outputMidiDevice = outputMidiDevice;
            return this;
        }

        public Launchpad build() throws BuilderFailedException, MidiUnavailableException {
            if (inputMidiDevice == null && outputMidiDevice == null) {
                throw new BuilderFailedException("InputDevice or OutputDevice cannot be null");
            }
            return new Launchpad(this);
        }

        public static MidiDevice autodetect(String device_Name) throws MidiUnavailableException {
            MidiDevice.Info[] devices = MidiSystem.getMidiDeviceInfo();

            for (MidiDevice.Info device: devices) {
                if (Objects.equals(device.getName(), device_Name) || device.getName().contains(device_Name)) {
                    return MidiSystem.getMidiDevice(device);
                }
            }

            return null;
        }

    }

}
