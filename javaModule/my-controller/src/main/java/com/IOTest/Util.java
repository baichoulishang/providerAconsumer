package com.IOTest;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Util {


    public static int LOG_LEVEL = 0; //0 info 1 debug

    public static void main(String[] args) throws FileNotFoundException {
        method3();
        System.out.println("=============");
        method4();
    }

    public static void method4() {
        RandomAccessFile aFile = null;
        FileChannel fc = null;
        try {
            aFile = new RandomAccessFile("E:\\111.txt", "rw");
            fc = aFile.getChannel();
            long timeBegin = System.currentTimeMillis();
            // 使用基本的ByteBuffer来读取文件
            ByteBuffer buff = ByteBuffer.allocate((int) aFile.length());
            buff.clear();
            fc.read(buff);
            long timeEnd = System.currentTimeMillis();
            System.out.println("Read time: " + (timeEnd - timeBegin) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(aFile, fc);
        }
    }

    public static void method3() {
        RandomAccessFile aFile = null;
        FileChannel fc = null;
        try {
            aFile = new RandomAccessFile("E:\\111.txt", "rw");
            fc = aFile.getChannel();
            long timeBegin = System.currentTimeMillis();
            // 转换成MappedByteBuffer
            MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, aFile.length());
            long timeEnd = System.currentTimeMillis();
            System.out.println("Read time: " + (timeEnd - timeBegin) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(aFile, fc);
        }
    }

    private static void close(RandomAccessFile aFile, FileChannel fc) {
        try {
            if (aFile != null) {
                aFile.close();
            }
            if (fc != null) {
                fc.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void server() {
        ServerSocket serverSocket = null;
        InputStream in = null;
        try {
            serverSocket = new ServerSocket(8080);
            int recvMsgSize = 0;
            byte[] recvBuf = new byte[1024];
            while (true) {
                Socket clntSocket = serverSocket.accept();
                SocketAddress clientAddress = clntSocket.getRemoteSocketAddress();
                System.out.println("Handling client at " + clientAddress);
                in = clntSocket.getInputStream();
                while ((recvMsgSize = in.read(recvBuf)) != -1) {
                    byte[] temp = new byte[recvMsgSize];
                    System.arraycopy(recvBuf, 0, temp, 0, recvMsgSize);
                    System.out.println(new String(temp));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void client() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("10.10.195.115", 8080));
            if (socketChannel.finishConnect()) {
                int i = 0;
                while (true) {
                    TimeUnit.SECONDS.sleep(1);
                    String info = "I'm " + i++ + "-th information from client";
                    buffer.clear();
                    buffer.put(info.getBytes());
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        System.out.println(buffer);
                        socketChannel.write(buffer);
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            closeClient(socketChannel);
        }
    }

    private static void closeClient(SocketChannel socketChannel) {
        try {
            if (socketChannel != null) {
                socketChannel.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void method2() {
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream("C:\\Users\\Chocolate\\Desktop\\111.txt"));
            byte[] buf = new byte[1024];
            int bytesRead = in.read(buf);
            while (bytesRead != -1) {
                for (int i = 0; i < bytesRead; i++)
                    System.out.print((char) buf[i]);
                bytesRead = in.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAll(in);
        }
    }

    /**
     * 利用NIO进行写操作
     */
    public static void method1() {
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile("C:\\Users\\Chocolate\\Desktop\\111.txt", "rw");
            // 创建相关的管道
            FileChannel fileChannel = file.getChannel();
            // 设定ByteBuffer的缓冲区的大小
            ByteBuffer buf = ByteBuffer.allocate(1024);
            // 通过管道将数据写进ByteBuffer中,返回数据的数量
            int bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);
            while (bytesRead != -1) {
                // 让ByteBuffer做好被读取的准备
                buf.flip();
                while (buf.hasRemaining()) {
                    // 从ByteBuffer中读取数据
                    System.out.print((char) buf.get());
                }
                buf.compact();
                bytesRead = fileChannel.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void closeAll(InputStream in) {
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //把一个String前边加上一个byte，表示长度
    public static byte[] addLength(String str) {
        byte len = (byte) str.length();
        byte[] ret = new byte[len + 1];
        ret[0] = len;
        for (int i = 0; i < len; i++) {
            ret[i + 1] = (byte) str.charAt(i);
        }
        return ret;
    }

    // 根据input返回一个output，操作缓存, prefixLength为true，则在前面加长度
    // input:
    // ->get|key
    // ->set|key|value
    //
    // output:
    // ->errorcode|response
    //  ->0|response set成功或者get有值
    //  ->1|response get的为null
    //  ->2|bad command
    public static byte[] processRequest(Map<String, String> cache, byte[] request, int length, boolean prefixLength) {
        if (request == null) {
            return prefixLength ? addLength("2|bad command") : "2|bad command".getBytes();
        }
        String req = new String(request, 0, length);
        Util.log_debug("command:" + req);
        String[] params = req.split("\\|");

        if (params.length < 2 || params.length > 3 || !(params[0].equals("get") || params[0].equals("set"))) {
            return prefixLength ? addLength("2|bad command") : "2|bad command".getBytes();
        }
        if (params[0].equals("get")) {
            String value = cache.get(params[1]);
            if (value == null) {
                return prefixLength ? addLength("1|null") : "1|null".getBytes();
            } else {
                return prefixLength ? addLength("0|" + value) : ("0|" + value).getBytes();
            }
        }

        if (params[0].equals("set") && params.length >= 3) {
            cache.put(params[1], params[2]);
            return prefixLength ? addLength("0|success") : ("0|success").getBytes();
        } else {
            return prefixLength ? addLength("2|bad command") : "2|bad command".getBytes();
        }

    }

    public static void log_debug(String str) {
        if (LOG_LEVEL >= 1) {
            System.out.println(str);
        }
    }

    public static void log_info(String str) {
        if (LOG_LEVEL >= 0) {
            System.out.println(str);
        }
    }
}
