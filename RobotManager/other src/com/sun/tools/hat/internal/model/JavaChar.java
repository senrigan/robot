/*    */ package com.sun.tools.hat.internal.model;
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
/*    */ public class JavaChar
/*    */   extends JavaValue
/*    */ {
/*    */   char value;
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
/*    */   public JavaChar(char paramChar)
/*    */   {
/* 47 */     this.value = paramChar;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 51 */     return "" + this.value;
/*    */   }
/*    */ }


/* Location:              C:\Program Files\Java\jdk1.7.0_76\lib\tools.jar!\com\sun\tools\hat\internal\model\JavaChar.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */