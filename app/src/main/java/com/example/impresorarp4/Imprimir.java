package com.example.impresorarp4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Imprimir {
   private StringBuilder sbResultado;
  // private String m ;
    static final String ENCODING = "ISO-8859-1";


    /*TODO variables para formatos de la impresora */
        private static final String MEN_OSB_14 = "MEN_OSB 14";
    private static final String MEN_OSB_11 = "MEN_OSB 11";
    private static final String MEN_OSB_7 = "MEN_OSB 7";
    private static final String MEN_OSB_6 = "MEN_OSB 6";
    private static final String MEN_OSB_9 = "MEN_OSB 9";
    private static final String MEN_OSB_0 = "MEN_OSB 0";
    private static final String MEN_OSB1 = "MEN_OSB1";
    private static final String MEN_OSB_20 = "MEN_OSB 20";
    private static final String MEN_OSB_21 = "MEN_OSB 21";
    private static final String MEN_OSB_22 = "MEN_OSB_22";
    private static final String MEN_OSB_23 = "MEN_OSB_23";
    private static final String MEN_OSB_24 = "MEN_OSB_24";
    private static final String MEN_OSB_25 = "MEN_OSB_25";
    private static final String MSJ_ESTIM_OSB = "MSJ_ESTIM_OSB";
    private static final String MEN_RESTRICCION_1 = "REST_OSB1";
    private static final String MEN_RESTRICCION_2 = "REST_OSB2";

    static final String FONT = "MF204";
    static final String FONTMED = "MF204,HMULT2,VMULT2";
    static final String FONTSMALL = "MF226";
    static String FONTSMALL2 = "PT05T";
    static final String FONTLARGE = "MF055";
    static final String FONTBOLD = "MF107";
    static final String FONTBOLD2 = "MF185";
    static final String FONTINVERSE = "MF204,HMULT2,VMULT2,INVERSE";
    static final String FONTINVERSE2 = "MF107,INVERSE";
    static final String FONTBOLD3 = "MF185";
    static final String FONTINVERSE3 = "MF204,INVERSE";
    static final String BARCODE = "EN128, HIGH 12, WIDE 2";
    static final String SEPARADOR = "=========================================================================";
    static final String SEPARADOR_ = "________________________________________________________________________________";
    static final String LN = "|";
    static final String ESC = "";




    public byte[] Formato1(String m) throws IOException {
            sbResultado = new StringBuilder();
            /*
            sbResultado.append("EZ{BACK:30}\n");

            sbResultado.append(String.format("EZ{PRINT:\n"));
            sbResultado.append("@10,10:T,L820,T3|");
            sbResultado.append("@15,250:MF226,VMULT2,HMULT2|"+m+"|");
            sbResultado.append(" @10,10:V,L150,T3|");
            sbResultado.append("@10,827:V,L150,T3|");
            sbResultado.append("  @160,10:T,L823,T3|");
            sbResultado.append(String.format("}{LP}"));

            sbResultado.append("EZ{AHEAD:50}\n");

             */
        sbResultado.append(String.format("EZ{PRINT:\n"));
        sbResultado.append("@160,0:MF107|Cliente:|");
        sbResultado.append("@160,150:MF107|Iglesia maria Guadal|");
        sbResultado.append(" @160,535:MF107|OFI.com|");
        sbResultado.append("@160,680:MF107|yaponCAEES|");
        sbResultado.append("@185,0:MF107|Direccion:|");
        sbResultado.append("@185,150:MF107|Sum pj t sur 41 No 7|");
        sbResultado.append(" @185,535:MF107|Tarifa|");
        sbResultado.append(" @185,660:MF107|R-Recidencial|");
        sbResultado.append("}");

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
