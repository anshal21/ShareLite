/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shareit;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import static shareit.MV_MainPanel.mobile_socket;
import static shareit.MV_SharePanel.File_path;
import static shareit.MV_SharePanel.mdos;
import static shareit.MV_SharePanel.mos;

/**
 *
 * @author anshal
 */
public class MV_Send_Thread extends Thread{
    OutputStream os;
    InputStream is;
    DataOutputStream dos;
    public void run(){
        if(mobile_socket.isClosed())
        {
            System.out.println("Socket has been closed :(");
        }
        else{
        File fl=new File(File_path);
        try {
           // dos=new DataOutputStream(mobile_socket.getOutputStream());
            is=new FileInputStream(fl);
            //os=mobile_socket.getOutputStream();
            mdos.writeUTF(fl.getName());
            byte[] buffer=new byte[1024*1024*10];
            int len=0;
            while((len=is.read(buffer))>0)
            {
                mos.write(buffer,0,len);
            }
            is.close();
            //os.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MV_Send_Thread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MV_Send_Thread.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }
    
    
}
