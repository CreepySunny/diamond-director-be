package nl.fontys.s3.indi.diamond_director_be.Domain.GameState.Enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum PlayResult {
    // Hit types
    SINGLE("1B"),
    DOUBLE("2B"),
    TRIPLE("3B"),
    HOME_RUN("HR"),

    // Out types
    STRIKEOUT("K"),
    SACRIFICE_FLY("SF"),
    ERROR("E"),
    GROUNDOUT("GO"),
    FLYOUT("FO"),
    LINEOUT("LO"),
    POP_OUT("PO"),
    FIELDERS_CHOICE("FC"),

    // Walk types
    WALK("BB"),
    NON_INTENTIONAL_WALK("NIBB"),
    INTENTIONAL_WALK("IBB"),

    // Other types
    HIT_BY_PITCH("HBP");

    private static final Map<String, PlayResult> SHORTHAND_MAP = new HashMap<>();

    static {
        for (PlayResult playResult : PlayResult.values()) {
            SHORTHAND_MAP.put(playResult.getShorthand(), playResult);
        }
    }

    private final String shorthand;

    PlayResult(String shorthand) {
        this.shorthand = shorthand;
    }

    public static PlayResult fromShorthand(String shorthand) {
        return SHORTHAND_MAP.get(shorthand);
    }
}
