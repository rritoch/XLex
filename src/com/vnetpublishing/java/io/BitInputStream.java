package com.vnetpublishing.java.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public abstract class BitInputStream extends InputStream implements BitInput {

	@Override
	public int readbit(boolean[] b) throws IOException {
		return readbit(b,0,b.length);
	}

	@Override
	public int readbit(boolean[] b, int offset, int len) throws IOException {
		
		
		if (b == null) {
			throw new NullPointerException();
		}
		
		if ((offset < 0) || (len < 0) || (len > (b.length - offset))) {
			throw new IndexOutOfBoundsException();
		}
		
		int r;
		int ret = 0;
		while (ret < len) {
			r = readbit();
			if (r < 1) {
				break;
			}
			b[ret + offset] = (r > 0) ? true : false; 
		}
		
		if (ret < 1) {
			return -1;
		}
		
		return ret;
	}

	@Override
	public int read() throws IOException {
		boolean[] bb = new boolean[8];
		Arrays.fill(bb, false);
		
		int r = readbit(bb);
		
		if (r < 1) {
			return -1;
		}
		
		// Assume BE (Big Endian)
		return 
			(bb[0] ? 128 : 0) | 
			(bb[1] ? 64 : 0) | 
			(bb[2] ? 32 : 0) | 
			(bb[3] ? 16 : 0) |
			(bb[4] ? 8 : 0) |
			(bb[5] ? 4 : 0) |
			(bb[6] ? 2 : 0) |
			(bb[7] ? 1 : 0);
	}

}
