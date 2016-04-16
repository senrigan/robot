/*     */ package com.sun.tools.attach;
/*     */ 
/*     */ import com.sun.tools.attach.spi.AttachProvider;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class VirtualMachine
/*     */ {
/*     */   private AttachProvider provider;
/*     */   private String id;
/*     */   private volatile int hash;
/*     */   
/*     */   protected VirtualMachine(AttachProvider paramAttachProvider, String paramString)
/*     */   {
/* 122 */     if (paramAttachProvider == null) {
/* 123 */       throw new NullPointerException("provider cannot be null");
/*     */     }
/* 125 */     if (paramString == null) {
/* 126 */       throw new NullPointerException("id cannot be null");
/*     */     }
/* 128 */     provider = paramAttachProvider;
/* 129 */     id = paramString;
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
/*     */   public static List<VirtualMachineDescriptor> list()
/*     */   {
/* 148 */     ArrayList localArrayList = new ArrayList();
/*     */     
/* 150 */     List<AttachProvider> localList = AttachProvider.providers();
/* 151 */     for (AttachProvider localAttachProvider : localList) {
/* 152 */       localArrayList.addAll(localAttachProvider.listVirtualMachines());
/*     */     }
/* 154 */     return localArrayList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static VirtualMachine attach(String paramString)
/*     */     throws AttachNotSupportedException, IOException,Throwable
/*     */   {
/* 203 */     if (paramString == null) {
/* 204 */       throw new NullPointerException("id cannot be null");
/*     */     }
/* 206 */     List<AttachProvider> localList = AttachProvider.providers();
/* 207 */     if (localList.size() == 0) {
/* 208 */       throw new AttachNotSupportedException("no providers installed");
/*     */     }
/* 210 */     Object localObject = null;
/* 211 */     for (AttachProvider localAttachProvider : localList) {
/*     */       try {
/* 213 */         return localAttachProvider.attachVirtualMachine(paramString);
/*     */       } catch (AttachNotSupportedException localAttachNotSupportedException) {
/* 215 */         localObject = localAttachNotSupportedException;
/*     */       }
/*     */     }
/* 218 */     throw ((Throwable)localObject);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static VirtualMachine attach(VirtualMachineDescriptor paramVirtualMachineDescriptor)
/*     */     throws AttachNotSupportedException, IOException,Throwable
/*     */   {
/* 255 */     return paramVirtualMachineDescriptor.provider().attachVirtualMachine(paramVirtualMachineDescriptor);
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
/*     */   public abstract void detach()
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final AttachProvider provider()
/*     */   {
/* 283 */     return provider;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final String id()
/*     */   {
/* 292 */     return id;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract void loadAgentLibrary(String paramString1, String paramString2)
/*     */     throws AgentLoadException, AgentInitializationException, IOException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void loadAgentLibrary(String paramString)
/*     */     throws AgentLoadException, AgentInitializationException, IOException
/*     */   {
/* 377 */     loadAgentLibrary(paramString, null);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract void loadAgentPath(String paramString1, String paramString2)
/*     */     throws AgentLoadException, AgentInitializationException, IOException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void loadAgentPath(String paramString)
/*     */     throws AgentLoadException, AgentInitializationException, IOException
/*     */   {
/* 459 */     loadAgentPath(paramString, null);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract void loadAgent(String paramString1, String paramString2)
/*     */     throws AgentLoadException, AgentInitializationException, IOException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void loadAgent(String paramString)
/*     */     throws AgentLoadException, AgentInitializationException, IOException
/*     */   {
/* 526 */     loadAgent(paramString, null);
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
/*     */   public abstract Properties getSystemProperties()
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract Properties getAgentProperties()
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 588 */     if (hash != 0) {
/* 589 */       return hash;
/*     */     }
/* 591 */     hash = (provider.hashCode() * 127 + id.hashCode());
/* 592 */     return hash;
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
/* 613 */     if (paramObject == this)
/* 614 */       return true;
/* 615 */     if (!(paramObject instanceof VirtualMachine))
/* 616 */       return false;
/* 617 */     VirtualMachine localVirtualMachine = (VirtualMachine)paramObject;
/* 618 */     if (localVirtualMachine.provider() != provider()) {
/* 619 */       return false;
/*     */     }
/* 621 */     if (!localVirtualMachine.id().equals(id())) {
/* 622 */       return false;
/*     */     }
/* 624 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 631 */     return provider.toString() + ": " + id;
/*     */   }
/*     */ }

/* Location:           C:\Program Files\Java\jdk1.7.0_76\lib\tools.jar
 * Qualified Name:     com.sun.tools.attach.VirtualMachine
 * Java Class Version: 7 (51.0)
 * JD-Core Version:    0.7.1
 */