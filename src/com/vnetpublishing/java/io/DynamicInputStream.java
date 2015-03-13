package com.vnetpublishing.java.io;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class DynamicInputStream extends BitInputStream implements DynamicInput {

	private byte bit_offset = 7;
	private byte buff = 0;
	private DataInputStream dis = null;
	private BufferedInputStream is;
	
	public DynamicInputStream(InputStream is) {
		this.is = new BufferedInputStream(is);
	}
	
	private DataInputStream getDataInputStream() {
		if (null == dis) { 
			dis = new DataInputStream(this);
		}
		return dis;
	}
	
	@Override
	public void readFully(byte[] b) throws IOException {
		getDataInputStream().readFully(b);
	}

	@Override
	public void readFully(byte[] b, int off, int len) throws IOException {
		getDataInputStream().readFully(b,off,len);
	}

	@Override
	public int skipBytes(int n) throws IOException {
		return getDataInputStream().skipBytes(n);
	}

	@Override
	public boolean readBoolean() throws IOException {
		return getDataInputStream().readBoolean();
	}

	@Override
	public byte readByte() throws IOException {
		return getDataInputStream().readByte();
	}

	@Override
	public int readUnsignedByte() throws IOException {
		return getDataInputStream().readUnsignedByte();
	}

	@Override
	public short readShort() throws IOException {
		return getDataInputStream().readShort();
	}

	@Override
	public int readUnsignedShort() throws IOException {
		return getDataInputStream().readUnsignedShort();
	}

	@Override
	public char readChar() throws IOException {
		return getDataInputStream().readChar();
	}

	@Override
	public int readInt() throws IOException {
		return getDataInputStream().readInt();
	}

	@Override
	public long readLong() throws IOException {
		return getDataInputStream().readLong();
	}

	@Override
	public float readFloat() throws IOException {
		return getDataInputStream().readFloat();
	}

	@Override
	public double readDouble() throws IOException {
		return getDataInputStream().readDouble();
	}

	
	@Override
	@Deprecated
	public String readLine() throws IOException { 
		return getDataInputStream().readLine();
	}

	@Override
	public String readUTF() throws IOException {
		return getDataInputStream().readUTF();
	}

	@Override
	public synchronized int readbit() throws IOException {
		if (bit_offset == (byte)7) {
			int r = is.read();
			if (r < 0) {
				return r;
			}
			bit_offset = (byte)6;
			buff = (byte)r;
			return r & 128;
		}
		
		try {
			return 1 & (buff >> bit_offset);
		} finally {
			if (bit_offset == (byte)0) {
				bit_offset = 7;
			} else {
				bit_offset--;
			}
		}
	}

	@Override
	public int getChar(String charset) throws IOException {
		return getChar(Charset.forName(charset));
	}
	
	@Override
	public int getChar(Charset charset) throws IOException {
		int mbpc = (int)Math.ceil(charset.newEncoder().maxBytesPerChar());
		byte[] bb = new byte[mbpc];
		byte bo = bit_offset;
		is.mark(mbpc);
		
		int c = read(bb);
		is.reset();
		bit_offset = bo;
		
		if (c < 1) {
			return -1;
		}
		
		String s = new String(bb,charset).substring(0,1);
		bb = s.getBytes(charset);
		read(bb);
		return s.charAt(0);
	}

}
