package com.example.documents_rewrite.mainApplication.logic;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    Runnable runnable = () -> {
        try (
                Socket client = new Socket("127.0.0.1", 8000);
                BufferedWriter writer =
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        client.getOutputStream()));
                BufferedOutputStream fileOut = new BufferedOutputStream(client.getOutputStream())
        )
        {
            Loader loader = new Loader(fileOut, writer, "pathName", "fileName");
            loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
    };
}
