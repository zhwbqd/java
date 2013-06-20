/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.javabase.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

public class BufferStudy
{
    public static void main(final String[] args)
    {
        byteBuffer();
        //                charBuffer();
        //		byteView();
    }

    private static void charBuffer()
    {
        char[] array = new char[100];
        CharBuffer bf = CharBuffer.allocate(100);
        System.out.println(bf.isReadOnly());
        bf = CharBuffer.wrap(array, 12, 20); //position: 12 -- limit: 32
        System.out.println(bf.isReadOnly());
        bf = CharBuffer.wrap(array);
        System.out.println(bf.isReadOnly());
        bf = CharBuffer.wrap("hello world", 1, 8);
        System.out.println(bf.isReadOnly());
        bf.append('a').put('b').append('c').flip();
        int count = bf.remaining();
        for (int i = 0; i<count; i++)
        {
            System.out.print(bf.get() + " ");
        }
        for (char c : array)
        {
            System.out.print(c + " ");
        }
        bf.clear(); //逻辑清空
        System.out.println("");

    }

    private static void byteBuffer()
    {
        ByteBuffer bf = ByteBuffer.allocate(100);
        /*填充*/
        bf.put((byte)'a');
        bf.put((byte)(0 >> 3));
        bf.putShort((short)(2 << 3)); //一个short 2个byte
        bf.putChar('a'); //一个char 2个byte
        bf.put((byte)'b');
        bf.putInt(1); //一个Int  4个byte
        bf.putLong(1000L); //一个long 8个byte, 默认使用大端存贮策略，即高位在后
        bf.put((byte)'H').put((byte)'e').put((byte)'l').put((byte)'l').put((byte)'o');
        System.out.println("position: " + bf.position() + " " + "limit: " + bf.limit());

        /*取得*/
        bf.flip();//翻转，设置position为0 limit为position
        System.out.println("position: " + bf.position() + " " + "limit: " + bf.limit());

        /*compact set new position = limit - old position, limit = capacity*/
        bf.position(10);
        bf.compact();
        System.out.println("position: " + bf.position() + " " + "limit: " + bf.limit());

        bf.flip();//翻转，设置position为0 limit为position
        System.out.println("position: " + bf.position() + " " + "limit: " + bf.limit());

        byte[] outp = new byte[bf.limit()];
        for (int i = 0; bf.hasRemaining(); i++)
        {
            outp[i] = bf.get();
        }

        bf.rewind(); //重新置position 为0， 重读
        System.out.println("position: " + bf.position() + " " + "limit: " + bf.limit());
        for (byte b : outp)
        {
            System.out.print(b);
            System.out.print(" ");
        }

        /*多字节数值被存储在内存中的方式一般被称为endian-ness（字节顺序）。如果数字数值的最高字节——big end（大端），位于低位地址，那么系统就是大端字节顺序.*/
        System.out.println(bf.order());
    }

	public static void byteView() {
		ByteBuffer byteBuffer = ByteBuffer.allocate(7).order(
				ByteOrder.BIG_ENDIAN);
		CharBuffer charBuffer = byteBuffer.asCharBuffer();
		// Load the ByteBuffer with some bytes
		byteBuffer.put(0, (byte) 0);
		byteBuffer.put(1, (byte) 'H');
		byteBuffer.put(2, (byte) 0);
		byteBuffer.put(3, (byte) 'i');
		byteBuffer.put(4, (byte) 0);
		byteBuffer.put(5, (byte) '!');
		byteBuffer.put(6, (byte) 0);
		println(byteBuffer);
		println(charBuffer);

		System.out.println(byteBuffer.order(ByteOrder.LITTLE_ENDIAN).getInt());
		// System.out.println(byteBuffer.getInt());
	}

	private static void println(final Buffer buffer) {
		System.out.println("pos=" + buffer.position() + ", limit="
				+ buffer.limit() + ", capacity=" + buffer.capacity() + ": '"
				+ buffer.toString() + "'");
	}
}
