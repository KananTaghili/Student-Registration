package com.example.myapplication.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class FileSave {
    public static void saveFile() {
        write(Data.d, "file.txt");
    }

    public static Data getData() {
        return (Data) read("file.txt");
    }

    public static Object read(String filename) {
        try {
            File file = new File("/storage/emulated/0/MyApp/" + filename);
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream input = new ObjectInputStream(fis);
            Object data = (Object) input.readObject();
            input.close();
            return data;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void write(Serializable object, String filename) {
        try {
            File root = new File("/storage/emulated/0/MyApp/");
            if (!root.exists()) {
                root.mkdirs();
            }
            File file = new File(root, filename);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutput out = new ObjectOutputStream(fos);
            out.writeObject(object);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
