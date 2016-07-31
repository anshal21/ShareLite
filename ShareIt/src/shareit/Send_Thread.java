/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shareit;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.Math.ceil;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLEngineResult.Status;
import javax.swing.JOptionPane;
import static shareit.Share_Panel.Progress;
import static shareit.Share_Panel.Recieve_file;
import static shareit.Share_Panel.Send_file;
import static shareit.Share_Panel.ShareStatus;

/**
 *
 * @author anshal
 */
public class Send_Thread extends Thread{
    String file_path,rec_ip;
    public Send_Thread(String file_path,String rec_ip)
    {
        this.file_path=file_path;
        this.rec_ip=rec_ip;
    }
        public void run(){
             
            Send_file.setEnabled(false);
            Recieve_file.setEnabled(false);
            int chunk_size=1024*1024; // 10mb chunk
            ShareStatus.setText("Status: Initiating Send..");
         File file_to_send=new File(file_path);
         System.out.println("send Button Active");
            try {
                int l=file_path.length();
                int i=l-1;
                int sizeofFIle=(int) file_to_send.length();
                int number_of_chunk=(int) ceil(sizeofFIle/(1.0*chunk_size));
                
                String file_name=file_to_send.getName();
                FileInputStream fis=new FileInputStream(file_to_send);
                BufferedInputStream bis=new BufferedInputStream(fis);
                int file_size=(int)file_to_send.length();
                byte[] barry=new byte[chunk_size+10];
           
              
                System.out.println("send Button Active level-2");
                Socket s=new Socket(rec_ip, 10003);
                
                DataOutputStream dos=new DataOutputStream(s.getOutputStream());
               DataInputStream dis_send=new DataInputStream(s.getInputStream());
                 OutputStream os=s.getOutputStream();
                System.out.println("send Button Active level-3");
                int last_chunk_size=file_size%chunk_size;
                dos.writeInt(number_of_chunk);
                 dos.writeUTF(file_name);
                  dos.writeInt(last_chunk_size);
                 Progress.setValue(0);
                 int nc=number_of_chunk;
                 ShareStatus.setText("Status: Send in progress..");
                while(number_of_chunk>0){
                            
                               
                            
                              
                                
                                 bis.read(barry, 0,barry.length);
                                os.write(barry,0,barry.length);
                                
                                System.out.println("Done Sending");
                                System.out.println("send Button Active level-4");
                               os.flush();
                               
                               
                               number_of_chunk--;
                         //      barry=null;
                               dos.flush();
                               Progress.setValue((100*(nc-number_of_chunk+1))/nc);
                }
                ShareStatus.setText("Status: Finished Sending:)");
                dos.close();
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Share_Panel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Share_Panel.class.getName()).log(Level.SEVERE, null, ex);
            }
              Send_file.setEnabled(true);
            Recieve_file.setEnabled(true);
        } 
        
    }
