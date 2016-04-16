/*     */ package com.sun.tools.attach.spi;
/*     */ 
/*     */ import com.sun.tools.attach.AttachNotSupportedException;
/*     */ import com.sun.tools.attach.AttachPermission;
/*     */ import com.sun.tools.attach.VirtualMachine;
/*     */ import com.sun.tools.attach.VirtualMachineDescriptor;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ServiceLoader;
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
/*     */ public abstract class AttachProvider
/*     */ {
/*  79 */   private static final Object lock = new Object();
/*  80 */   private static List<AttachProvider> providers = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected AttachProvider()
/*     */   {
/*  91 */     SecurityManager localSecurityManager = System.getSecurityManager();
/*  92 */     if (localSecurityManager != null) {
/*  93 */       localSecurityManager.checkPermission(new AttachPermission("createAttachProvider"));
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
/*     */   public abstract String name();
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
/*     */   public abstract String type();
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
/*     */   public abstract VirtualMachine attachVirtualMachine(String paramString)
/*     */     throws AttachNotSupportedException, IOException, Throwable;
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
/*     */   public VirtualMachine attachVirtualMachine(VirtualMachineDescriptor paramVirtualMachineDescriptor)
/*     */     throws AttachNotSupportedException, IOException,Throwable
/*     */   {
/* 190 */     if (paramVirtualMachineDescriptor.provider() != this) {
/* 191 */       throw new AttachNotSupportedException("provider mismatch");
/*     */     }
/* 193 */     return attachVirtualMachine(paramVirtualMachineDescriptor.id());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract List<VirtualMachineDescriptor> listVirtualMachines();
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
/*     */   public static List<AttachProvider> providers()
/*     */   {
/* 247 */     synchronized (lock) {
/* 248 */       if (providers == null) {
/* 249 */         providers = new ArrayList();
/*     */         
/* 251 */         ServiceLoader localServiceLoader = ServiceLoader.load(AttachProvider.class, AttachProvider.class.getClassLoader());
/*     */         
/*     */ 
/*     */ 
/* 255 */         Iterator localIterator = localServiceLoader.iterator();
/*     */         
/* 257 */         while (localIterator.hasNext()) {
/*     */           try {
/* 259 */             providers.add((AttachProvider) localIterator.next());
System.out.println("prividesr"+providers);
/*     */           } catch (Throwable localThrowable) {
/* 261 */             if ((localThrowable instanceof ThreadDeath)) {
/* 262 */               ThreadDeath localThreadDeath = (ThreadDeath)localThrowable;
/* 263 */               throw localThreadDeath;
/*     */             }
/*     */             
/* 266 */             System.err.println(localThrowable);
/*     */           }
/*     */         }
/*     */       }
/* 270 */       return Collections.unmodifiableList(providers);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Program Files\Java\jdk1.7.0_76\lib\tools.jar
 * Qualified Name:     com.sun.tools.attach.spi.AttachProvider
 * Java Class Version: 7 (51.0)
 * JD-Core Version:    0.7.1
 */