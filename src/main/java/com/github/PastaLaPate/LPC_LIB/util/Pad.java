package com.github.PastaLaPate.LPC_LIB.util;

import com.github.PastaLaPate.LPC_LIB.Launchpad;

import javax.sound.midi.InvalidMidiDataException;
import java.util.ArrayList;
import java.util.Random;

public class Pad {

    // The note of pad
    private final int note;
    // The color of pad
    private int color;

    // All the pads
    private static ArrayList<Pad> pads = new ArrayList<>();

    // Create all pads
    static {
        for (int i = 36; i < 100; i++) {
            pads.add(new Pad(i));
        }
    }

    // Get pads
    public static ArrayList<Pad> getPads() {
        return pads;
    }
    /*
    * @desc : Get a pad
    * @param note : The note of pad
    * */

    public static Pad getPad(int note) {
        for (Pad pad : pads) {
            if (pad.getNote() == note) {
                return pad;
            }
        }
        return null;
    }

    private Pad(int note) {
        this.note = note;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int newColor) throws InvalidMidiDataException {
        color = newColor;
        Launchpad.getINSTANCE().setLight(note, newColor);
    }

    public void offLight() throws InvalidMidiDataException {
        Launchpad.getINSTANCE().offLight(note);
    }

    public int getNote() {
        return note;
    }
}
