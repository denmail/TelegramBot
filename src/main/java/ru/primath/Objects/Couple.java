package ru.primath.Objects;

public class Couple {
    private final String title;
    private final String aud;

    public byte id;

    public Couple(String title, String aud) {
        this.title = title;
        this.aud = aud;
    }


    public String getTitle() {
        return title;
    }

    public String getAud() {
        return aud;
    }
}
