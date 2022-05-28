package com.github.PastaLaPate.LPC_LIB.Interface;

public interface MidiProtocolListener {

    /**
     * Called when a note starts being emitted.
     *
     * @param note The note emitted.
     * @param timestamp When the event occurred.
     */
    public void onNoteOn(int note, long timestamp);

    /**
     * Called when a note is not emitted anymore.
     *
     * @param note The note silenced.
     * @param timestamp When the event occurred.
     */
    public void onNoteOff(int note, long timestamp);
}