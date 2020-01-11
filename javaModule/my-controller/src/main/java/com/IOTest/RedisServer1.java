package com.IOTest;

/**
 * @author 陈宜康
 * @date 2019/12/18 20:10
 * @forWhat
 */


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RedisServer1 {

    //全局缓存
    public static Map<String, String> cache = new ConcurrentHashMap<String, String>();

    public static void main(String[] args) throws IOException {

        // 创建服务端ServerSocket的时候，传入端口8888
        // backlog,值设为10,作用是客户端建立连接时服务端没法立即处理的话，能够等待的队列长度。
        ServerSocket serverSocket = new ServerSocket(8888, 10);

        byte[] buffer = new byte[512];
        while (true) {
            // 接受客户端连接请求
            Socket clientSocket = null;
            clientSocket = serverSocket.accept();
            System.out.println("client address:" + clientSocket.getRemoteSocketAddress().toString());

            //读取数据并且操作缓存,然后写回数据
            try {
                //读数据
                InputStream in = clientSocket.getInputStream();
                int bytesRead = in.read(buffer, 0, 512);
                int totalBytesRead = 0;
                while (bytesRead != -1) {
                    totalBytesRead += bytesRead;
                    bytesRead = in.read(buffer, totalBytesRead, 512 - totalBytesRead);
                }

                //操作缓存
                byte[] response = Util.processRequest(cache, buffer, totalBytesRead, false);
                Util.log_debug("response:" + new String(response));

                //写回数据
                OutputStream os = clientSocket.getOutputStream();
                os.write(response);
                os.flush();
                clientSocket.shutdownOutput();

            } catch (IOException e) {
                System.out.println("read or write data exception");
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
