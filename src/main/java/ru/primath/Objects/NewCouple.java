package ru.primath.Objects;

public class NewCouple {
    private String title;
    private String aud;

    public byte id;

    public NewCouple(String title, String aud) {
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
