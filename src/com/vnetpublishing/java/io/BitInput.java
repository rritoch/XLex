package com.vnetpublishing.java.io;

import java.io.IOException;

public interface BitInput {
	int readbit() throws IOException;
	int readbit(boolean[] b) throws IOException;
	int readbit(boolean[] b, int offset, int len) throws IOException;
}
