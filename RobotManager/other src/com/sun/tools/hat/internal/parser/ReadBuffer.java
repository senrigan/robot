package com.sun.tools.hat.internal.parser;

import java.io.IOException;

public abstract interface ReadBuffer
{
  public abstract void get(long paramLong, byte[] paramArrayOfByte)
    throws IOException;
  
  public abstract char getChar(long paramLong)
    throws IOException;
  
  public abstract byte getByte(long paramLong)
    throws IOException;
  
  public abstract short getShort(long paramLong)
    throws IOException;
  
  public abstract int getInt(long paramLong)
    throws IOException;
  
  public abstract long getLong(long paramLong)
    throws IOException;
}


/* Location:              C:\Program Files\Java\jdk1.7.0_76\lib\tools.jar!\com\sun\tools\hat\internal\parser\ReadBuffer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */