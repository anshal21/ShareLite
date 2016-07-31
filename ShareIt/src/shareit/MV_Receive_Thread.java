/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shareit;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import static shareit.MV_MainPanel.mobile_socket;
import static shareit.MV_SharePanel.mdis;
import static shareit.MV_SharePanel.mis;

/**
 *
 * @author anshal
 */
public class MV_Receive_Thread extends Thread {
    DataInputStream dis;
    InputStream is;
    OutputStream os;
    public void run()
    {   
        try {
        //    dis=new DataInputStream(mobile_socket.getInputStream());
            String file_name=mdis.readUTF();
            System.out.println("File to be received is : "+file_name);
         //   InputStream is=mobile_socket.getInputStream();
            File nf=new File(System.getProperty("user.home")+"/ShareLite/"+file_name);
            OutputStream os=new FileOutputStream(nf);
            byte[] buffer=new byte[1024*1024*10];
            int len=0;
            while((len=mis.read(buffer))>0)
            {
                os.write(buffer,0,len);
            }
         //   is.close();
            os.close();
            //is.close();
           // mis.close();
           
            
        } catch (IOException ex) {
            Logger.getLogger(MV_Receive_Thread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
    
    }
    
}
