/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shareit;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anshal
 */
public class merge_files
    {
        String file_name;
        String folder_path;
        int number_of_chunk;
        String dest_file_path;
        public merge_files(String file_name,String folder_path,int number_of_chunk)
        {
            this.file_name=file_name;
            this.folder_path=folder_path;
            this.number_of_chunk=number_of_chunk;
        }
        public void merge_func() throws FileNotFoundException
        {
            try {
                dest_file_path=folder_path+"/"+file_name;
                File dest_file=new File(dest_file_path);
                FileOutputStream fos=new FileOutputStream(dest_file);
                BufferedOutputStream bos=new BufferedOutputStream(fos);
                byte[] byte_array=new byte[1024*1024+10];
                while(number_of_chunk>0)
                {
                    String File_Path=folder_path+"/"+file_name+"_"+number_of_chunk;
                    File src_file=new File(File_Path);
                    FileInputStream fis;
                    try {
                        fis = new FileInputStream(src_file);
                        BufferedInputStream bis=new BufferedInputStream(fis);
                        bis.read(byte_array,0, (int) src_file.length());
                        bos.write(byte_array);
                     //   byte_array=null;
                        bis.close();
                        boolean delete = src_file.delete();
                        number_of_chunk--;
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Share_Panel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Share_Panel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                bos.close();              
                fos.close();
                System.out.println("Done Merging");
            } catch (IOException ex) {
                Logger.getLogger(Share_Panel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
