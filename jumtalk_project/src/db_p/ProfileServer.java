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

import javax.swing.JOptionPane;


public class ProfileServer {
   
   public ProfileServer() {
	
      
     
      
      try {
         ServerSocket server = new ServerSocket(8888);   
         
         System.out.println("프로필 사진 인아웃 서버시작");
         
         while(true) {
            Socket client = server.accept();
            System.out.println("연결성공");
  
            MulReceiver mulReceiver =  new MulReceiver(client);
          
            mulReceiver.start();
           
         }
         
      } catch (Exception e) {
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
        	
            while(dis!=null) {
            try {
            	LetterClass contant=null;
            	try {
            		
            		contant = (LetterClass) dis.readObject();
					System.out.println("클라이언트로부터 요청 수신");
				} catch (Exception e) {
					
				}
            	
            	if(contant.kind.equals("upload")) {
            		System.out.println(name +" : "+ contant.to_id+" 프로필 사진 업로드 요청");
            		upload(contant);
            		System.out.println(name +" : "+ contant.to_id+" 프로필 사진 업로드 완료");
            	}else if(contant.kind.equals("download")){
            		System.out.println(name +" : "+ contant.to_id+" 프로필 다운로드  요청");
            		download(contant);
            		System.out.println(name +" : "+ contant.to_id+" 프로필 다운로드 완료");
            	}else if(contant.kind.equals("login")) {
            		name=contant.from_id;
            		System.out.println(contant.from_id+" : 로그인");
            	}else if(contant.kind.equals("logout")) {
            		
            		break;
            	}
            	
            	
				
			} catch (Exception e) {
				// TODO: handle exception
			}
            }

         } catch (Exception e) {
         }finally {
   
            
            try {
            	UserDB.setLOGINCHK(name, "false");
            	System.out.println(name+ " : 로그아웃");
               dis.close();
               dos.close();
            } catch (IOException e) {
              
            }
            
         }   
      }
   
      void upload(LetterClass co) {
    	  try {
    		  FileOutputStream fio = new FileOutputStream("icon\\"+co.filename);
    		  System.out.println(co.filename+" 저장 완료");
    		  fio.write(co.filebyte);
    		  fio.close();
    	  } catch (Exception e) {
    		  e.printStackTrace();
    	  }
      }
      
      void download(LetterClass co) {
    	  try {
    		  File file = new File("icon\\"+co.filename);
    		  byte[]  buf;
    		  if(!file.exists()) {
    			  FileInputStream fis = new FileInputStream("icon\\smile.png");
    			  System.out.println("저장되어 있는 사진이 없어 smile.png 전송");
    			  buf = new byte[fis.available()];
    			  fis.read(buf);
    			  fis.close();
    		  }else {
    			  FileInputStream fis = new FileInputStream(file);
    			  System.out.println("저장되어 있는 사진이 있어서 "+co.filename+" 전송");
    			  buf = new byte[fis.available()];
    			  fis.read(buf);
    			  fis.close();
    			  
    		  }
    		  dos.writeObject(new LetterClass("", "", "", "", buf));
    		  
    		  
    	  } catch (Exception e) {
    		  e.printStackTrace();
    	  }
    	  
      }
      
      
      
      
      
   }
   


   public static void main(String[] args) {
      
      
      new ProfileServer();

   }

}