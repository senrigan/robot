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
/*    */ public class ReferenceChain
/*    */ {
/*    */   JavaHeapObject obj;
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
/*    */   ReferenceChain next;
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
/*    */   public ReferenceChain(JavaHeapObject paramJavaHeapObject, ReferenceChain paramReferenceChain)
/*    */   {
/* 47 */     this.obj = paramJavaHeapObject;
/* 48 */     this.next = paramReferenceChain;
/*    */   }
/*    */   
/*    */   public JavaHeapObject getObj() {
/* 52 */     return this.obj;
/*    */   }
/*    */   
/*    */   public ReferenceChain getNext() {
/* 56 */     return this.next;
/*    */   }
/*    */   
/*    */   public int getDepth() {
/* 60 */     int i = 1;
/* 61 */     ReferenceChain localReferenceChain = this.next;
/* 62 */     while (localReferenceChain != null) {
/* 63 */       i++;
/* 64 */       localReferenceChain = localReferenceChain.next;
/*    */     }
/* 66 */     return i;
/*    */   }
/*    */ }


/* Location:              C:\Program Files\Java\jdk1.7.0_76\lib\tools.jar!\com\sun\tools\hat\internal\model\ReferenceChain.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */