/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.javabase.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class ChannelStudy {
	public static void main(final String[] args) throws IOException {
		ReadableByteChannel source = Channels.newChannel(new FileInputStream(
				"pom.xml"));
		WritableByteChannel dest = Channels.newChannel(new FileOutputStream(
				"test.txt"));
		channelCopy1(source, dest);
		source.close();
		dest.close();
	}

	/**
	 * Channel copy method 1. This method copies data from the src channel and
	 * writes it to the dest channel until EOF on src. This implementation makes
	 * use of compact( ) on the temp buffer to pack down the data if the buffer
	 * wasn't fully drained. This may result in data copying, but minimizes
	 * system calls. It also requires a cleanup loop to make sure all the data
	 * gets sent.
	 * 
	 * @param source
	 *            the source
	 * @param dest
	 *            the dest
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private static void channelCopy1(final ReadableByteChannel source,
			final WritableByteChannel dest) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
		while (source.read(buffer) != -1) {
			buffer.flip(); // ready for read or write
			dest.write(buffer);
			buffer.compact();// if partial transfer, shift remainder down, if
								// down, same as clear.
		}
		buffer.flip();
		while (buffer.hasRemaining()) {
			dest.write(buffer);
		}
	}

	/**
	 * Channel copy2.This method performs the same copy, but assures the temp
	 * buffer is empty before reading more data. This never requires data
	 * copying but may result in more systems calls. No post-loop cleanup is
	 * needed because the buffer will be empty when the loop is exited.
	 * 
	 * @param source
	 *            the source
	 * @param dest
	 *            the dest
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private static void channelCopy2(final ReadableByteChannel source,
			final WritableByteChannel dest) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
		while (source.read(buffer) != -1) {
			// Prepare the buffer to be drained
			buffer.flip(); // Make sure that the buffer was fully drained
			while (buffer.hasRemaining()) {
				dest.write(buffer);
			}
			// Make the buffer empty, ready for filling
			buffer.clear();
		}
	}
}
