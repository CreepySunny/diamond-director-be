package nl.fontys.s3.indi.diamond_director_be.domain.Game;

import lombok.Getter;
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

    // Walk types
    NON_INTENTIONAL_WALK("NIBB"),
    INTENTIONAL_WALK("IBB"),

    // Other types
    HIT_BY_PITCH("HBP");

    private final String shorthand;

    PlayResult(String shorthand) {
        this.shorthand = shorthand;
    }
}
