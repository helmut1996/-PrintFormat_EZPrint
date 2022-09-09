package com.example.impresorarp4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Imprimir {
   private StringBuilder sbResultado;
  // private String m ;
    static final String ENCODING = "ISO-8859-1";

    public byte[] Formato1(String m) throws IOException {
            sbResultado = new StringBuilder();
            sbResultado.append("EZ{BACK:30}\n");

            sbResultado.append(String.format("EZ{PRINT:\n"));
            sbResultado.append("@10,10:T,L820,T3|");
            sbResultado.append("@15,250:MF226,VMULT2,HMULT2|"+m+"|");
            sbResultado.append(" @10,10:V,L150,T3|");
            sbResultado.append("@10,827:V,L150,T3|");
            sbResultado.append("  @160,10:T,L823,T3|");
            sbResultado.append(String.format("}{LP}"));

            sbResultado.append("EZ{AHEAD:50}\n");

        return stringToByteArray(sbResultado.toString());
    }





    public byte[] stringToByteArray(String s) throws IOException {

        byte b[] = null;
        ByteArrayOutputStream ByteArray = new ByteArrayOutputStream();
        byte buf[] = s.getBytes(ENCODING);
        ByteArray.write(buf);
        buf = null;
        b = ByteArray.toByteArray();
        return b;

    }
}
