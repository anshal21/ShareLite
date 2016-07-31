/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shareit;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static shareit.MV_MainPanel.mobile_server_socket;
import static shareit.MV_MainPanel.mobile_socket;
import static shareit.MV_MainPanel.stable;
/**
 *
 * @author anshal
 */
  public class MakeConnection extends Thread {
        public void run()
        {
            try {
                
                mobile_server_socket=new ServerSocket(2000);
                   mobile_socket=mobile_server_socket.accept();
                   stable=true;
                   while(true)
                   {
                       System.out.println("I m here");
                       sleep(1000);
                   }
                   
            } catch (IOException ex) {
                Logger.getLogger(MV_MainPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(MakeConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        }
    }
