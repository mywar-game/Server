package com.framework.server.msg.model;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;

public class LongM
    implements ICodeAble {

  public long ob;

  public void decode(IXInputStream dataInputStream) throws IOException {
    ob = dataInputStream.readLong();
  }

  public void encode(IXOutStream dataOutputStream) throws IOException {
    dataOutputStream.writeLong(ob);
  }

  public LongM(long value) {
    ob = value;
  }

  public LongM() {
  }

  public long value() {
    return ob;
  }

  public boolean equals(Object ob) {
    if (ob == null) {
      return false;
    }

    if (ob instanceof LongM) {
      if ( ( (LongM) ob).value() == this.value()) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

}
