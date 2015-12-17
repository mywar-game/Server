package com.framework.server.msg.model;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;

public class IntM
    implements ICodeAble {

  public int ob;

  public void decode(IXInputStream dataInputStream) throws IOException {
    ob = dataInputStream.readInt();
  }

  public void encode(IXOutStream dataOutputStream) throws IOException {
    dataOutputStream.writeInt(ob);
  }

  public IntM(int value) {
    ob = value;
  }

  public IntM() {
  }

  public int value() {
    return ob;
  }

  public boolean equals(Object ob) {
    if (ob == null) {
      return false;
    }

    if (ob instanceof IntM) {
      if ( ( (IntM) ob).value() == this.value()) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

}
