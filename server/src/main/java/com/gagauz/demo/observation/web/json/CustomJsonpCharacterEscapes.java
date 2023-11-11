package com.gagauz.demo.observation.web.json;

import com.fasterxml.jackson.core.JsonpCharacterEscapes;
import com.fasterxml.jackson.core.io.CharacterEscapes;

@SuppressWarnings("serial")
public class CustomJsonpCharacterEscapes extends JsonpCharacterEscapes {

    @Override
    public int[] getEscapeCodesForAscii() {
        var escapeCodes = super.getEscapeCodesForAscii();
        escapeCodes[10] = CharacterEscapes.ESCAPE_NONE;
        escapeCodes[13] = CharacterEscapes.ESCAPE_NONE;
        return escapeCodes;
    }
}
