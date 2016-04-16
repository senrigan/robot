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
/*    */ public abstract class JavaThing
/*    */ {
/*    */   public JavaThing dereference(Snapshot paramSnapshot, JavaField paramJavaField)
/*    */   {
/* 64 */     return this;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean isSameTypeAs(JavaThing paramJavaThing)
/*    */   {
/* 74 */     return getClass() == paramJavaThing.getClass();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public abstract boolean isHeapAllocated();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public abstract int getSize();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public abstract String toString();
/*    */   
/*    */ 
/*    */ 
/*    */   public int compareTo(JavaThing paramJavaThing)
/*    */   {
/* 96 */     return toString().compareTo(paramJavaThing.toString());
/*    */   }
/*    */ }


/* Location:              C:\Program Files\Java\jdk1.7.0_76\lib\tools.jar!\com\sun\tools\hat\internal\model\JavaThing.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */