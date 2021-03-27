package ru.spacecorp.getmanenko.logservice.util;

public enum ConsoleTextColor {

    /**
     * BLACK to black colored text for console
     */
    BLACK(CustomColor.ANSI_BLACK),

    /**
     * RED to red colored text for console
     */
    RED(CustomColor.ANSI_RED),

    /**
     * GREEN to green colored text for console
     */
    GREEN(CustomColor.ANSI_GREEN),

    /**
     * YELLOW to yellow colored text for console
     */
    YELLOW(CustomColor.ANSI_YELLOW),

    /**
     * BLUE to blue colored text for console
     */
    BLUE(CustomColor.ANSI_BLUE),

    /**
     * PURPLE to purple colored text for console
     */
    PURPLE(CustomColor.ANSI_PURPLE),

    /**
     * CYAN to cyan colored text for console
     */
    CYAN(CustomColor.ANSI_CYAN),

    /**
     * WHITE to white colored text for console
     */
    WHITE(CustomColor.ANSI_WHITE),

    /**
     * RESET to default colored text for console
     */
    RESET(CustomColor.ANSI_RESET);


    private final String value;

    ConsoleTextColor(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

}
