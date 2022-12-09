package ru.netology.graphics.image;

public class ColorSchema implements TextColorSchema {
    //  char [] symbol= { '#', '$', '@', '%', '*', '+', '-', '\''};
    char[] symbol = {'@', '$', 'm', 's', 'x', '*', '-', '\''};

    @Override
    public char convert(int color) {
        return symbol[color / 32];
    }
}
