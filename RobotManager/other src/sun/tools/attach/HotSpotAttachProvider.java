/*     */ package sun.tools.attach;
/*     */ 
/*     */ import com.sun.tools.attach.AttachNotSupportedException;
/*     */ import com.sun.tools.attach.AttachPermission;
/*     */ import com.sun.tools.attach.VirtualMachineDescriptor;
/*     */ import com.sun.tools.attach.spi.AttachProvider;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import sun.jvmstat.monitor.HostIdentifier;
/*     */ import sun.jvmstat.monitor.MonitoredHost;
/*     */ import sun.jvmstat.monitor.MonitoredVm;
/*     */ import sun.jvmstat.monitor.MonitoredVmUtil;
/*     */ import sun.jvmstat.monitor.VmIdentifier;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class HotSpotAttachProvider
/*     */   extends AttachProvider
/*     */ {
/*     */   private static final String JVM_VERSION = "java.property.java.vm.version";
/*     */   
/*     */   public void checkAttachPermission()
/*     */   {
/*  60 */     SecurityManager localSecurityManager = System.getSecurityManager();
/*  61 */     if (localSecurityManager != null) {
/*  62 */       localSecurityManager.checkPermission(new AttachPermission("attachVirtualMachine"));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<VirtualMachineDescriptor> listVirtualMachines()
/*     */   {
/*  74 */     ArrayList localArrayList = new ArrayList();
/*     */     
/*     */     MonitoredHost localMonitoredHost;
/*     */     Set localSet;
/*     */     try
/*     */     {
/*  80 */       localMonitoredHost = MonitoredHost.getMonitoredHost(new HostIdentifier((String)null));
/*  81 */       localSet = localMonitoredHost.activeVms();
/*     */     } catch (Throwable localThrowable1) {
	Throwable localObject1=null;
/*  83 */       if ((localThrowable1 instanceof ExceptionInInitializerError)) {
/*  84 */         localObject1 = localThrowable1.getCause();
/*     */       }
/*  86 */       if ((localObject1 instanceof ThreadDeath)) {
/*  87 */         throw ((ThreadDeath)localObject1);
/*     */       }
/*  89 */       if ((localObject1 instanceof SecurityException)) {
/*  90 */         return localArrayList;
/*     */       }
/*  92 */       throw new InternalError();
/*     */     }
/*     */     
/*  95 */     for (Object localObject1 = localSet.iterator(); ((Iterator)localObject1).hasNext();) { Object localObject2 = ((Iterator)localObject1).next();
/*  96 */       if ((localObject2 instanceof Integer)) {
/*  97 */         String str1 = localObject2.toString();
/*  98 */         String str2 = str1;
/*  99 */         boolean bool = false;
/* 100 */         MonitoredVm localMonitoredVm = null;
/*     */         try {
/* 102 */           localMonitoredVm = localMonitoredHost.getMonitoredVm(new VmIdentifier(str1));
/*     */           try {
/* 104 */             bool = MonitoredVmUtil.isAttachable(localMonitoredVm);
/*     */             
/* 106 */             str2 = MonitoredVmUtil.commandLine(localMonitoredVm);
/*     */           }
/*     */           catch (Exception localException) {}
/* 109 */           if (bool) {
/* 110 */             localArrayList.add(new HotSpotVirtualMachineDescriptor(this, str1, str2));
/*     */           }
/*     */         } catch (Throwable localThrowable2) {
/* 113 */           if ((localThrowable2 instanceof ThreadDeath)) {
/* 114 */             throw ((ThreadDeath)localThrowable2);
/*     */           }
/*     */         } finally {
/* 117 */           if (localMonitoredVm != null) {
/* 118 */             localMonitoredVm.detach();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 123 */     return localArrayList;
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
/*     */   void testAttachable(String paramString)
/*     */     throws AttachNotSupportedException,Throwable
/*     */   {
/* 140 */     MonitoredVm localMonitoredVm = null;
/*     */     try {
/* 142 */       VmIdentifier localVmIdentifier = new VmIdentifier(paramString);
/* 143 */       MonitoredHost localObject1 = MonitoredHost.getMonitoredHost(localVmIdentifier);
/* 144 */       localMonitoredVm = ((MonitoredHost)localObject1).getMonitoredVm(localVmIdentifier);
/*     */       
/* 146 */       if (MonitoredVmUtil.isAttachable(localMonitoredVm)) {
/*     */         return;
/*     */       }
/*     */     } catch (Throwable localThrowable) {
/*     */       Object localObject1;
/* 151 */       if ((localThrowable instanceof ThreadDeath)) {
/* 152 */         localObject1 = (ThreadDeath)localThrowable;
/* 153 */         throw ((Throwable)localObject1);
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally {
/* 158 */       if (localMonitoredVm != null) {
/* 159 */         localMonitoredVm.detach();
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 164 */     throw new AttachNotSupportedException("The VM does not support the attach mechanism");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static class HotSpotVirtualMachineDescriptor
/*     */     extends VirtualMachineDescriptor
/*     */   {
/*     */     HotSpotVirtualMachineDescriptor(AttachProvider paramAttachProvider, String paramString1, String paramString2)
/*     */     {
/* 176 */       super(paramAttachProvider, paramString2);
/*     */     }
/*     */     
/*     */     public boolean isAttachable() {
/* 180 */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Program Files\Java\jdk1.7.0_76\lib\tools.jar!\sun\tools\attach\HotSpotAttachProvider.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */