package com.framework.server.msg.model;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;

public class ByteM
    implements ICodeAble {

  public byte ob;

  public void decode(IXInputStream dataInputStream) throws IOException {
    ob = dataInputStream.readByte();
  }

  public void encode(IXOutStream dataOutputStream) throws IOException {
    dataOutputStream.writeByte(ob);
  }

  public ByteM(byte value) {
    ob = value;
  }

  public ByteM() {
  }

  public byte value() {
    return ob;
  }

  public boolean equals(Object ob) {
    if (ob == null) {
      return false;
    }

    if (ob instanceof ByteM) {
      if (((ByteM) ob).value() == this.value()) {
        return true;
      } else {
          return false;
      }
    } else {
        return false;
    }
  }

}
