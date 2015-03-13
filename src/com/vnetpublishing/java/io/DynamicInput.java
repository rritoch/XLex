package com.vnetpublishing.java.io;

import java.io.DataInput;
import java.io.IOException;
import java.nio.charset.Charset;

public interface DynamicInput extends DataInput, BitInput {
	int getChar(String charset) throws IOException;
	int getChar(Charset charset) throws IOException;
}
