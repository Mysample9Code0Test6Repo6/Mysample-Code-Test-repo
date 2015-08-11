package com.incyyte.app.domain;

import java.util.ArrayList;
import java.util.List;

public class AddressList {  
   private List<Address> addresses;  
   private int count;
   private int arraySize;
    
   public AddressList() {
	   addresses = new ArrayList();
   }    
   
   public int count() {
       return count;
   }
   
   public void addItem(Address address) {
	   addresses.add(address);
       count++; 
   }
   
   public void deleteItem(int idx) {
	   addresses.remove(idx);
       --count;
   }
      
   public List<Address> getAddresses() {
	return addresses;
}

public AddressListIterator createIterator() {
       return new InnerIterator();
   }
   
   
   private class InnerIterator implements AddressListIterator {
       private int currentPosition = 0;
       
       private InnerIterator() {}
       
       public void first() {
           currentPosition = 0;
       }
       
       public void next() {
           if (currentPosition < (count)) {
               ++currentPosition;
           }
       }
       
       public boolean isDone() {
           if (currentPosition >= (count)) {
               return true;
           } else {
               return false;
           }
       }
       
       public Address currentItem() {
           return addresses.get(currentPosition);
       }
   }
}