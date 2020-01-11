package com.IOTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 陈宜康
 * @date 2019/12/18 20:19
 * @forWhat
 */
public class RedisServer2 {
    // 全局缓存对象,使用ConcurrentHashMap来完成缓存
    public static Map<String, String> cache = new ConcurrentHashMap<String, String>();

    public static void main(String[] args) throws IOException {
        // 用于处理请求的线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(200, 1000, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1000));
        // 建立服务端ServerSocket的时候，传入端口8888
        // backlog:值设为10,作用是客户端建立连接时服务端没法立即处理的话，能够等待的队列长度。
        ServerSocket serverSocket = new ServerSocket(8888, 1000);

        while (true) {
            // 接受客户端连接请求
            // 主线程一直阻塞accept，来了一个连接就交给一个线程
            Socket clientSocket = serverSocket.accept();
            Util.log_debug(clientSocket.getRemoteSocketAddress().toString());
            // 让线程池处理这个请求
            threadPool.execute(new RequestHandler(clientSocket));
        }
    }
}

/**
 * 创建任务
 */
class RequestHandler implements Runnable {
    // Socket对象
    private Socket clientSocket;

    public RequestHandler(Socket socket) {
        clientSocket = socket;
    }

    public void run() {
        byte[] buffer = new byte[512];
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
            // 操作缓存
            // 根据input返回一个output，操作缓存
            byte[] response = Util.processRequest(RedisServer2.cache, buffer, totalBytesRead, false);
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
