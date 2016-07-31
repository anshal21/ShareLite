/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shareit;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static shareit.Share_Panel.Progress;
import static shareit.Share_Panel.Recieve_file;
import static shareit.Share_Panel.Send_file;
import static shareit.Share_Panel.ShareStatus;

/**
 *
 * @author anshal
 */
 
    public class Rec_Thread extends Thread{
        public void run(){
            try {
                Progress.setValue(0);
                ShareStatus.setText("Status: Initiating Recieve..");
        // TODO add your handling code here:
                Recieve_file.setEnabled(false);
                Send_file.setEnabled(false);
        ServerSocket ss=new ServerSocket(10003);
        System.out.println("waiting...");
        Socket rec_socket=ss.accept();
        System.out.println("Connected");
           
                    
          
        DataInputStream dis=new DataInputStream(rec_socket.getInputStream());
        InputStream is=rec_socket.getInputStream();
        //DataOutputStream dos=new DataOutputStream(rec_socket.getOutputStream());
        int number_of_chunk=dis.readInt();
        String file_name=dis.readUTF();
        String folder_location=System.getProperty("user.home")+"/ShareLite";
         byte[] rec_barry=new byte[1024*1024+10];
         int last_chunk_size=dis.readInt();
         int size=1024*1024;
         int nc=number_of_chunk;
        
        ShareStatus.setText("Status: Recieving Data...");
        while(number_of_chunk>0)
        {
                    
                    if(number_of_chunk==1)
                        size=last_chunk_size;
                    System.out.println("read value "+size);
                   // int size=Integer.parseInt(hh);
                    String file_location=System.getProperty("user.home")+"/ShareLite";
                    File fl=new File(file_location);
                    fl.mkdirs();
                    System.out.println("folder Created");
                    
                    file_location=file_location+"/"+file_name+"_"+number_of_chunk;
                    fl=new File(file_location);
                    fl.createNewFile();
                    System.out.println("Size: "+size+"and"+"Path"+file_name);
                    

                    FileOutputStream fos=new FileOutputStream(fl);
                    BufferedOutputStream bos=new BufferedOutputStream(fos);
                    int bytesRead;
                    int current=0;
                            bytesRead = is.read(rec_barry,0,rec_barry.length);
                      current = bytesRead;
                            System.out.println("Byte Recieved"+current);
                          //  Progress.setValue((100*(val_in_prog+current))/tot_size);
                          // dos.writeInt(100*current/(size-10));
                  do {
                     bytesRead =
                        is.read(rec_barry, current, (rec_barry.length-current));
                     if(bytesRead >= 0) current += bytesRead;
                     System.out.println("Byte Recieved"+current+"and limit is "+size);
                      System.out.println("this is for chunk val "+number_of_chunk);
                  //    Progress.setValue((100*(val_in_prog+current))/tot_size);
                     // dos.writeInt(100*current/(size-10));
                  } while(current<size);
                  System.out.println("Byte Recieved"+current);
                // Progress.setValue((100*(val_in_prog+current))/tot_size);
                      //   dos.writeInt(100*current/(size-10));
                  bos.write(rec_barry, 0 , current);
                  bos.flush();
                  System.out.println("File " + fl + " downloaded (" + current + " bytes read)");
                  bos.flush();
                  bos.close();
                  fos.flush();
                  fos.close();
                 //  rec_barry=null;
                   Progress.setValue((100*(nc-number_of_chunk+1))/nc);
                   number_of_chunk--;
        }
        dis.close();
        is.close();
        ShareStatus.setText("Status: Receiving Complete!");
        rec_socket.close();
        ss.close();
        
        System.out.println("Transfer Complete");
        ShareStatus.setText("Status: Initiating Merge..");
       merge_files mf=new merge_files(file_name,folder_location,nc);
        System.out.println("Initiating Merge");
        mf.merge_func();
        ShareStatus.setText("Status: Done Merging :)");
        Recieve_file.setEnabled(true);
        Send_file.setEnabled(true);
    } catch (IOException ex) {
        Logger.getLogger(Share_Panel.class.getName()).log(Level.SEVERE, null, ex);
    }
        }
    }
