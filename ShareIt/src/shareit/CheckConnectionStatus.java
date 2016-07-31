/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shareit;

import java.util.logging.Level;
import java.util.logging.Logger;
import static shareit.MV_MainPanel.mobile_socket;

/**
 *
 * @author anshal
 */
public class CheckConnectionStatus extends Thread{
    public void run()
    {
        while(!mobile_socket.isClosed())
        {
            try {
                System.out.println("connection alive");
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(CheckConnectionStatus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Connection Closed");
    }
    
}
