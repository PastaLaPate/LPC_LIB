package com.github.PastaLaPate.LPC_LIB.util;

import com.github.PastaLaPate.LPC_LIB.Launchpad;

import javax.sound.midi.InvalidMidiDataException;
import java.util.ArrayList;
import java.util.Random;

public class Pad {

    private final int note;
    private int color;

    private static ArrayList<Pad> pads = new ArrayList<>();

    static {
        for (int i = 36; i < 100; i++) {
            pads.add(new Pad(i));
        }
    }

    public static ArrayList<Pad> getPads() {
        return pads;
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
