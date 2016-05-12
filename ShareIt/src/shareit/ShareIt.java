/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shareit;

import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anshal Dwivedi
 */
public class ShareIt {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            Share_Panel sp=new Share_Panel();
            sp.setVisible(true);
            sp.getContentPane().setBackground( new Color(174, 238, 238) );
        } catch (IOException ex) {
            Logger.getLogger(ShareIt.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
