package ru.job4j.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(135)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {

                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                            if(str.contains("GET /?msg=Hello HTTP/1.1")) {
                                out.write("Hello, dear friend.".getBytes());
                                System.out.println("Hello, dear friend");
                                break;
                            } if (str.contains("GET /?msg=Exit HTTP/1.1")) {
                                server.close();
                                out.write("Close".getBytes());
                                System.out.println("Close");
                                break;

                            }
                            if (str.matches("[GET /?msg=].+[HTTP/1.1]")) {
                                int start = str.indexOf("=") + 1;
                                int end = str.indexOf("HTTP");
                                out.write(str.substring(start, end).getBytes());
                                System.out.println(str.substring(start, end));
                                break;
                            }



                    }

                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Exception in log example", e);
        }
    }
}