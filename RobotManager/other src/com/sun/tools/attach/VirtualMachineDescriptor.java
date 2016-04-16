/*     */ package com.sun.tools.attach;
/*     */ 
/*     */ import com.sun.tools.attach.spi.AttachProvider;
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
/*     */ public class VirtualMachineDescriptor
/*     */ {
/*     */   private AttachProvider provider;
/*     */   private String id;
/*     */   private String displayName;
/*     */   private volatile int hash;
/*     */   
/*     */   public VirtualMachineDescriptor(AttachProvider paramAttachProvider, String paramString1, String paramString2)
/*     */   {
/*  77 */     if (paramAttachProvider == null) {
/*  78 */       throw new NullPointerException("provider cannot be null");
/*     */     }
/*  80 */     if (paramString1 == null) {
/*  81 */       throw new NullPointerException("identifier cannot be null");
/*     */     }
/*  83 */     if (paramString2 == null) {
/*  84 */       throw new NullPointerException("display name cannot be null");
/*     */     }
/*  86 */     provider = paramAttachProvider;
/*  87 */     id = paramString1;
/*  88 */     displayName = paramString2;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public VirtualMachineDescriptor(AttachProvider paramAttachProvider, String paramString)
/*     */   {
/* 113 */     this(paramAttachProvider, paramString, paramString);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AttachProvider provider()
/*     */   {
/* 122 */     return provider;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String id()
/*     */   {
/* 131 */     return id;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String displayName()
/*     */   {
/* 140 */     return displayName;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 152 */     if (hash != 0) {
/* 153 */       return hash;
/*     */     }
/* 155 */     hash = (provider.hashCode() * 127 + id.hashCode());
/* 156 */     return hash;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 177 */     if (paramObject == this)
/* 178 */       return true;
/* 179 */     if (!(paramObject instanceof VirtualMachineDescriptor))
/* 180 */       return false;
/* 181 */     VirtualMachineDescriptor localVirtualMachineDescriptor = (VirtualMachineDescriptor)paramObject;
/* 182 */     if (localVirtualMachineDescriptor.provider() != provider()) {
/* 183 */       return false;
/*     */     }
/* 185 */     if (!localVirtualMachineDescriptor.id().equals(id())) {
/* 186 */       return false;
/*     */     }
/* 188 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 195 */     String str = provider.toString() + ": " + id;
/* 196 */     if (displayName != id) {
/* 197 */       str = str + " " + displayName;
/*     */     }
/* 199 */     return str;
/*     */   }
/*     */ }

/* Location:           C:\Program Files\Java\jdk1.7.0_76\lib\tools.jar
 * Qualified Name:     com.sun.tools.attach.VirtualMachineDescriptor
 * Java Class Version: 7 (51.0)
 * JD-Core Version:    0.7.1
 */