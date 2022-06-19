
package com.github.PastaLaPate.LPC_LIB.util;

import com.github.PastaLaPate.LPC_LIB.Launchpad;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.sound.midi.InvalidMidiDataException;
import java.util.ArrayList;
import java.util.Random;

public class Pad {

    // The note of pad
    private final int note;
    // The color of pad
    private int color = 0;

    // All the pads
    private static ArrayList<Pad> pads = new ArrayList<>();

    // Create all pads
    static {
        for (int i = 36; i < 100; i++) {
            pads.add(new Pad(i));
        }
    }

    // Deprecated use getPad() to change color and light
    @Deprecated
    public static @NotNull ArrayList<Pad> getPads() {
        return pads;
    }
    /*
     * @desc : Get a pad
     * @param note : The note of pad
     * */

    public static @Nullable Pad getPad(@NotNull int note) {
        for (Pad pad : pads) {
            if (pad.getNote() == note) {
                return pad;
            }
        }
        return null;
    }

    private Pad(@NotNull int note) {
        this.note = note;
    }

    public @NotNull int getColor() {
        return color;
    }

    public void setColor(@NotNull int newColor) throws InvalidMidiDataException {
        color = newColor;
        Launchpad.getINSTANCE().setLight(note, newColor);
    }

    public void offLight() throws InvalidMidiDataException {
        Launchpad.getINSTANCE().offLight(note);
    }

    public @NotNull int getNote() {
        return note;
    }
}