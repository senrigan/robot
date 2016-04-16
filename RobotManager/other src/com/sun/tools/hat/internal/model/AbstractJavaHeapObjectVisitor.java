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
/*    */ 
/*    */ public abstract class AbstractJavaHeapObjectVisitor
/*    */   implements JavaHeapObjectVisitor
/*    */ {
/*    */   public abstract void visit(JavaHeapObject paramJavaHeapObject);
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
/*    */   public boolean exclude(JavaClass paramJavaClass, JavaField paramJavaField)
/*    */   {
/* 50 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public boolean mightExclude()
/*    */   {
/* 57 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Program Files\Java\jdk1.7.0_76\lib\tools.jar!\com\sun\tools\hat\internal\model\AbstractJavaHeapObjectVisitor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */