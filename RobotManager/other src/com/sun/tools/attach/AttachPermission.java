/*     */ package com.sun.tools.attach;
/*     */ 
/*     */ import java.security.BasicPermission;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class AttachPermission
/*     */   extends BasicPermission
/*     */ {
/*     */   static final long serialVersionUID = -4619447669752976181L;
/*     */   
/*     */   public AttachPermission(String paramString)
/*     */   {
/*  97 */     super(paramString);
/*  98 */     if ((!paramString.equals("attachVirtualMachine")) && (!paramString.equals("createAttachProvider"))) {
/*  99 */       throw new IllegalArgumentException("name: " + paramString);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AttachPermission(String paramString1, String paramString2)
/*     */   {
/* 116 */     super(paramString1);
/* 117 */     if ((!paramString1.equals("attachVirtualMachine")) && (!paramString1.equals("createAttachProvider"))) {
/* 118 */       throw new IllegalArgumentException("name: " + paramString1);
/*     */     }
/* 120 */     if ((paramString2 != null) && (paramString2.length() > 0)) {
/* 121 */       throw new IllegalArgumentException("actions: " + paramString2);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Program Files\Java\jdk1.7.0_76\lib\tools.jar
 * Qualified Name:     com.sun.tools.attach.AttachPermission
 * Java Class Version: 7 (51.0)
 * JD-Core Version:    0.7.1
 */