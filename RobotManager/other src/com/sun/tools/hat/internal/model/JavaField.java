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
/*    */ public class JavaField
/*    */ {
/*    */   private String name;
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
/*    */   private String signature;
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
/*    */   public JavaField(String paramString1, String paramString2)
/*    */   {
/* 47 */     this.name = paramString1;
/* 48 */     this.signature = paramString2;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean hasId()
/*    */   {
/* 57 */     int i = this.signature.charAt(0);
/* 58 */     return (i == 91) || (i == 76);
/*    */   }
/*    */   
/*    */   public String getName() {
/* 62 */     return this.name;
/*    */   }
/*    */   
/*    */   public String getSignature() {
/* 66 */     return this.signature;
/*    */   }
/*    */ }


/* Location:              C:\Program Files\Java\jdk1.7.0_76\lib\tools.jar!\com\sun\tools\hat\internal\model\JavaField.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */