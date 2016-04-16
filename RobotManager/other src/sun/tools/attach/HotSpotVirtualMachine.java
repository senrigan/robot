/*     */ package sun.tools.attach;
/*     */ 
/*     */ import com.sun.tools.attach.AgentInitializationException;
/*     */ import com.sun.tools.attach.AgentLoadException;
/*     */ import com.sun.tools.attach.VirtualMachine;
/*     */ import com.sun.tools.attach.spi.AttachProvider;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
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
/*     */ public abstract class HotSpotVirtualMachine
/*     */   extends VirtualMachine
/*     */ {
/*     */   private static final int JNI_ENOMEM = -4;
/*     */   private static final int ATTACH_ERROR_BADJAR = 100;
/*     */   private static final int ATTACH_ERROR_NOTONCP = 101;
/*     */   private static final int ATTACH_ERROR_STARTFAIL = 102;
/*     */   
/*     */   HotSpotVirtualMachine(AttachProvider paramAttachProvider, String paramString)
/*     */   {
/*  45 */     super(paramAttachProvider, paramString);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void loadAgentLibrary(String paramString1, boolean paramBoolean, String paramString2)
/*     */     throws AgentLoadException, AgentInitializationException, IOException
/*     */   {
/*  58 */     InputStream localInputStream = execute("load", new Object[] { paramString1, paramBoolean ? "true" : "false", paramString2 });
/*     */     
/*     */ 
/*     */     try
/*     */     {
/*  63 */       int i = readInt(localInputStream);
/*  64 */       if (i != 0) {
/*  65 */         throw new AgentInitializationException("Agent_OnAttach failed", i);
/*     */       }
/*     */     } finally {
/*  68 */       localInputStream.close();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void loadAgentLibrary(String paramString1, String paramString2)
/*     */     throws AgentLoadException, AgentInitializationException, IOException
/*     */   {
/*  79 */     loadAgentLibrary(paramString1, false, paramString2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void loadAgentPath(String paramString1, String paramString2)
/*     */     throws AgentLoadException, AgentInitializationException, IOException
/*     */   {
/*  88 */     loadAgentLibrary(paramString1, true, paramString2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void loadAgent(String paramString1, String paramString2)
/*     */     throws AgentLoadException, AgentInitializationException, IOException
/*     */   {
/*  98 */     String str = paramString1;
/*  99 */     if (paramString2 != null)
/* 100 */       str = str + "=" + paramString2;
/*     */     int i;
/*     */     try {
/* 103 */       loadAgentLibrary("instrument", str);
/*     */     } catch (AgentLoadException localAgentLoadException) {
/* 105 */       throw new InternalError("instrument library is missing in target VM");
/*     */ 
/*     */ 
/*     */     }
/*     */     catch (AgentInitializationException localAgentInitializationException)
/*     */     {
/*     */ 
/* 112 */       i = localAgentInitializationException.returnValue();
/* 113 */       switch (i) {
/*     */       case -4: 
/* 115 */         throw new AgentLoadException("Insuffient memory"); }
/*     */     }
/* 117 */     throw new AgentLoadException("Agent JAR not found or no Agent-Class attribute");   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Properties getSystemProperties()
/*     */     throws IOException
/*     */   {
/* 141 */     InputStream localInputStream = null;
/* 142 */     Properties localProperties = new Properties();
/*     */     try {
/* 144 */       localInputStream = executeCommand("properties", new Object[0]);
/* 145 */       localProperties.load(localInputStream);
/*     */     } finally {
/* 147 */       if (localInputStream != null) localInputStream.close();
/*     */     }
/* 149 */     return localProperties;
/*     */   }
/*     */   
/*     */   public Properties getAgentProperties() throws IOException {
/* 153 */     InputStream localInputStream = null;
/* 154 */     Properties localProperties = new Properties();
/*     */     try {
/* 156 */       localInputStream = executeCommand("agentProperties", new Object[0]);
/* 157 */       localProperties.load(localInputStream);
/*     */     } finally {
/* 159 */       if (localInputStream != null) localInputStream.close();
/*     */     }
/* 161 */     return localProperties;
/*     */   }
/*     */   
/*     */ 
/*     */   public void localDataDump()
/*     */     throws IOException
/*     */   {
/* 168 */     executeCommand("datadump", new Object[0]).close();
/*     */   }
/*     */   
/*     */   public InputStream remoteDataDump(Object... paramVarArgs)
/*     */     throws IOException
/*     */   {
/* 174 */     return executeCommand("threaddump", paramVarArgs);
/*     */   }
/*     */   
/*     */   public InputStream dumpHeap(Object... paramVarArgs)
/*     */     throws IOException
/*     */   {
/* 180 */     return executeCommand("dumpheap", paramVarArgs);
/*     */   }
/*     */   
/*     */   public InputStream heapHisto(Object... paramVarArgs) throws IOException
/*     */   {
/* 185 */     return executeCommand("inspectheap", paramVarArgs);
/*     */   }
/*     */   
/*     */   public InputStream setFlag(String paramString1, String paramString2) throws IOException
/*     */   {
/* 190 */     return executeCommand("setflag", new Object[] { paramString1, paramString2 });
/*     */   }
/*     */   
/*     */   public InputStream printFlag(String paramString) throws IOException
/*     */   {
/* 195 */     return executeCommand("printflag", new Object[] { paramString });
/*     */   }
/*     */   
/*     */   public InputStream executeJCmd(String paramString) throws IOException {
/* 199 */     return executeCommand("jcmd", new Object[] { paramString });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   abstract InputStream execute(String paramString, Object... paramVarArgs)
/*     */     throws AgentLoadException, IOException;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private InputStream executeCommand(String paramString, Object... paramVarArgs)
/*     */     throws IOException
/*     */   {
/*     */     try
/*     */     {
/* 217 */       return execute(paramString, paramVarArgs);
/*     */     } catch (AgentLoadException localAgentLoadException) {
/* 219 */       throw new InternalError("Should not get here");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   int readInt(InputStream paramInputStream)
/*     */     throws IOException
/*     */   {
/* 230 */     StringBuilder localStringBuilder = new StringBuilder();
/*     */     
/*     */ 
/*     */ 
/* 234 */     byte[] arrayOfByte = new byte[1];
/*     */     int i;
/* 236 */     do { i = paramInputStream.read(arrayOfByte, 0, 1);
/* 237 */       if (i > 0) {
/* 238 */         char c = (char)arrayOfByte[0];
/* 239 */         if (c == '\n') {
/*     */           break;
/*     */         }
/* 242 */         localStringBuilder.append(c);
/*     */       }
/*     */       
/* 245 */     } while (i > 0);
/*     */     
/* 247 */     if (localStringBuilder.length() == 0) {
/* 248 */       throw new IOException("Premature EOF");
/*     */     }
/*     */     int j;
/*     */     try
/*     */     {
/* 253 */       j = Integer.parseInt(localStringBuilder.toString());
/*     */     } catch (NumberFormatException localNumberFormatException) {
/* 255 */       throw new IOException("Non-numeric value found - int expected");
/*     */     }
/* 257 */     return j;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/* 262 */   private static long defaultAttachTimeout = 5000L;
/*     */   
/*     */ 
/*     */   private volatile long attachTimeout;
/*     */   
/*     */ 
/*     */ 
/*     */   long attachTimeout()
/*     */   {
/* 271 */     if (this.attachTimeout == 0L) {
/* 272 */       synchronized (this) {
/* 273 */         if (this.attachTimeout == 0L) {
/*     */           try {
/* 275 */             String str = System.getProperty("sun.tools.attach.attachTimeout");
/*     */             
/* 277 */             this.attachTimeout = Long.parseLong(str);
/*     */           }
/*     */           catch (SecurityException localSecurityException) {}catch (NumberFormatException localNumberFormatException) {}
/*     */           
/* 281 */           if (this.attachTimeout <= 0L) {
/* 282 */             this.attachTimeout = defaultAttachTimeout;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 287 */     return this.attachTimeout;
/*     */   }
/*     */ }


/* Location:              C:\Program Files\Java\jdk1.7.0_76\lib\tools.jar!\sun\tools\attach\HotSpotVirtualMachine.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */