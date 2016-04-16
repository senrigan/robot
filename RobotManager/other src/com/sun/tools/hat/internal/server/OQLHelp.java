/*    */ package com.sun.tools.hat.internal.server;
/*    */ 
/*    */ import java.io.BufferedInputStream;
/*    */ import java.io.InputStream;
/*    */ import java.io.PrintWriter;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ class OQLHelp
/*    */   extends QueryHandler
/*    */ {
/*    */   public void run()
/*    */   {
/* 49 */     Object localObject = getClass().getResourceAsStream("/com/sun/tools/hat/resources/oqlhelp.html");
/* 50 */     int i = -1;
/*    */     try {
/* 52 */       localObject = new BufferedInputStream((InputStream)localObject);
/* 53 */       while ((i = ((InputStream)localObject).read()) != -1) {
/* 54 */         this.out.print((char)i);
/*    */       }
/*    */     } catch (Exception localException) {
/* 57 */       printException(localException);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Program Files\Java\jdk1.7.0_76\lib\tools.jar!\com\sun\tools\hat\internal\server\OQLHelp.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */