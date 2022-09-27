package com.example.impresorarp4;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import datamaxoneil.printer.DocumentLP;

public class Imprimir {

    /*
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
*/
    /*TODO variables para formatos de la impresora */
    static final String ENCODING = "ISO-8859-1";
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
    static final String SEPARADOR = "=========================================================================";
    static final String SEPARADOR_ = "________________________________________________________________________________";
    static final String LN = "|";
    static final String ESC = "";
    /*TODO variables para formatos de la impresora */

    //TODO Coordenadas
    int x;
    int y;
    //TODO Coordenadas
    ArrayList<String> cargoVarios;
    String barcode = "12345678941";

    /*TODO: TIPOS DE BAR CODES*/
    static final String BARCODE = "EN128, HIGH 12, WIDE 2";
    static final String BARCODE2 = "BC128, HIGH 12, WIDE 3";
    String BARCODE3 = "PD417, HIGH 10, WIDE 1";
    String BARCODEWIDTH = "EAN08, HIGH 18, WIDE 1";
    String BARCODEBOLD ="EAN13,HIGH 12, WIDE 1";
    String BARCODEBOLD2 = "PD417,HIGH 12, WIDE 1";
    static final String BARCODELITTLE ="BC39W, HIGH 8, WIDE 1";
    static final String BARCODELARGE = "BC128 HIGH 12, WIDE 2";
    /*TODO: TIPOS DE BAR CODES*/

