package com;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Socketa {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("10.140.33.25", 9000);
        OutputStream out = socket.getOutputStream();
        PrintWriter output = new PrintWriter(out);

        output.println("Hello world");
        output.flush();

        socket.close();
    }
}
