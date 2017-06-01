package com.eglsc.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created by ${huipei.x} on 2017-5-25.
 */
public class ByteArrayOutputStream extends OutputStream {
    private final ByteBuffer buffer;

    public ByteArrayOutputStream() {
        this(1024);
    }

    public ByteArrayOutputStream(int size) {
        this.buffer = new ByteBuffer(size);
    }

    public void write(byte[] b, int off, int len) {
        this.buffer.append(b, off, len);
    }

    public void write(int b) {
        this.buffer.append((byte)b);
    }

    public int size() {
        return this.buffer.size();
    }

    public void close() {
    }

    public void reset() {
        this.buffer.reset();
    }

    public void writeTo(OutputStream out) throws IOException {
        int index = this.buffer.index();

        for(int i = 0; i < index; ++i) {
            byte[] buf = this.buffer.array(i);
            out.write(buf);
        }

        out.write(this.buffer.array(index), 0, this.buffer.offset());
    }

    public byte[] toByteArray() {
        return this.buffer.toArray();
    }

    public String toString() {
        return new String(this.toByteArray());
    }

    public String toString(String enc) throws UnsupportedEncodingException {
        return new String(this.toByteArray(), enc);
    }

    public String toString(Charset charset) {
        return new String(this.toByteArray(), charset);
    }
}
