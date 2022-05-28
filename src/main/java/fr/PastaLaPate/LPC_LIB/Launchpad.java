package fr.PastaLaPate.LPC_LIB;

import fr.PastaLaPate.LPC_LIB.Exceptions.BuilderFailedException;
import fr.PastaLaPate.LPC_LIB.Interface.LaunchpadListener;
import fr.PastaLaPate.LPC_LIB.Interface.MidiProtocolListener;
import fr.PastaLaPate.LPC_LIB.midi.DefaultMidiProtocolListener;
import fr.PastaLaPate.LPC_LIB.midi.DefaultMidiProtocolReceiver;

import javax.sound.midi.*;

public class Launchpad {

    private MidiDevice inputMidiDevice;
    private MidiDevice outputMidiDevice;

    /** The Launchpad's input channel (Device -> LPC_LIB). */
    private final Receiver receiver;
    /** The Launchpad's output channel (LPC_LIB -> Device). */
    private final Transmitter transmitter;
    private Launchpad(LaunchpadBuilder lpB) throws MidiUnavailableException {
        this.inputMidiDevice = lpB.inputMidiDevice;
        this.outputMidiDevice = lpB.outputMidiDevice;

        if (!outputMidiDevice.isOpen()) {
            outputMidiDevice.open();
        }

        this.receiver = outputMidiDevice.getReceiver();

        if (!inputMidiDevice.isOpen()) {
            inputMidiDevice.open();
        }

        this.transmitter = inputMidiDevice.getTransmitter();
    }

    public void setListener(LaunchpadListener launchpadListener) {
        MidiProtocolListener defaultMidiProtocolListener = new DefaultMidiProtocolListener(launchpadListener);
        Receiver midiReceiver = new DefaultMidiProtocolReceiver(defaultMidiProtocolListener);
        transmitter.setReceiver(midiReceiver);
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
                if (device.getName() == device_Name || device.getName().contains(device_Name)) {
                    return MidiSystem.getMidiDevice(device);
                }
            }

            return null;
        }

    }

}
