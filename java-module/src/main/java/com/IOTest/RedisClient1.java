package com.IOTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author 陈宜康
 * @date 2019/12/18 20:13
 * @forWhat
 */
public class RedisClient1 implements RedisClient {
    public static void main(String[] args) {
        RedisClient redis = new RedisClient1("192.168.123.129", 6379);
        redis.set("123", "456");
        String value = redis.get("123");
        System.out.print(value);
    }

    private String ip;
    private int port;

    public RedisClient1(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public String get(String key) {
        Socket socket = null;
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            throw new RuntimeException("connect to " + ip + ":" + port + " failed");
        }

        try {
            //写数据
            OutputStream os = socket.getOutputStream();
            os.write(("get|" + key).getBytes());
            socket.shutdownOutput(); //不shutdown的话对端会等待read

            //读数据
            InputStream in = socket.getInputStream();
            byte[] buffer = new byte[512];
            int offset = 0;
            int bytesRead = in.read(buffer);
            while (bytesRead != -1) {
                offset += bytesRead;
                bytesRead = in.read(buffer, offset, 512 - offset);
            }

            String[] response = (new String(buffer, 0, offset)).split("\\|");
            if (response[0].equals("2")) {
                throw new RuntimeException("bad command");
            } else if (response[0].equals("1")) {
                return null;
            } else {
                return response[1];
            }

        } catch (IOException e) {
            throw new RuntimeException("network error");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void set(String key, String value) {
        Socket socket = null;
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            throw new RuntimeException("connect to " + ip + ":" + port + " failed");
        }
        try {
            OutputStream os = socket.getOutputStream();
            os.write(("set|" + key + "|" + value).getBytes());
            os.flush();
            socket.shutdownOutput();

            InputStream in = socket.getInputStream();
            byte[] buffer = new byte[512];
            int offset = 0;
            int bytesRead = in.read(buffer);
            while (bytesRead != -1) {
                offset += bytesRead;
                bytesRead = in.read(buffer, offset, 512 - offset);
            }
            String bufString = new String(buffer, 0, offset);

            String[] response = bufString.split("\\|");
            if (response[0].equals("2")) {
                throw new RuntimeException("bad command");
            }

        } catch (IOException e) {
            throw new RuntimeException("network error");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {

    }
}
