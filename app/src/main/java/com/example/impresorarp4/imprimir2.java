package com.example.impresorarp4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class imprimir2 {

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




    public byte[] Formato2(String m) throws IOException {
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

        /*TODO PRIMERA PARTE DE LA FACTURA CLESA*/
        sbResultado.append(String.format("EZ{PRINT:\n"));
        sbResultado.append("  @0,140:MF226,VMULT 1HMULT 1|CLIENTE| ");
        sbResultado.append("@00,210:MF226,VMULT 1HMULT 1|SOLORZANO TORREZ ATILI OFI.COM|");
        sbResultado.append("@0,560:MF226,VMULT 1HMULT 1|CIAC SN MIGUEL EEO|");
        sbResultado.append("@20,140:MF226|DIRECCION|");
        sbResultado.append("@20,240:MF226|SUM 100MTS AL OTE ESCUELACION EL AMATE|");
        sbResultado.append( "@20,590:MF226|TARIFA: R - Residencial EEO|");
        sbResultado.append("@40,140:MF226|REFERENCIA DIR 100 MIS AL OTE DE ES |");
        sbResultado.append("@40,580:MF226|SEC RUTA  31136 25 146|");
        sbResultado.append("@80,580:MF226|FECHA EMISION 09/09/2022 |");
        sbResultado.append("@60,140:MF226|DIRECCION C CUELACION EL AMATE|");
        sbResultado.append("@60,580:MF226|MEDIDOR 96902201|");
        sbResultado.append("@80,140:MF185|NIC  3156911|");
        sbResultado.append("@100,580:MF226|FACTURAS PENDIENTES|");
        sbResultado.append("@120,580:MF107|CON SUBCIDIO|");
        /*TODO PRIMERA PARTE DE LA FACTURA CLESA*/

        /*TODO SEGUNDA PARTE DE LA FACTURA CLESA*/
        sbResultado.append("@220,10:MF226|DESDE  12/08/2022|");
        sbResultado.append("@220,230:MF226|TOTAL|");
        sbResultado.append("@220,430:MF226|54.89|");
        sbResultado.append("@220,600:MF226|NUMERO  62366731|");
        sbResultado.append("@240,10:MF226|HASTA  12/08/2022|");
        sbResultado.append("@240,230:MF226|TOTAL OTROS SERV|");
        sbResultado.append("@240,430:MF226|00.00|");
        sbResultado.append("@240,590:MF226|REGISTROS  No 321 - 2|");
        sbResultado.append("@"+"270,0:"+FONTSMALL+LN+"DIAS FACTURADOS  31"+LN);
        sbResultado.append("@"+"290,0:"+FONTSMALL+LN+"MES FACTURADO 09/05/2022"+LN);
        sbResultado.append("@"+"270,220:"+FONTSMALL+LN+"ALCALDIA"+LN);
        sbResultado.append("@"+"270,420:"+FONTSMALL+LN+"00.00"+LN);
        sbResultado.append("@"+"270,580:"+FONTSMALL+LN+"NIT 6614 161195 1030"+LN);
        sbResultado.append("@"+"300,0:"+FONTSMALL+LN+"FECHA EMISION  31/05/2022"+LN);
        sbResultado.append("@"+"280,520:"+FONTSMALL+LN+"GIRO Luz y Fuerza Electrica"+LN);
        sbResultado.append("@"+"320,0:"+FONTSMALL+LN+"CONSUMO KW 134.50"+LN);
        sbResultado.append("@"+"320,220:"+FONTSMALL+LN+"TOTAL A PAGAR"+LN);
        sbResultado.append("@"+"320,420:"+FONTSMALL+LN+"00.00"+LN);
        sbResultado.append("@"+"300,560:"+FONTSMALL+LN+"ULTIMA FECHA DE PAGO"+LN);
        sbResultado.append("@"+"320,560:"+FONTBOLD+LN+"20/05/2022"+LN);
        /*TODO SEGUNDA PARTE DE LA FACTURA CLESA*/

        /*TODO TERCERA PARTE DE LA FACTURA CLESA*/
        sbResultado.append("@400,0:MF226|INICIO|");
        sbResultado.append("@400,60:MF226|2/05/2022|");
        sbResultado.append("@400,160:MF226|SIN HORARIO|");
        sbResultado.append("@400,280:MF226|LECTURA|");
        sbResultado.append("@420,280:MF226|ACTUAL|");
        sbResultado.append("@440,280:MF226|916.0|");
        sbResultado.append("@400,420:MF226|LECTURA|");
        sbResultado.append("@420,420:MF226|ANTERIOR|");
        sbResultado.append("@440,420:MF226|842.0|");
        sbResultado.append("@420,0:MF226|FINAL|");
        sbResultado.append("@420,60:MF226|2/05/2022|");
        sbResultado.append("@440,0:MF226|ENERGIA|");
        sbResultado.append("@440,80:MF226|0.5236|");
        sbResultado.append("@400,520:MF226|CAP.LEIDA|");
        sbResultado.append("@420,520:MF226|CONSUMO|");
        sbResultado.append("@440,520:MF226|842.0|");
        sbResultado.append("@400,640:MF226|ACTUAL ANTERIO|");
        sbResultado.append("@420,640:MF226|CONSUMO|");
        sbResultado.append("@440,640:MF226|842.0|");
        /*TODO TERCERA PARTE DE LA FACTURA CLESA*/

        /*TODO CUARTA PARTE DE LA FACTURA CLESA*/
        sbResultado.append("@600,0:MF185|ICAL.CONSUMO"+LN);
        sbResultado.append("@620,0:MF226|IEnergia BT"+LN);
        sbResultado.append("@600,150:MF226|MEDIDOR"+LN);
        sbResultado.append("@620,150:MF226|5411245"+LN);
        sbResultado.append("@600,250:MF226|MULT"+LN);
        sbResultado.append("@620,250:MF226|0.00"+LN);
        sbResultado.append("@600,320:MF226|TIPO MEDIDOR"+LN);
        sbResultado.append("@620,320:MF226|KWH 0.00"+LN);
        sbResultado.append("@600,460:MF226|CAP"+LN);
        sbResultado.append("@620,460:MF226|CONTRATADA"+LN);
        sbResultado.append("@640,460:MF226|10"+LN);
        sbResultado.append("@600,580:MF226|CAP"+LN);
        sbResultado.append("@620,580:MF226|FACTURADA"+LN);
        sbResultado.append("@640,580:MF226|10"+LN);
        sbResultado.append("@600,690:MF226|CAP"+LN);
        sbResultado.append("@620,690:MF226|LEIDA"+LN);
        sbResultado.append("@640,690:MF226|10"+LN);
        /*TODO CUARTA PARTE DE LA FACTURA CLESA*/

        /*TODO QUINTA PARTE GAFICOS DE LA FACTURA CLESA*/

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






        sbResultado.append("@920,90:MF226|03|");
        sbResultado.append("@940,90:MF226|03|");
        sbResultado.append("@920,120:MF226|03|");
        sbResultado.append("@940,120:MF226|03|");
        sbResultado.append("@920,150:MF226|03|");
        sbResultado.append("@920,150:MF226|03|");
        sbResultado.append("@920,180:MF226|03|");
        sbResultado.append("@920,180:MF226|03|");
        sbResultado.append("@920,220:MF226|03|");
        sbResultado.append("@920,220:MF226|03|");


        /*TODO CARGOS VARIOS*/
        sbResultado.append("@820,400:MF226|CARGO VARIOS     150|");
        sbResultado.append("@840,400:MF226|CARGO VARIOS     150|");
        sbResultado.append("@860,400:MF226|CARGO VARIOS     150|");
        sbResultado.append("@880,400:MF226|CARGO VARIOS    150|");
        sbResultado.append("@900,400:MF185|SUBCIDIO     150|");
        /*TODO CARGOS VARIOS*/

        sbResultado.append("@990,400:MF185|SUBTOTAL     150|");
        sbResultado.append("@1010,400:MF185|CARGOS VARIOS     150|");
        sbResultado.append("@1030,400:MF185|Interes por mora    150|");
        sbResultado.append("@1050,400:MF185|SUB TOTAL    150|");

        sbResultado.append("@1100,400:MF185|FACTURAS PENDIENTES DE PAGO  12.50|");
        sbResultado.append("@1120,400:MF185|TOTAL A  12.50|");
        sbResultado.append("@1140,400:MF185|TOTAL B  12.50|");
        sbResultado.append("@1160,400:MF185|TOTAL A+B+C  12.50|");
        sbResultado.append("@1250,0:MF185|TOTAL ALCALDIA $  0.00|");
        sbResultado.append("@1420,10:MF185|MENSAJE PARA LA FACTURA DEL CLIENTE|");
        sbResultado.append("@1500,10:MF185|No.|");
        sbResultado.append("@1500,80:MF185|582598417|");
        /*TODO QUINTA PARTE GAFICOS DE LA FACTURA CLESA*/

        /*TODO SEXTA PARTE DE LA FACTURA CLESA*/
        sbResultado.append("@1700,180:MF185|FACTURA|");
        sbResultado.append("@1720,180:MF226|SERIE|");
        sbResultado.append("@1720,240:MF226|52051|");
        sbResultado.append("@1740,180:MF226|ID DE CARGO|");
        sbResultado.append("@1740,290:MF226|I524105258|");
        sbResultado.append("@1760,180:MF226|NPE|");
        sbResultado.append("@1760,230:MF226|I524105258|");
        sbResultado.append("@1780,180:MF226|NIC|");
        sbResultado.append("@1780,230:MF226|I521528|");
        sbResultado.append("@1800,180:MF226|MES FACTURADO|");
        sbResultado.append("@1800,320:MF226|I521528|");
        sbResultado.append("@1820,220:MF226|IULTIMA FECHA DE PAGO|");
        sbResultado.append("@1840,260:MF107|13/05/2022|");

        sbResultado.append("@1750,500:MF185|TOTAL EOO (A+B) $ 12.50|");
        sbResultado.append("@1800,500:MF185|TOTAL ALCALDIA (C) $ 12.50|");
        sbResultado.append("@1870,500:MF185|TOTAL EOO (A+B) $ 12.50|");
        sbResultado.append("@1900,500:MF107|(A+B+C)|");

        /*TODO SEXTA PARTE DE LA FACTURA CLESA*/

        /*TODO PARTE SIETE CODEBAR DE LA FACTURA CLESA*/
        sbResultado.append("@2000,120:MF185|CODEBAR|");
        sbResultado.append("@2040,120:MF185|554554548744548484848448448484|");
        sbResultado.append("@2060,120:MF185|No.|");
        sbResultado.append("@2060,190:MF185|55145454215|");
        /*TODO PARTE SIETE CODEBAR DE LA FACTURA CLESA*/
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