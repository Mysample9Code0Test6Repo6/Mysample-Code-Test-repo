package com.incyyte.app.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.incyyte.app.service.Exceptions.SystemUnavailableException;

import sun.misc.BASE64Encoder;
import sun.misc.CharacterEncoder;

public final class PasswordService{
  private static PasswordService instance;
  private PasswordService(){
  }

  public synchronized String encrypt(String plaintext) throws SystemUnavailableException{
    MessageDigest md = null;
    
    try{
      md = MessageDigest.getInstance("SHA"); 
    }catch(NoSuchAlgorithmException e){
      throw new SystemUnavailableException(e.getMessage());
    }
    
    try{
      md.update(plaintext.getBytes("UTF-8"));
    }catch(UnsupportedEncodingException e){
      throw new SystemUnavailableException(e.getMessage());
    }
    
    byte raw[] = md.digest();
    String hash = (new BASE64Encoder()).encode(raw);
    return hash;
  }
  
  public static synchronized PasswordService getInstance(){
    if(instance == null){
      return new PasswordService();
    }else{
      return instance;
    }
  }
}

