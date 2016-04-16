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
/*    */ public class JavaDouble
/*    */   extends JavaValue
/*    */ {
/*    */   double value;
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
/*    */   public JavaDouble(double paramDouble)
/*    */   {
/* 47 */     this.value = paramDouble;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 51 */     return Double.toString(this.value);
/*    */   }
/*    */ }


/* Location:              C:\Program Files\Java\jdk1.7.0_76\lib\tools.jar!\com\sun\tools\hat\internal\model\JavaDouble.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */