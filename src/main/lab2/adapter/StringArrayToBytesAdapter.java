package main.lab2.adapter;

import java.io.IOException;
import java.io.OutputStream;

public class StringArrayToBytesAdapter {
   private OutputStream outputStream;

   public StringArrayToBytesAdapter(OutputStream outputStream){
       this.outputStream = outputStream;
   }

   public void adapt(String[] strings){
       for (String string :
               strings) {
           try {
               this.outputStream.write((string + '\n').getBytes());
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }
}
