package com.IOTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author 陈宜康
 * @date 2019/12/21 19:43
 * @forWhat
 */
public class ServerConnect {
    private static final int BUF_SIZE = 1024;
    private static final int PORT = 8080;
    private static final int TIMEOUT = 3000;

    public static void main(String[] args) {
        selector();
    }


    public static void handleAccept(SelectionKey key) throws IOException {
        ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
        // 监听新进来的连接
        SocketChannel sc = ssChannel.accept();
        sc.configureBlocking(false);
        sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocateDirect(BUF_SIZE));
    }

    public static void handleRead(SelectionKey key) throws IOException {
        // 从SelectionKey中得到通道对象,并且转换成我们需要的类型
        SocketChannel sc = (SocketChannel) key.channel();
        // 得到ByteBuffer
        ByteBuffer buf = (ByteBuffer) key.attachment();
        // 从通道中将数据写入到ByteBuffer中
        // 注意,将数据写入到ByteBuffer中调用的是read方法!!!!
        // 这里使用的是FileChannel的方法了,返回的是剩余可以读取的字节数
        long bytesRead = sc.read(buf);
        while (bytesRead > 0) {
            // 让ByteBuffer做好被读取的准备
            buf.flip();
            // 若有介于position 和limit之间的元素，则返回true
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            // 清空缓冲区，将position设置为0，limit设置为容量。我们可以调用此方法覆写缓冲区
            buf.clear();
            // 重新计算可以读取的字节数
            bytesRead = sc.read(buf);
        }
        if (bytesRead == -1) {
            sc.close();
        }
    }

    public static void handleWrite(SelectionKey key) throws IOException {
        ByteBuffer buf = (ByteBuffer) key.attachment();
        buf.flip();
        SocketChannel sc = (SocketChannel) key.channel();
        while (buf.hasRemaining()) {
            sc.write(buf);
        }
        buf.compact();
    }

    public static void selector() {
        Selector selector = null;
        ServerSocketChannel ssc = null;
        try {
            selector = Selector.open();
            // 打开ServerSocketChannel
            ssc = ServerSocketChannel.open();
            // 绑定端口
            ssc.socket().bind(new InetSocketAddress(PORT));
            ssc.configureBlocking(false);

            // 将Channel注册到Selector上.此处的Channel是ServerSocketChannel
            // 返回一个SelectionKey 对象。这个对象代表了注册到该Selector的通道
            SelectionKey register = ssc.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                if (selector.select(TIMEOUT) == 0) {
                    System.out.println("==");
                    continue;
                }
                // 得到已经就绪的通道的信息
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                // 根据准备就绪的通道的类型,执行不同的方法
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    if (key.isAcceptable()) {
                        handleAccept(key);
                    }
                    if (key.isReadable()) {
                        handleRead(key);
                    }
                    if (key.isWritable() && key.isValid()) {
                        handleWrite(key);
                    }
                    if (key.isConnectable()) {
                        System.out.println("isConnectable = true");
                    }
                    iter.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAll(selector, ssc);
        }
    }

    private static void closeAll(Selector selector, ServerSocketChannel ssc) {
        try {
            if (selector != null) {
                selector.close();
            }
            if (ssc != null) {
                ssc.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
