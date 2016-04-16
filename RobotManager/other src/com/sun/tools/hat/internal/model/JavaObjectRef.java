/*    */ package com.sun.tools.hat.internal.model;
/*    */ 
/*    */ import com.sun.tools.hat.internal.util.Misc;
/*    */ import java.io.PrintStream;
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
/*    */ public class JavaObjectRef
/*    */   extends JavaThing
/*    */ {
/*    */   private long id;
/*    */   
/*    */   public JavaObjectRef(long paramLong)
/*    */   {
/* 48 */     this.id = paramLong;
/*    */   }
/*    */   
/*    */   public long getId() {
/* 52 */     return this.id;
/*    */   }
/*    */   
/*    */   public boolean isHeapAllocated() {
/* 56 */     return true;
/*    */   }
/*    */   
/*    */   public JavaThing dereference(Snapshot paramSnapshot, JavaField paramJavaField) {
/* 60 */     return dereference(paramSnapshot, paramJavaField, true);
/*    */   }
/*    */   
/*    */   public JavaThing dereference(Snapshot paramSnapshot, JavaField paramJavaField, boolean paramBoolean) {
/* 64 */     if ((paramJavaField != null) && (!paramJavaField.hasId()))
/*    */     {
/*    */ 
/* 67 */       return new JavaLong(this.id);
/*    */     }
/* 69 */     if (this.id == 0L) {
/* 70 */       return paramSnapshot.getNullThing();
/*    */     }
/* 72 */     Object localObject = paramSnapshot.findThing(this.id);
/* 73 */     if (localObject == null) {
/* 74 */       if ((!paramSnapshot.getUnresolvedObjectsOK()) && (paramBoolean)) {
/* 75 */         String str = "WARNING:  Failed to resolve object id " + Misc.toHex(this.id);
/*    */         
/* 77 */         if (paramJavaField != null) {
/* 78 */           str = str + " for field " + paramJavaField.getName() + " (signature " + paramJavaField.getSignature() + ")";
/*    */         }
/*    */         
/* 81 */         System.out.println(str);
/*    */       }
/*    */       
/* 84 */       localObject = new HackJavaValue("Unresolved object " + Misc.toHex(this.id), 0);
/*    */     }
/*    */     
/* 87 */     return (JavaThing)localObject;
/*    */   }
/*    */   
/*    */   public int getSize() {
/* 91 */     return 0;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 95 */     return "Unresolved object " + Misc.toHex(this.id);
/*    */   }
/*    */ }


/* Location:              C:\Program Files\Java\jdk1.7.0_76\lib\tools.jar!\com\sun\tools\hat\internal\model\JavaObjectRef.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */