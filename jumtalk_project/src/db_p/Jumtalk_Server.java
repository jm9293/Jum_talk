package db_p;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.Collections;
import java.util.HashMap;






public class Jumtalk_Server {
   
	HashMap<String, ObjectOutputStream> usermap;
   
   public Jumtalk_Server() {
	  usermap = new HashMap<String, ObjectOutputStream>();
      
      Collections.synchronizedMap(usermap);
      
      try {
         ServerSocket server = new ServerSocket(7777);   
         
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
            System.out.println(name);
            dos = new ObjectOutputStream(client.getOutputStream());
            dis = new ObjectInputStream(client.getInputStream());
            name = dis.readUTF();
            
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
					
				} catch (Exception e) {
					
				}
            	
            	sendToClass(contant);
				
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
   
   }

   void sendToClass(Content co) {
      String to_id = co.to_id;
      String from_id = co.from_id;
      try {
    	ObjectOutputStream from = usermap.get(from_id);
		from.writeObject(co);
		System.out.println(usermap);
		ObjectOutputStream to = usermap.get(to_id);
		if(!(to==null)) {
		to.writeObject(co);  
		}else {
			
		}
		System.out.println("보냇음");
	} catch (IOException e) {
		// TODO 자동 생성된 catch 블록
		e.printStackTrace();
	};      
         
      
   }
   
   
   

   public static void main(String[] args) {
      
      
      new Jumtalk_Server();

   }

}