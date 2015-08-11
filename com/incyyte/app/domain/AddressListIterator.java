package com.incyyte.app.domain;

public interface AddressListIterator {
   public void first();
   public void next();  
   public boolean isDone();
   public Address currentItem();
}
