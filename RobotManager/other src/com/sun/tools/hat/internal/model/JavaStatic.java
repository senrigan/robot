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
/*    */ public class JavaStatic
/*    */ {
/*    */   private JavaField field;
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
/*    */   private JavaThing value;
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
/*    */   public JavaStatic(JavaField paramJavaField, JavaThing paramJavaThing)
/*    */   {
/* 50 */     this.field = paramJavaField;
/* 51 */     this.value = paramJavaThing;
/*    */   }
/*    */   
/*    */   public void resolve(JavaClass paramJavaClass, Snapshot paramSnapshot) {
/* 55 */     long l = -1L;
/* 56 */     if ((this.value instanceof JavaObjectRef)) {
/* 57 */       l = ((JavaObjectRef)this.value).getId();
/*    */     }
/* 59 */     this.value = this.value.dereference(paramSnapshot, this.field);
/* 60 */     if ((this.value.isHeapAllocated()) && (paramJavaClass.getLoader() == paramSnapshot.getNullThing()))
/*    */     {
/*    */ 
/*    */ 
/* 64 */       JavaHeapObject localJavaHeapObject = (JavaHeapObject)this.value;
/* 65 */       String str = "Static reference from " + paramJavaClass.getName() + "." + this.field.getName();
/*    */       
/* 67 */       paramSnapshot.addRoot(new Root(l, paramJavaClass.getId(), 9, str));
/*    */     }
/*    */   }
/*    */   
/*    */   public JavaField getField()
/*    */   {
/* 73 */     return this.field;
/*    */   }
/*    */   
/*    */   public JavaThing getValue() {
/* 77 */     return this.value;
/*    */   }
/*    */ }


/* Location:              C:\Program Files\Java\jdk1.7.0_76\lib\tools.jar!\com\sun\tools\hat\internal\model\JavaStatic.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */