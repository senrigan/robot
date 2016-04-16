/*    */ package com.sun.tools.attach;
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
/*    */ public class AgentInitializationException
/*    */   extends Exception
/*    */ {
/*    */   static final long serialVersionUID = -1508756333332806353L;
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
/*    */   private int returnValue;
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
/*    */   public AgentInitializationException()
/*    */   {
/* 56 */     returnValue = 0;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public AgentInitializationException(String paramString)
/*    */   {
/* 66 */     super(paramString);
/* 67 */     returnValue = 0;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public AgentInitializationException(String paramString, int paramInt)
/*    */   {
/* 79 */     super(paramString);
/* 80 */     returnValue = paramInt;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int returnValue()
/*    */   {
/* 91 */     return returnValue;
/*    */   }
/*    */ }

/* Location:           C:\Program Files\Java\jdk1.7.0_76\lib\tools.jar
 * Qualified Name:     com.sun.tools.attach.AgentInitializationException
 * Java Class Version: 7 (51.0)
 * JD-Core Version:    0.7.1
 */