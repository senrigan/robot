package com.sun.tools.hat.internal.model;

public abstract interface JavaHeapObjectVisitor
{
  public abstract void visit(JavaHeapObject paramJavaHeapObject);
  
  public abstract boolean exclude(JavaClass paramJavaClass, JavaField paramJavaField);
  
  public abstract boolean mightExclude();
}


/* Location:              C:\Program Files\Java\jdk1.7.0_76\lib\tools.jar!\com\sun\tools\hat\internal\model\JavaHeapObjectVisitor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */