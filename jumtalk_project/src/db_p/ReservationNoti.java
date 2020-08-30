package db_p;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ReservationNoti extends Thread{

   String userID;
   ArrayList<Chatlist2> chatlists;
   ArrayList<Chatlist2> chatlists2;
   boolean close = false;
      
      public ReservationNoti(int kind, String userID) {
         
      this.userID = userID;
      if(kind==1) {
         start();
         
      }
//      ChatListDB.getCHATLIST(userID);
         
      }
      
      @Override
      public void run() {
      
         while(!close) {
            
            try {
               chatlists = ReservationDB.getRESERVATION(userID);

               
               sleep(20000);
             
               chatlists2 = ReservationDB.getRESERVATION(userID);
               
               if(chatlists.size() != chatlists2.size()) {
                
                  
                   int tt = chatlists2.size()-chatlists.size();
                   int cnt = tt;
            
                     
                   
                   for (int i = 0; i < tt; i++) {
                
                     JFrame reschk = new JFrame();
                   
                     
                     String str = chatlists2.get(chatlists2.size()-cnt).chattimestr+ " " + chatlists2.get(chatlists2.size()-cnt).chatmenu+"이 예약되었습니다.   확인해주세요 !";
                     
                     cnt--;
                     
                     JLabel reschk2 = new JLabel(str, JLabel.CENTER);
                     reschk2.setFont(new Font("고딕", Font.BOLD, 20));
                     
                    
                     reschk.add(reschk2);
                     reschk.setBounds(100, 100, 1000, 100);
                     
                     
                     
                     reschk.setVisible(true);
                      
                  }

               }
               Date dd = new Date();
               ArrayList<Chatlist> aa = ChatListDB.getCHATLIST(userID);
               for (Chatlist chatlist : aa) {
                  if((((chatlist.chattime.getTime()-dd.getTime())/1000))/60==4) {
                     JOptionPane.showMessageDialog(null, "채팅시작 5분전 입니다. 채팅을 준비해주세요"
                     +new SimpleDateFormat("hh:mm:ss").format(new Date()));
                  }
                  
               }
               
               
               
            } catch (InterruptedException e) {
               
               e.printStackTrace();
            }
         }
         
         
      }
      

}