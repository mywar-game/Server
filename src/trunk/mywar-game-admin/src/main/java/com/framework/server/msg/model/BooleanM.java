package com.framework.server.msg.model;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;

public class BooleanM
    implements ICodeAble {

  public boolean ob;

  public void decode(IXInputStream dataInputStream) throws IOException {
    ob = dataInputStream.readBoolean();
  }

  public void encode(IXOutStream dataOutputStream) throws IOException {
    dataOutputStream.writeBoolean(ob);
  }

  public BooleanM(boolean value) {
    ob = value;
  }

  public BooleanM() {
  }

  public boolean value() {
    return ob;
  }

  public boolean equals(Object ob) {
    if (ob == null) {
      return false;
    }

    if (ob instanceof BooleanM) {
      if ( ( (BooleanM) ob).value() == this.value()) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

}
