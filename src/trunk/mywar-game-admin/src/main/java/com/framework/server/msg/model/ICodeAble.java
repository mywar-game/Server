package com.framework.server.msg.model;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;


public interface ICodeAble {
   public void encode(IXOutStream outputStream) throws IOException;
   public void decode(IXInputStream inputStream) throws IOException;
}
