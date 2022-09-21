package com.example.impresorarp4;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import datamaxoneil.printer.DocumentLP;

public class Imprimir {
    static final String ENCODING = "ISO-8859-1";

    String caracteresEsp = " {} & ? [] @";
            /*(" &" + "/"+ "()" +"="+ "?"+ "" +"' "+" ;"+
    " `"+" +"+"*" + " {}"+"[]" +"~ @"+" \\\\  \\ | ¬ # $"+" % ° ");


             */

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
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Coordenadas
    String x;
    int y;

    ArrayList<String> cargoVarios;



    public byte[] Formato1(String m) throws IOException {
        StringBuilder sbResultado = new StringBuilder();

        cargoVarios = new ArrayList<>();
        cargoVarios.add("Cargo vario 1");
        cargoVarios.add("Cargo vario 2");
        cargoVarios.add("Cargo vario 3");
        cargoVarios.add("Cargo vario 4");
        cargoVarios.add("Cargo vario 5");



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

        /*TODO PRIMERA PARTE DE LA FACTURA EOO*/
        sbResultado.append(String.format(ESC+"EZ{PRINT:\n"));
        sbResultado.append("@"+ "0,140:"+FONTSMALL+LN+"CLIENTE"+LN);
        sbResultado.append("@"+"0,210:"+FONTSMALL+LN+"SOLORZANO TORREZ ATILI OFI.COM"+LN);
        sbResultado.append("@"+"0,580:"+FONTSMALL+LN+"CIAC SN MIGUEL EEO"+LN);
        sbResultado.append("@"+"20,140:"+FONTSMALL+LN+"DIRECCION"+LN);
        sbResultado.append("@"+"20,240:"+FONTSMALL+LN+"SUM 100MTS AL OTE ESCUELACION EL AMATE"+LN);
        sbResultado.append( "@"+"20,590:"+FONTSMALL+LN+"TARIFA: R - Residencial EEO"+LN);
        sbResultado.append("@"+"40,140:"+FONTSMALL+LN+"REFERENCIA DIR 100 MIS AL OTE DE ES "+LN);
        sbResultado.append("@"+"40,580:"+FONTSMALL+LN+"SEC RUTA  31136 25 146"+LN);
        sbResultado.append("@"+"80,580:"+FONTSMALL+LN+"FECHA EMISION 09/09/2022 "+LN);
        sbResultado.append("@"+"60,140:"+FONTSMALL+LN+"DIRECCION C CUELACION EL AMATE"+LN);
        sbResultado.append("@"+"60,580:"+FONTSMALL+LN+"MEDIDOR 96902201"+LN);
        sbResultado.append("@"+"80,140:"+FONTBOLD2+LN+"NIC  3156911"+LN);
        sbResultado.append("@"+"100,580:"+FONTSMALL+LN+"FACTURAS PENDIEN0TES"+LN);
        sbResultado.append("@"+"120,580:"+FONTBOLD+LN+"CON SUBCIDIO"+LN);
        /*TODO PRIMERA PARTE DE LA FACTURA EOO*/

        /*TODO SEGUNDA PARTE DE LA FACTURA EOO*/
        sbResultado.append("@"+"220,10:"+FONTSMALL+LN+"DESDE  12/08/2022"+LN);
        sbResultado.append("@"+"220,230:"+FONTSMALL+LN+"TOTAL"+LN);
        sbResultado.append("@"+"220,430:"+FONTSMALL+LN+"54.89"+LN);
        sbResultado.append("@"+"220,600:"+FONTSMALL+LN+"NUMERO  62366731"+LN);
        sbResultado.append("@"+"240,10:"+FONTSMALL+LN+"HASTA  12/08/2022"+LN);
        sbResultado.append("@"+"240,230:"+FONTSMALL+LN+"TOTAL OTROS SERV"+LN);
        sbResultado.append("@"+"240,430:"+FONTSMALL+LN+"00.00"+LN);
        sbResultado.append("@"+"240,590:"+FONTSMALL+LN+"REGISTROS  No 321 - 2"+LN);
        sbResultado.append("@"+"260,0:"+FONTSMALL+LN+"DIAS FACTURADOS  31"+LN);
        sbResultado.append("@"+"280,0:"+FONTSMALL+LN+"MES FACTURADO 09/05/2022"+LN);
        sbResultado.append("@"+"280,220:"+FONTSMALL+LN+"ALCALDIA"+LN);
        sbResultado.append("@"+"280,420:"+FONTSMALL+LN+"00.00"+LN);
        sbResultado.append("@"+"280,580:"+FONTSMALL+LN+"NIT 6614 161195 1030"+LN);
        sbResultado.append("@"+"300,0:"+FONTSMALL+LN+"FECHA EMISION  31/05/2022"+LN);
        sbResultado.append("@"+"300,520:"+FONTSMALL+LN+"GIRO Luz y Fuerza Electrica"+LN);
        sbResultado.append("@"+"320,0:"+FONTSMALL+LN+"CONSUMO KW 134.50"+LN);
        sbResultado.append("@"+"320,220:"+FONTSMALL+LN+"TOTAL A PAGAR"+LN);
        sbResultado.append("@"+"320,420:"+FONTSMALL+LN+"00.00"+LN);
        sbResultado.append("@"+"320,560:"+FONTSMALL+LN+"ULTIMA FECHA DE PAGO"+LN);
        sbResultado.append("@"+"340,560:"+FONTBOLD+LN+"20/05/2022"+LN);
        /*TODO SEGUNDA PARTE DE LA FACTURA EOO*/

        /*TODO TERCERA PARTE DE LA FACTURA EOO*/
        sbResultado.append("@"+"400,0:"+FONTSMALL+LN+"INICIO"+LN);
        sbResultado.append("@"+"400,60:"+FONTSMALL+LN+"2/05/2022"+LN);
        sbResultado.append("@"+"400,160:"+FONTSMALL+LN+"SIN HORARIO"+LN);
        sbResultado.append("@"+"400,280:"+FONTSMALL+LN+"LECTURA"+LN);
        sbResultado.append("@"+"420,280:"+FONTSMALL+LN+"ACTUAL"+LN);
        sbResultado.append("@"+"440,280:"+FONTSMALL+LN+"916.0"+LN);
        sbResultado.append("@"+"400,420:"+FONTSMALL+LN+"LECTURA"+LN);
        sbResultado.append("@"+"420,420:"+FONTSMALL+LN+"ANTERIOR"+LN);
        sbResultado.append("@"+"440,420:"+FONTSMALL+LN+"842.0"+LN);
        sbResultado.append("@"+"420,0:"+FONTSMALL+LN+"FINAL"+LN);
        sbResultado.append("@"+"420,60:"+FONTSMALL+LN+"2/05/2022"+LN);
        sbResultado.append("@"+"440,0:"+FONTSMALL+LN+"ENERGIA"+LN);
        sbResultado.append("@"+"440,80:"+FONTSMALL+LN+"0.5236"+LN);
        sbResultado.append("@"+"400,520:"+FONTSMALL+LN+"CAP.LEIDA"+LN);
        sbResultado.append("@"+"420,520:"+FONTSMALL+LN+"CONSUMO"+LN);
        sbResultado.append("@"+"440,520:"+FONTSMALL+LN+"842.0"+LN);
        sbResultado.append("@"+"400,640:"+FONTSMALL+LN+"ACTUAL ANTERIO"+LN);
        sbResultado.append("@"+"420,640:"+FONTSMALL+LN+"CONSUMO"+LN);
        sbResultado.append("@"+"440,640:"+FONTSMALL+LN+"842.0"+LN);
        /*TODO TERCERA PARTE DE LA FACTURA EOO*/

        /*TODO CUARTA PARTE DE LA FACTURA EOO*/
        sbResultado.append("@"+"600,0:"+FONTBOLD2+LN+"CAL.CONSUMO"+LN);
        sbResultado.append("@"+"620,0:"+FONTSMALL+LN+"Energia BT"+LN);
        sbResultado.append("@"+"600,150:"+FONTSMALL+LN+"MEDIDOR"+LN);
        sbResultado.append("@"+"620,150:"+FONTSMALL+LN+"5411245"+LN);
        sbResultado.append("@"+"600,250:"+FONTSMALL+LN+"MULT"+LN);
        sbResultado.append("@"+"620,250:"+FONTSMALL+LN+"MF226|0.00"+LN);
        sbResultado.append("@"+"600,320:"+FONTSMALL+LN+"TIPO MEDIDOR"+LN);
        sbResultado.append("@"+"620,320:"+FONTSMALL+LN+"KWH 0.00"+LN);
        sbResultado.append("@"+"600,460:"+FONTSMALL+LN+"CAP"+LN);
        sbResultado.append("@"+"620,460:"+FONTSMALL+LN+"CONTRATADA"+LN);
        sbResultado.append("@"+"640,460:"+FONTSMALL+LN+"10"+LN);
        sbResultado.append("@"+"600,580:"+FONTSMALL+LN+"CAP"+LN);
        sbResultado.append("@"+"620,580:"+FONTSMALL+LN+"FACTURADA"+LN);
        sbResultado.append("@"+"640,580:"+FONTSMALL+LN+"10"+LN);
        sbResultado.append("@"+"600,690:"+FONTSMALL+LN+"MF226|CAP"+LN);
        sbResultado.append("@"+"620,690:"+FONTSMALL+LN+"LEIDA"+LN);
        sbResultado.append("@"+"640,690:"+FONTSMALL+LN+"10"+LN);
        /*TODO CUARTA PARTE DE LA FACTURA EOO*/

        /*TODO QUINTA PARTE GAFICOS DE LA FACTURA EOO*/

        sbResultado.append("@"+"920,10:"+FONTSMALL+LN+"MES"+LN);
        sbResultado.append("@"+"940,10:"+FONTSMALL+LN+"CONSUMO"+LN);
        sbResultado.append("@"+"960,10:"+FONTSMALL+LN+"CONSUMO PROMEDIO KWH 105"+LN);
        sbResultado.append("@"+"1050,10:"+FONTSMALL+LN+"ALCALDIA (C)"+LN);
        sbResultado.append("@"+"1280,10:"+FONTSMALL+LN+"DETALLE TASAS MUNICIPALES IMPORTE"+LN);




        sbResultado.append("@"+"820,80:"+"V,L92,T20"+LN);
        sbResultado.append("@"+"820,110:"+"V,L92,T20"+LN);
        sbResultado.append("@"+"820,140:"+"V,L90,T20"+LN);
        sbResultado.append("@"+"820,170:"+"V,L57,T20"+LN);
        sbResultado.append("@"+"820,210:"+"V,L71,T20"+LN);






        sbResultado.append("@" + "920,90:"+FONTSMALL+LN+"03"+LN);
        sbResultado.append("@"+"940,90:"+FONTSMALL+LN+"03"+LN);
        sbResultado.append("@"+"920,120:"+FONTSMALL+LN+"03"+LN);
        sbResultado.append("@"+"940,120:"+FONTSMALL+LN+"03"+LN);
        sbResultado.append("@"+ "920,150:"+FONTSMALL+LN+"03"+LN);
        sbResultado.append("@"+"920,150:"+FONTSMALL+LN+"03"+LN);
        sbResultado.append("@"+"920,180:"+FONTSMALL+LN+"03"+LN);
        sbResultado.append("@"+"920,180:"+FONTSMALL+LN+"03"+LN);
        sbResultado.append("@"+"920,220:"+FONTSMALL+LN+"03"+LN);
        sbResultado.append("@"+"920,220:"+FONTSMALL+LN+"03"+LN);


        /*TODO CARGOS VARIOS*/
        y = 800;
        for (int x=0; x < cargoVarios.size();x++){
            String c = cargoVarios.get(x);
            y = y+20;
            sbResultado.append("@"+y+",400:"+FONTSMALL+LN+c+LN);
        }


       /*
        sbResultado.append("@"+"840,400:"+FONTSMALL+LN+"CARGO VARIOS     150"+LN);
        sbResultado.append("@"+"860,400:"+FONTSMALL+LN+"CARGO VARIOS     150"+LN);
        sbResultado.append("@"+"880,400:"+FONTSMALL+LN+"CARGO VARIOS    150"+LN);
        */
        sbResultado.append("@"+(y+20)+",400:"+FONTBOLD2+LN+"SUBCIDIO     150"+LN);
        /*TODO CARGOS VARIOS*/

        sbResultado.append("@"+"990,400:"+FONTBOLD2+LN+"SUBTOTAL     150"+LN);
        sbResultado.append("@"+"1010,400:"+FONTBOLD2+LN+"CARGOS VARIOS     150"+LN);
        sbResultado.append("@"+"1030,400:"+FONTBOLD2+LN+"Interes por mora    150"+LN);
        sbResultado.append("@"+"1050,400:"+FONTBOLD2+LN+"SUB TOTAL    150"+LN);

        sbResultado.append("@"+"1100,400:"+FONTBOLD2+LN+"FACTURAS PENDIENTES DE PAGO  12.50"+LN);
        sbResultado.append("@"+"1120,400:"+FONTBOLD2+LN+"TOTAL A  12.50"+LN);
        sbResultado.append("@"+"1140,400:"+FONTBOLD2+LN+"TOTAL B  12.50"+LN);
        sbResultado.append("@"+"1160,400:"+FONTBOLD2+LN+"TOTAL A+B+C  12.50"+LN);
        sbResultado.append("@"+"1250,0:"+FONTBOLD2+LN+"TOTAL ALCALDIA $  0.00"+LN);
        sbResultado.append("@"+"1420,10:"+FONTBOLD2+LN+"MENSAJE PARA LA FACTURA DEL CLIENTE"+LN);
        sbResultado.append("@"+"1500,10:"+FONTBOLD2+LN+"No."+LN);
        sbResultado.append("@"+"1500,80:"+FONTBOLD2+LN+"582598417"+LN);
        /*TODO QUINTA PARTE GAFICOS DE LA FACTURA EOO*/

        /*TODO SEXTA PARTE DE LA FACTURA EOO*/
        sbResultado.append("@"+"1700,180:"+FONTBOLD2+LN+"MF185|FACTURA"+LN);
        sbResultado.append("@"+"1720,180:"+FONTSMALL+LN+"MF226|SERIE"+LN);
        sbResultado.append("@"+"1720,240:"+FONTSMALL+LN+"52051"+LN);
        sbResultado.append("@"+"1740,180:"+FONTSMALL+LN+"ID DE CARGO"+LN);
        sbResultado.append("@"+"1740,290:"+FONTSMALL+LN+"I524105258"+LN);
        sbResultado.append("@"+"1760,180:"+FONTSMALL+LN+"NPE"+LN);
        sbResultado.append("@"+"1760,230:"+FONTSMALL+LN+"I524105258"+LN);
        sbResultado.append("@"+"1780,180:"+FONTSMALL+LN+"NIC"+LN);
        sbResultado.append("@"+"1780,230:"+FONTSMALL+LN+"I521528"+LN);
        sbResultado.append("@"+"1800,180:"+FONTSMALL+LN+"MF226|MES FACTURADO"+LN);
        sbResultado.append("@"+"1800,320:"+FONTSMALL+LN +"I521528"+LN);
        sbResultado.append("@"+"1820,220:"+FONTSMALL+LN+"IULTIMA FECHA DE PAGO"+LN);
        sbResultado.append("@"+"1840,260:"+FONTBOLD+LN+"13/05/2022"+LN);

        sbResultado.append("@"+"1750,500:"+FONTBOLD2+LN+"TOTAL EOO (A+B) $ 12.50"+LN);
        sbResultado.append("@"+"1800,500:"+FONTBOLD2+LN+"TOTAL ALCALDIA (C) $ 12.50"+LN);
        sbResultado.append("@"+"1870,500:"+FONTBOLD2+LN+"TOTAL EOO (A+B) $ 12.50"+LN);
        sbResultado.append("@"+"1900,500:"+FONTBOLD2+LN+"(A+B+C)"+LN);

        /*TODO SEXTA PARTE DE LA FACTURA EOO*/

        /*TODO PARTE SIETE CODEBAR DE LA FACTURA EOO*/
        sbResultado.append("@"+"2000,120:MF185|CODEBAR"+LN);
        sbResultado.append("@"+"2040,120:"+FONTBOLD2+LN+"554554548744548484848448448484"+LN);
        sbResultado.append("@"+"2060,120:"+FONTBOLD2+LN+"No."+LN);
        sbResultado.append("@"+"2060,190:"+FONTBOLD2+LN+"55145454215"+LN);
        sbResultado.append("@"+"2080,190:"+FONTBOLD2+LN+ "Notificacion de No" +LN);
        /*TODO PARTE SIETE CODEBAR DE LA FACTURA EOO*/
        sbResultado.append("}");
        return stringToByteArray(atildes(sbResultado.toString()));
    }






    public byte[] generarLogo(Bitmap anImage) throws IOException {

        byte[] printData;

        DocumentLP docLP;

        int m_printHeadWidth = 932;

        String ezHead = "EZ{AHEAD:110}\n";

        String ezBack = "EZ{BACK:190}\n";

        docLP = new DocumentLP("!");

        docLP.writeImage(anImage, m_printHeadWidth);

        byte[] imprime1 = stringToByteArray(ESC.concat(ezBack));

        printData = new byte[docLP.getDocumentData().length + imprime1.length];

        System.arraycopy(docLP.getDocumentData(),0,printData,0,docLP.getDocumentData().length);

        System.arraycopy(imprime1,0,printData,docLP.getDocumentData().length,imprime1.length);

        return printData;

    }


    private String atildes(String cadena) {
        return cadena.replace("á", "\240").replace("é", "\202").replace("í", "\241").replace("ó", "\242")
                .replace("ú", "\243").replace("ñ", "\244").replace("Ñ", "\245");

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



