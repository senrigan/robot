/*     */ package sun.tools.attach;
/*     */ 
/*     */ import com.sun.tools.attach.AgentLoadException;
/*     */ import com.sun.tools.attach.AttachNotSupportedException;
/*     */ import com.sun.tools.attach.spi.AttachProvider;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WindowsVirtualMachine
/*     */   extends HotSpotVirtualMachine
/*     */ {
/*     */   WindowsVirtualMachine(AttachProvider paramAttachProvider, String paramString)
/*     */     throws AttachNotSupportedException, IOException
/*     */   {
/*  48 */     super(paramAttachProvider, paramString);
/*     */     int i;
/*     */     try
/*     */     {
/*  52 */       i = Integer.parseInt(paramString);
/*     */     } catch (NumberFormatException localNumberFormatException) {
/*  54 */       throw new AttachNotSupportedException("Invalid process identifier");
/*     */     }
/*  56 */     this.hProcess = openProcess(i);
/*     */     
/*     */ 
/*     */ 
/*     */     try
/*     */     {
/*  62 */       enqueue(this.hProcess, stub, null, null, new Object[0]);
/*     */     } catch (IOException localIOException) {
/*  64 */       throw new AttachNotSupportedException(localIOException.getMessage());
/*     */     }
/*     */   }
/*     */   
/*     */   public void detach() throws IOException {
/*  69 */     synchronized (this) {
/*  70 */       if (this.hProcess != -1L) {
/*  71 */         closeProcess(this.hProcess);
/*  72 */         this.hProcess = -1L;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   InputStream execute(String paramString, Object... paramVarArgs)
/*     */     throws AgentLoadException, IOException
/*     */   {
/*  80 */     assert (paramVarArgs.length <= 3);
/*     */     
/*     */ 
/*  83 */     int i = new Random().nextInt();
/*  84 */     String str = "\\\\.\\pipe\\javatool" + i;
/*  85 */     long l = createPipe(str);
/*     */     
/*     */ 
/*     */ 
/*  89 */     if (this.hProcess == -1L) {
/*  90 */       closePipe(l);
/*  91 */       throw new IOException("Detached from target VM");
/*     */     }
/*     */     
/*     */     try
/*     */     {
/*  96 */       enqueue(this.hProcess, stub, paramString, str, paramVarArgs);
/*     */       
/*     */ 
/*     */ 
/* 100 */       connectPipe(l);
/*     */       
/*     */ 
/* 103 */       PipedInputStream localPipedInputStream = new PipedInputStream(l);
/*     */       
/*     */ 
/* 106 */       int j = readInt(localPipedInputStream);
/* 107 */       if (j != 0)
/*     */       {
/* 109 */         if (paramString.equals("load")) {
/* 110 */           throw new AgentLoadException("Failed to load agent library");
/*     */         }
/* 112 */         throw new IOException("Command failed in target VM");
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 117 */       return localPipedInputStream;
/*     */     }
/*     */     catch (IOException localIOException) {
/* 120 */       closePipe(l);
/* 121 */       throw localIOException; } }
/*     */   
/*     */   static native void init();
/*     */   
/*     */   static native byte[] generateStub();
/*     */   
/*     */   static native long openProcess(int paramInt) throws IOException;
/*     */   
/*     */   static native void closeProcess(long paramLong) throws IOException;
/*     */   
/* 131 */   private class PipedInputStream extends InputStream { public PipedInputStream(long paramLong) { this.hPipe = paramLong; }
/*     */     
/*     */     public synchronized int read() throws IOException
/*     */     {
/* 135 */       byte[] arrayOfByte = new byte[1];
/* 136 */       int i = read(arrayOfByte, 0, 1);
/* 137 */       if (i == 1) {
/* 138 */         return arrayOfByte[0] & 0xFF;
/*     */       }
/* 140 */       return -1;
/*     */     }
/*     */     
/*     */     public synchronized int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2) throws IOException
/*     */     {
/* 145 */       if ((paramInt1 < 0) || (paramInt1 > paramArrayOfByte.length) || (paramInt2 < 0) || (paramInt1 + paramInt2 > paramArrayOfByte.length) || (paramInt1 + paramInt2 < 0))
/*     */       {
/* 147 */         throw new IndexOutOfBoundsException(); }
/* 148 */       if (paramInt2 == 0) {
/* 149 */         return 0;
/*     */       }
/* 151 */       return WindowsVirtualMachine.readPipe(this.hPipe, paramArrayOfByte, paramInt1, paramInt2);
/*     */     }
/*     */     
/*     */     public void close() throws IOException {
/* 155 */       if (this.hPipe != -1L) {
/* 156 */         WindowsVirtualMachine.closePipe(this.hPipe);
/* 157 */         this.hPipe = -1L;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */     private long hPipe;
/*     */   }
/*     */   
/*     */ 
/*     */   static native long createPipe(String paramString)
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */   static native void closePipe(long paramLong)
/*     */     throws IOException;
/*     */   
/*     */ 
/*     */   static native void connectPipe(long paramLong)
/*     */     throws IOException;
/*     */   
/*     */   static native int readPipe(long paramLong, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*     */     throws IOException;
/*     */   
/*     */   static native void enqueue(long paramLong, byte[] paramArrayOfByte, String paramString1, String paramString2, Object... paramVarArgs)
/*     */     throws IOException;
/*     */   
/*     */   static
/*     */   {
/* 185 */     System.loadLibrary("attach");
/* 186 */     init(); }
/* 187 */   private static byte[] stub = generateStub();
/*     */   private volatile long hProcess;
/*     */ }


/* Location:              C:\Program Files\Java\jdk1.7.0_76\lib\tools.jar!\sun\tools\attach\WindowsVirtualMachine.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */