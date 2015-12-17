package com.framework.server.msg.model;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;

public class CharM
    implements ICodeAble {

  public char ob;

  public void decode(IXInputStream dataInputStream) throws IOException {
    ob = dataInputStream.readChar();
  }

  public void encode(IXOutStream dataOutputStream) throws IOException {
    dataOutputStream.writeChar(ob);
  }

  public CharM(char value) {
    ob = value;
  }

  public CharM() {
  }

  public char value() {
    return ob;
  }

  public boolean equals(Object ob) {
    if (ob == null) {
      return false;
    }

    if (ob instanceof CharM) {
      if ( ( (CharM) ob).value() == this.value()) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

}
