package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Converter implements TextGraphicsConverter {
    private int width;
    private int height;
    private double maxRatio;

    private TextColorSchema schema;

    public Converter() {
        schema = new ColorSchema();
    }

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));

        int imgWidth = img.getWidth();
        //   System.out.println(imgWidth);
        int imgHeight = img.getHeight();
        //  System.out.println(imgHeight);


        double ratio = (imgWidth >= imgHeight) ? (double) imgWidth / imgHeight : (double) imgHeight / imgWidth;
        if (maxRatio != 0 & ratio > maxRatio) {
            throw new BadImageSizeException(ratio, maxRatio);
        }

        double k = 0;
        if (width != 0 && height != 0) {
            k = Math.max((double) imgWidth / width, (double) imgHeight / height);
        }
        if (width != 0 && height == 0) {
            k = (double) imgWidth / width;
        }
        if (width == 0 && height != 0) {
            k = (double) imgHeight / height;
        }
        int newWidth = k > 1 ? (int) (imgWidth / k) : imgWidth;
//        System.out.println(newWidth);
        int newHeight = k > 1 ? (int) (imgHeight / k) : imgHeight;
//        System.out.println(newHeight);

        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        ImageIO.write(bwImg, "png", new File("out.png"));
        WritableRaster bwRaster = bwImg.getRaster();

        //     char[][] picture = new char[newHeight][newWidth];
        StringBuilder result = new StringBuilder();

        for (int h = 0; h < newHeight; h++) {
            for (int w = 0; w < newWidth; w++) {
                int color = bwRaster.getPixel(w, h, new int[3])[0];
                char c = schema.convert(color);
                //           picture[h][w] = c;
                result.append(c);
                result.append(c);
                // result.append(c);
            }
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public void setMaxWidth(int width) {
        this.width = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.height = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }

}
