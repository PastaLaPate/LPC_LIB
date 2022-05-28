package fr.PastaLaPate.LPC_LIB.midi;

import fr.PastaLaPate.LPC_LIB.Exceptions.MidiEventException;
import fr.PastaLaPate.LPC_LIB.Interface.MidiProtocolListener;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

public class DefaultMidiProtocolReceiver implements Receiver {

    private final MidiProtocolListener listener;

    public DefaultMidiProtocolReceiver(MidiProtocolListener listener) {
        this.listener = listener;
    }

    @Override
    public void send(MidiMessage message, long timeStamp) {
        if (message instanceof ShortMessage) {
            handleShortMessage((ShortMessage) message, timeStamp);
        } else {
            throw new MidiEventException("Unknown event : " + message);
        }
    }

    protected void handleShortMessage(ShortMessage message, long timestamp) {
        int status = message.getStatus();
        int note = message.getData1();
        int velocity = message.getData2();

        if (status == ShortMessage.NOTE_ON) {
            handleNoteOnMessage(note, velocity, timestamp);
        } else {
            throw new MidiEventException("Unknown event : " + status);
        }
    }

    protected void handleNoteOnMessage(int note, int velocity, long timestamp) {
        if (velocity == 0) {
            listener.onNoteOff(note, timestamp);
        } else {
            listener.onNoteOn(note, timestamp);
        }
    }

    @Override
    public void close() {

    }
}