    public byte[] Formato1(String m) throws IOException {
        StringBuilder sbResultado = new StringBuilder();

        cargoVarios = new ArrayList<>();
        cargoVarios.add("Cargo vario 1");
        cargoVarios.add("Cargo vario 2");
        cargoVarios.add("Cargo vario 3");
        cargoVarios.add("Cargo vario 4");
        cargoVarios.add("Cargo vario 5");

        /*TODO PRIMERA PARTE DE LA FACTURA EOO*/
        y = 0;
        sbResultado.append(ESC+"EZ{PRINT:\n");
        sbResultado.append("@").append(y).append(",140:").append(FONTSMALL).append(LN).append("CLIENTE").append(LN);
        sbResultado.append("@").append(y).append(",210:").append(FONTSMALL).append(LN).append("SOLORZANO TORREZ ATILI OFI.COM").append(LN);
        sbResultado.append("@").append(y).append(",580:").append(FONTSMALL).append(LN).append("CIAC SN MIGUEL EEO").append(LN);
        sbResultado.append("@").append(y + 20).append(",140:").append(FONTSMALL).append(LN).append("DIRECCION").append(LN);
        sbResultado.append("@").append(y + 20).append(",240:").append(FONTSMALL).append(LN).append("SUM 100MTS AL OTE ESCUELACION EL AMATE").append(LN);
        sbResultado.append("@").append(y + 20).append(",590:").append(FONTSMALL).append(LN).append("TARIFA: R - Residencial EEO").append(LN);
        sbResultado.append("@").append(y + 40).append(",140:").append(FONTSMALL).append(LN).append("REFERENCIA DIR 100 MIS AL OTE DE ES ").append(LN);
        sbResultado.append("@").append(y + 40).append(",580:").append(FONTSMALL).append(LN).append("SEC RUTA  31136 25 146").append(LN);
        sbResultado.append("@").append(y + 80).append(",580:").append(FONTSMALL).append(LN).append("FECHA EMISION 09/09/2022 ").append(LN);
        sbResultado.append("@").append(y + 60).append(",140:").append(FONTSMALL).append(LN).append("DIRECCION C CUELACION EL AMATE").append(LN);
        sbResultado.append("@").append(y + 60).append(",580:").append(FONTSMALL).append(LN).append("MEDIDOR 96902201").append(LN);
        sbResultado.append("@").append(y + 80).append(",140:").append(FONTBOLD2).append(LN).append("NIC  3156911").append(LN);
        sbResultado.append("@").append(y + 100).append(",580:").append(FONTSMALL).append(LN).append("FACTURAS PENDIEN0TES").append(LN);
        sbResultado.append("@").append(y + 120).append(",580:").append(FONTBOLD).append(LN).append("CON SUBCIDIO").append(LN);
        /*TODO PRIMERA PARTE DE LA FACTURA EOO*/

        /*TODO SEGUNDA PARTE DE LA FACTURA EOO*/
        y = 220;
        sbResultado.append("@").append(y).append(",10:").append(FONTSMALL).append(LN).append("DESDE  12/08/2022").append(LN);
        sbResultado.append("@").append(y).append(",230:").append(FONTSMALL).append(LN).append("TOTAL").append(LN);
        sbResultado.append("@").append(y).append(",430:").append(FONTSMALL).append(LN).append("54.89").append(LN);
        sbResultado.append("@").append(y).append(",600:").append(FONTSMALL).append(LN).append("NUMERO  62366731").append(LN);
        sbResultado.append("@").append(y + 20).append(",10:").append(FONTSMALL).append(LN).append("HASTA  12/08/2022").append(LN);
        sbResultado.append("@").append(y + 20).append(",230:").append(FONTSMALL).append(LN).append("TOTAL OTROS SERV").append(LN);
        sbResultado.append("@").append(y + 20).append(",430:").append(FONTSMALL).append(LN).append("00.00").append(LN);
        sbResultado.append("@").append(y + 20).append(",590:").append(FONTSMALL).append(LN).append("REGISTROS  No 321 - 2").append(LN);
        sbResultado.append("@").append(y + 40).append(",0:").append(FONTSMALL).append(LN).append("DIAS FACTURADOS  31").append(LN);
        sbResultado.append("@").append(y + 60).append(",0:").append(FONTSMALL).append(LN).append("MES FACTURADO 09/05/2022").append(LN);
        sbResultado.append("@").append(y + 60).append(",220:").append(FONTSMALL).append(LN).append("ALCALDIA").append(LN);
        sbResultado.append("@").append(y + 60).append(",420:").append(FONTSMALL).append(LN).append("00.00").append(LN);
        sbResultado.append("@").append(y + 60).append(",580:").append(FONTSMALL).append(LN).append("NIT 6614 161195 1030").append(LN);
        sbResultado.append("@").append(y + 80).append(",0:").append(FONTSMALL).append(LN).append("FECHA EMISION  31/05/2022").append(LN);
        sbResultado.append("@").append(y + 80).append(",520:").append(FONTSMALL).append(LN).append("GIRO Luz y Fuerza Electrica").append(LN);
        sbResultado.append("@").append(y + 100).append(",0:").append(FONTSMALL).append(LN).append("CONSUMO KW 134.50").append(LN);
        sbResultado.append("@").append(y + 100).append(",220:").append(FONTSMALL).append(LN).append("TOTAL A PAGAR").append(LN);
        sbResultado.append("@").append(y + 100).append(",420:").append(FONTSMALL).append(LN).append("00.00").append(LN);
        sbResultado.append("@").append(y + 100).append(",560:").append(FONTSMALL).append(LN).append("ULTIMA FECHA DE PAGO").append(LN);
        sbResultado.append("@").append(y + 120).append(",560:").append(FONTBOLD).append(LN).append("20/05/2022").append(LN);
        /*TODO SEGUNDA PARTE DE LA FACTURA EOO*/

        /*TODO TERCERA PARTE DE LA FACTURA EOO*/
        y = 400;
        sbResultado.append("@").append(y).append(",0:").append(FONTSMALL).append(LN).append("INICIO").append(LN);
        sbResultado.append("@").append(y).append(",60:").append(FONTSMALL).append(LN).append("2/05/2022").append(LN);
        sbResultado.append("@").append(y).append(",160:").append(FONTSMALL).append(LN).append("SIN HORARIO").append(LN);
        sbResultado.append("@").append(y).append(",280:").append(FONTSMALL).append(LN).append("LECTURA").append(LN);
        sbResultado.append("@").append(y + 20).append(",280:").append(FONTSMALL).append(LN).append("ACTUAL").append(LN);
        sbResultado.append("@").append(y + 40).append(",280:").append(FONTSMALL).append(LN).append("916.0").append(LN);
        sbResultado.append("@").append(y).append(",420:").append(FONTSMALL).append(LN).append("LECTURA").append(LN);
        sbResultado.append("@").append(y + 20).append(",420:").append(FONTSMALL).append(LN).append("ANTERIOR").append(LN);
        sbResultado.append("@").append(y + 40).append(",420:").append(FONTSMALL).append(LN).append("842.0").append(LN);
        sbResultado.append("@").append(y + 20).append(",0:").append(FONTSMALL).append(LN).append("FINAL").append(LN);
        sbResultado.append("@").append(y + 20).append(",60:").append(FONTSMALL).append(LN).append("2/05/2022").append(LN);
        sbResultado.append("@").append(y + 40).append(",0:").append(FONTSMALL).append(LN).append("ENERGIA").append(LN);
        sbResultado.append("@").append(y + 40).append(",80:").append(FONTSMALL).append(LN).append("0.5236").append(LN);
        sbResultado.append("@").append(y).append(",520:").append(FONTSMALL).append(LN).append("CAP.LEIDA").append(LN);
        sbResultado.append("@").append(y + 20).append(",520:").append(FONTSMALL).append(LN).append("CONSUMO").append(LN);
        sbResultado.append("@").append(y + 40).append(",520:").append(FONTSMALL).append(LN).append("842.0").append(LN);
        sbResultado.append("@").append(y).append(",640:").append(FONTSMALL).append(LN).append("ACTUAL ANTERIO").append(LN);
        sbResultado.append("@").append(y + 20).append(",640:").append(FONTSMALL).append(LN).append("CONSUMO").append(LN);
        sbResultado.append("@").append(y + 40).append(",640:").append(FONTSMALL).append(LN).append("842.0").append(LN);
        /*TODO TERCERA PARTE DE LA FACTURA EOO*/

        /*TODO CUARTA PARTE DE LA FACTURA EOO*/
        y = 600;
        sbResultado.append("@"+"600,0:"+FONTBOLD2+LN+"CAL.CONSUMO"+LN);
        sbResultado.append("@").append(y + 20).append(",0:").append(FONTSMALL).append(LN).append("Energia BT").append(LN);
        sbResultado.append("@").append(y).append(",150:").append(FONTSMALL).append(LN).append("MEDIDOR").append(LN);
        sbResultado.append("@").append(y + 20).append(",150:").append(FONTSMALL).append(LN).append("5411245").append(LN);
        sbResultado.append("@"+"600,250:"+FONTSMALL+LN+"MULT"+LN);
        sbResultado.append("@").append(y + 20).append(",250:").append(FONTSMALL).append(LN).append("MF226|0.00").append(LN);
        sbResultado.append("@").append(y).append(",320:").append(FONTSMALL).append(LN).append("TIPO MEDIDOR").append(LN);
        sbResultado.append("@").append(y + 20).append(",320:").append(FONTSMALL).append(LN).append("KWH 0.00").append(LN);
        sbResultado.append("@").append(y).append(",460:").append(FONTSMALL).append(LN).append("CAP").append(LN);
        sbResultado.append("@").append(y + 20).append(",460:").append(FONTSMALL).append(LN).append("CONTRATADA").append(LN);
        sbResultado.append("@").append(y + 40).append(",460:").append(FONTSMALL).append(LN).append("10").append(LN);
        sbResultado.append("@").append(y).append(",580:").append(FONTSMALL).append(LN).append("CAP").append(LN);
        sbResultado.append("@").append(y + 20).append(",580:").append(FONTSMALL).append(LN).append("FACTURADA").append(LN);
        sbResultado.append("@").append(y + 40).append(",580:").append(FONTSMALL).append(LN).append("10").append(LN);
        sbResultado.append("@").append(y).append(",690:").append(FONTSMALL).append(LN).append("MF226|CAP").append(LN);
        sbResultado.append("@").append(y + 20).append(",690:").append(FONTSMALL).append(LN).append("LEIDA").append(LN);
        sbResultado.append("@").append(y + 40).append(",690:").append(FONTSMALL).append(LN).append("10").append(LN);
        /*TODO CUARTA PARTE DE LA FACTURA EOO*/

        /*TODO QUINTA PARTE GAFICOS DE LA FACTURA EOO*/

        y=920;
        sbResultado.append("@").append(y).append(",10:").append(FONTSMALL).append(LN).append("MES").append(LN);
        sbResultado.append("@").append(y + 20).append(",10:").append(FONTSMALL).append(LN).append("CONSUMO").append(LN);
        sbResultado.append("@").append(y + 40).append(",10:").append(FONTSMALL).append(LN).append("CONSUMO PROMEDIO KWH 105").append(LN);
        sbResultado.append("@").append(y + 130).append(",10:").append(FONTSMALL).append(LN).append("ALCALDIA (C)").append(LN);
        sbResultado.append("@").append(y + 150).append(",10:").append(FONTSMALL).append(LN).append("DETALLE TASAS MUNICIPALES IMPORTE").append(LN);




        /*TODO ESTAS SON LAS BARRAS DE LAS GRAFICAS*/
        y =820;
        sbResultado.append("@").append(y).append(",80:").append("V,L92,T20").append(LN);
        sbResultado.append("@").append(y).append(",110:").append("V,L92,T20").append(LN);
        sbResultado.append("@").append(y).append(",140:").append("V,L90,T20").append(LN);
        sbResultado.append("@").append(y).append(",170:").append("V,L57,T20").append(LN);
        sbResultado.append("@").append(y).append(",210:").append("V,L71,T20").append(LN);
        /*TODO ESTAS SON LAS BARRAS DE LAS GRAFICAS*/




        y=920;
        sbResultado.append("@").append(y).append(",90:").append(FONTSMALL).append(LN).append("03").append(LN);
        sbResultado.append("@").append(y + 20).append(",90:").append(FONTSMALL).append(LN).append("03").append(LN);
        sbResultado.append("@").append(y).append(",120:").append(FONTSMALL).append(LN).append("03").append(LN);
        sbResultado.append("@").append(y + 20).append(",120:").append(FONTSMALL).append(LN).append("03").append(LN);
        sbResultado.append("@").append(y).append(",150:").append(FONTSMALL).append(LN).append("03").append(LN);
        sbResultado.append("@").append(y).append(",150:").append(FONTSMALL).append(LN).append("03").append(LN);
        sbResultado.append("@").append(y).append(",180:").append(FONTSMALL).append(LN).append("03").append(LN);
        sbResultado.append("@").append(y).append(",180:").append(FONTSMALL).append(LN).append("03").append(LN);
        sbResultado.append("@").append(y).append(",220:").append(FONTSMALL).append(LN).append("03").append(LN);
        sbResultado.append("@").append(y).append(",220:").append(FONTSMALL).append(LN).append("03").append(LN);


        /*TODO CARGOS VARIOS*/
        y = 800;
        for (int x=0; x < cargoVarios.size();x++){
            String c = cargoVarios.get(x);
            y = y+20;
            sbResultado.append("@").append(y).append(",400:").append(FONTSMALL).append(LN).append(c).append(LN);
        }



        sbResultado.append("@").append(y + 20).append(",400:").append(FONTBOLD).append(LN).append("SUBCIDIO     150").append(LN);
        /*TODO CARGOS VARIOS*/

        y= 990;
        sbResultado.append("@").append(y).append(",400:").append(FONTBOLD2).append(LN).append("SUBTOTAL     150").append(LN);
        sbResultado.append("@").append(y + 20).append(",400:").append(FONTBOLD2).append(LN).append("CARGOS VARIOS     150").append(LN);
        sbResultado.append("@").append(y + 40).append(",400:").append(FONTBOLD2).append(LN).append("Interes por mora    150").append(LN);
        sbResultado.append("@").append(y + 60).append(",400:").append(FONTBOLD2).append(LN).append("SUB TOTAL    150").append(LN);

        y =1100;
        sbResultado.append("@").append(y).append(",400:").append(FONTBOLD2).append(LN).append("FACTURAS PENDIENTES DE PAGO  12.50").append(LN);
        sbResultado.append("@").append(y + 20).append(",400:").append(FONTBOLD2).append(LN).append("TOTAL A  12.50").append(LN);
        sbResultado.append("@").append(y + 40).append(",400:").append(FONTBOLD2).append(LN).append("TOTAL B  12.50").append(LN);
        sbResultado.append("@").append(y + 60).append(",400:").append(FONTBOLD2).append(LN).append("TOTAL A+B+C  12.50").append(LN);
        sbResultado.append("@").append(y + 150).append(",0:").append(FONTBOLD2).append(LN).append("TOTAL ALCALDIA $  0.00").append(LN);
        sbResultado.append("@").append(y + 320).append(",10:").append(FONTBOLD).append(LN).append("MENSAJE PARA LA FACTURA DEL CLIENTE").append(LN);
        sbResultado.append("@").append(y + 400).append(",10:").append(FONTBOLD2).append(LN).append("No.").append(LN);
        sbResultado.append("@").append(y + 400).append(",80:").append(FONTBOLD2).append(LN).append("582598417").append(LN);
        /*TODO QUINTA PARTE GAFICOS DE LA FACTURA EOO*/

        /*TODO SEXTA PARTE DE LA FACTURA EOO*/
        y = 1700;
        sbResultado.append("@").append(y).append(",180:").append(FONTBOLD2).append(LN).append("FACTURA").append(LN);
        sbResultado.append("@").append(y + 20).append(",180:").append(FONTSMALL).append(LN).append("SERIE").append(LN);
        sbResultado.append("@").append(y + 20).append(",240:").append(FONTSMALL).append(LN).append("52051").append(LN);
        sbResultado.append("@").append(y + 40).append(",180:").append(FONTSMALL).append(LN).append("ID DE CARGO").append(LN);
        sbResultado.append("@").append(y + 40).append(",290:").append(FONTSMALL).append(LN).append("I524105258").append(LN);
        sbResultado.append("@").append(y + 60).append(",180:").append(FONTSMALL).append(LN).append("NPE").append(LN);
        sbResultado.append("@").append(y + 60).append(",230:").append(FONTSMALL).append(LN).append("I524105258").append(LN);
        sbResultado.append("@").append(y + 80).append(",180:").append(FONTSMALL).append(LN).append("NIC").append(LN);
        sbResultado.append("@").append(y + 80).append(",230:").append(FONTSMALL).append(LN).append("I521528").append(LN);
        sbResultado.append("@").append(y + 100).append(",180:").append(FONTSMALL).append(LN).append("MF226|MES FACTURADO").append(LN);
        sbResultado.append("@").append(y + 100).append(",320:").append(FONTSMALL).append(LN).append("I521528").append(LN);
        sbResultado.append("@").append(y + 120).append(",220:").append(FONTSMALL).append(LN).append("IULTIMA FECHA DE PAGO").append(LN);
        sbResultado.append("@").append(y + 140).append(",260:").append(FONTBOLD).append(LN).append("13/05/2022").append(LN);

        y = 1750;
        sbResultado.append("@").append(y).append(",500:").append(FONTBOLD2).append(LN).append("TOTAL EOO (A+B) $ 12.50").append(LN);
        sbResultado.append("@").append(y + 50).append(",500:").append(FONTBOLD2).append(LN).append("TOTAL ALCALDIA (C) $ 12.50").append(LN);
        sbResultado.append("@").append(y + 120).append(",500:").append(FONTBOLD2).append(LN).append("TOTAL EOO (A+B) $ 12.50").append(LN);
        sbResultado.append("@").append(y + 150).append(",500:").append(FONTBOLD).append(LN).append("(A+B+C)").append(LN);

        /*TODO SEXTA PARTE DE LA FACTURA EOO*/

        /*TODO PARTE SIETE CODEBAR DE LA FACTURA EOO*/
        y=2000;
        sbResultado.append("@").append(y - 70).append(",120:").append(BARCODE).append(LN).append(barcode).append(LN);
        sbResultado.append("@").append(y + 20).append(",120:").append(FONTBOLD2).append(LN).append("554554548744548484848448448484").append(LN);
        sbResultado.append("@").append(y + 40).append(",120:").append(FONTBOLD2).append(LN).append("No.").append(LN);
        sbResultado.append("@").append(y + 40).append(",190:").append(FONTBOLD2).append(LN).append("55145454215").append(LN);
        sbResultado.append("@").append(y + 120).append(",190:").append(FONTBOLD2).append(LN).append("").append(LN);// ESTA ES PARA DAR ESPACIO DE SEPARACION
        /*TODO PARTE SIETE CODEBAR DE LA FACTURA EOO*/
        sbResultado.append("}{LP}");
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

        byte[] b;
        ByteArrayOutputStream ByteArray = new ByteArrayOutputStream();
        byte[] buf = s.getBytes(ENCODING);
        ByteArray.write(buf);
        b = ByteArray.toByteArray();
        return b;

    }
}



