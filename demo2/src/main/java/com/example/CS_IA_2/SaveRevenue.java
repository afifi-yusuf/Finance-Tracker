package com.example.CS_IA_2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveRevenue {
    static String filename = "Revenues.bin";
    static FileOutputStream fos;

    static {
        try {
            fos = new FileOutputStream(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    ObjectOutputStream oos = new ObjectOutputStream(fos);

    public SaveRevenue() throws IOException {
    }

    public static void main(String[] args) throws IOException {

    }
}
