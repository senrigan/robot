/*    */ package com.sun.tools.hat.internal.parser;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PositionDataInputStream
/*    */   extends DataInputStream
/*    */ {
/*    */   public PositionDataInputStream(InputStream paramInputStream)
/*    */   {
/* 45 */     super((paramInputStream instanceof PositionInputStream) ? paramInputStream : new PositionInputStream(paramInputStream));
/*    */   }
/*    */   
/*    */   public boolean markSupported()
/*    */   {
/* 50 */     return false;
/*    */   }
/*    */   
/*    */   public void mark(int paramInt) {
/* 54 */     throw new UnsupportedOperationException("mark");
/*    */   }
/*    */   
/*    */   public void reset() {
/* 58 */     throw new UnsupportedOperationException("reset");
/*    */   }
/*    */   
/*    */   public long position() {
/* 62 */     return ((PositionInputStream)this.in).position();
/*    */   }
/*    */ }


/* Location:              C:\Program Files\Java\jdk1.7.0_76\lib\tools.jar!\com\sun\tools\hat\internal\parser\PositionDataInputStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */