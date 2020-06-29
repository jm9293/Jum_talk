package db_p;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.Collections;
import java.util.HashMap;






public class Jumtalk_Server2 {
   
	HashMap<String, ObjectOutputStream> usermap;
   
   public Jumtalk_Server2() {
	  usermap = new HashMap<String, ObjectOutputStream>();
      
      Collections.synchronizedMap(usermap);
      
      try {
         ServerSocket server = new ServerSocket(8888);   
         
         System.out.println("서버시작");
         
         while(true) {
            Socket client = server.accept();
            System.out.println("연결성공");
            new MulReceiver(client).start();
         }
         
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   
   }
   
   class MulReceiver extends Thread{
      
      String name;
      ObjectOutputStream dos;
      ObjectInputStream dis;
      
      public MulReceiver(Socket client) {
         
         try {
            System.out.println(client.getInetAddress().toString());
           
            dos = new ObjectOutputStream(client.getOutputStream());
            dis = new ObjectInputStream(client.getInputStream());
            
            
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         
      }
      
      
      @Override
      public void run() {
         // TODO Auto-generated method stub
         
         
         try {
            
            usermap.put(name, dos);
            
            while(dis!=null) {
            	
            try {
            	Content contant=null;
            	try {
            		
            		contant = (Content) dis.readObject();
					System.out.println("받았어");
				} catch (Exception e) {
					
				}
            	
            	if(contant.kind.equals("upload")) {
            		upload(contant);
            		System.out.println("올린다");
            	}else if(contant.kind.equals("download")){
            		download(contant);
            		System.out.println("다운로드");
            	}
            	
            	
				
			} catch (Exception e) {
				// TODO: handle exception
			}
            }

         } catch (Exception e) {
           e.printStackTrace();
         }finally {
            
            usermap.remove(dos);
            
            
            try {
               dis.close();
               dos.close();
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
            
         }   
      }
   
      void upload(Content co) {
    	  try {
    		  FileOutputStream fio = new FileOutputStream("icon\\"+co.filename);
    		  fio.write(co.filebyte);
    		  fio.close();
    	  } catch (Exception e) {
    		  e.printStackTrace();
    	  }
      }
      
      void download(Content co) {
    	  try {
    		  File file = new File("icon\\"+co.filename);
    		  byte[]  buf;
    		  if(!file.exists()) {
    			  FileInputStream fis = new FileInputStream("icon\\smile.png");
    			  buf = new byte[fis.available()];
    			  fis.read(buf);
    			  fis.close();
    			  System.out.println("없어");
    		  }else {
    			  FileInputStream fis = new FileInputStream(file);
    			  buf = new byte[fis.available()];
    			  fis.read(buf);
    			  fis.close();
    			  System.out.println("있어");
    		  }
    		  dos.writeObject(new Content("", "", "", "", buf));
    		  
    		  
    	  } catch (Exception e) {
    		  e.printStackTrace();
    	  }
    	  
      }
   }

   
   
   

   public static void main(String[] args) {
      
      
      new Jumtalk_Server2();

   }

}