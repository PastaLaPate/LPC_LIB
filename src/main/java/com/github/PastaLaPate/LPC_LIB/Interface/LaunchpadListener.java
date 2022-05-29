package com.github.PastaLaPate.LPC_LIB.Interface;


import com.github.PastaLaPate.LPC_LIB.util.Pad;

public interface LaunchpadListener {
    void PadPressed(Pad pad);

    void PadReleased(Pad pad);
}
