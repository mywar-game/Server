package com.framework.server.msg.model;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;

public class StringM
    implements ICodeAble {

  public String str;

  public void decode(IXInputStream dataInputStream) throws IOException {
    str = dataInputStream.readUTF();
  }

  public void encode(IXOutStream dataOutputStream) throws IOException {
    dataOutputStream.writeUTF(str);
  }

  public StringM(String value) {
    str = value;
  }

  public StringM() {
  }

  public String value() {
    return str;
  }

  public boolean equals(Object ob) {
    if (ob == null) {
      return false;
    }

    if (ob instanceof StringM) {
      if ( ( (StringM) ob).value().equals(this.value())) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

}
