package ru.netology.graphics;

import ru.netology.graphics.image.Converter;
import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.server.GServer;

public class Main {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new Converter();
        // converter.setMaxRatio(1);
        // converter.setMaxHeight(7);
        //  converter.setMaxWidth(25);

        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем

       /* // Или то же, но с выводом на экран:
        String url = "https://raw.githubusercontent.com/netology-code/java-diplom/main/pics/simple-test.png";
        //  String url = "https://i.ibb.co/6DYM05G/edu0.jpg";
        String imgTxt = converter.convert(url);
        System.out.println(imgTxt);*/
    }
}
