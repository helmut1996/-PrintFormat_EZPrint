package com.example.impresorarp4;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.UUID;

@RequiresApi(api = Build.VERSION_CODES.S)
public class MainActivity extends AppCompatActivity {
    private BluetoothAdapter mBTAdapter;
     Set<BluetoothDevice> mPairedDevices;
    private ArrayAdapter<String> mBTArrayAdapter;
    //StringBuilder sbResultado ;
    private final String m = "Imprimir en RP4";
    Imprimir imp;
    imprimir2 imp2;
    imprimir3 imp3;
    private Handler mHandler; // Nestor controller principal que reciter notifications de devolution de llamada
    private ConnectedThread mConnectedThread; // Subprocess de taboo en segundo plano bluetooth para envio y recibir datos
    private BluetoothSocket mBTSocket = null; // ruta de dates bidirectional de client a client
    private final String TAG = MainActivity.class.getSimpleName();
    private final static int REQUEST_ENABLE_BT = 1; // used to identify adding bluetooth names
    public final static int MESSAGE_READ = 2; // Se utilize en el controller Bluetooth para identifier la actualization de mensajes
    private final static int CONNECTING_STATUS = 3; // Se utilize en el controller Bluetooth para identifier el estate del mensaje
    private static final UUID BT_MODULE_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); // "random" unique identifier

    //private boolean isAndroid12 = Build.VERSION.SDK_INT>=Build.VERSION_CODES.S;


   Button btn_on;
   Button btn_off;
   Button mDiscoverBtn;
   Button mListPairedDevicesBtn;
   TextView mBluetoothStatus;
   ListView mDevicesListView;
  // Botones para Imprimir
   Button btn_print1;
   Button btn_print2;
   Button btn_print3;

   // para Graficos
    Bitmap anImage,ImgP;
    LinearLayout container;
     CustomProgressDialog loading;


     //validacion de Bluetooth
    private static final String[] BLE_PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
             Manifest.permission.BLUETOOTH_CONNECT,
             Manifest.permission.BLUETOOTH_SCAN,
             Manifest.permission.BLUETOOTH_ADMIN,
             Manifest.permission.BLUETOOTH,
    };

    private static final String[] ANDROID_12_BLE_PERMISSIONS = new String[]{
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.BLUETOOTH_ADVERTISE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.BLUETOOTH,


    };

    public static void requestBlePermissions(Activity activity, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            ActivityCompat.requestPermissions(activity, ANDROID_12_BLE_PERMISSIONS, requestCode);
        else
            ActivityCompat.requestPermissions(activity, BLE_PERMISSIONS, requestCode);
    }
/// fin ///

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestBlePermissions(MainActivity.this,12);



        loading = new CustomProgressDialog(MainActivity.this );
        mBluetoothStatus =findViewById(R.id.tv_connect);
        btn_on = findViewById(R.id.btn_on);
        btn_off = findViewById(R.id.btn_off);
        mDiscoverBtn =findViewById(R.id.btn_discover);
        mListPairedDevicesBtn = findViewById(R.id.btn_pairedDivices);
        btn_print1 =findViewById(R.id.btn_print1);
        btn_print2 = findViewById(R.id.btn_print2);
        btn_print3 = findViewById(R.id.btn_print3);

        anImage = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        container = findViewById(R.id.contenedorImg);


        // se Extancia la clase de Impresion para mandar a llamar los formatos de Impresion\
        imp = new Imprimir();
        imp2 = new imprimir2();
        imp3 = new imprimir3();
/////////////////////////////////////////////////////////////////////////////////////////////

        mBTArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        mBTAdapter = BluetoothAdapter.getDefaultAdapter(); // get a handle on the bluetooth radio

        mDevicesListView =findViewById(R.id.list_devices);
        mDevicesListView.setAdapter(mBTArrayAdapter); // assign model to view
        mDevicesListView.setOnItemClickListener(mDeviceClickListener);

        // Ask for location permission if not already allowed
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);


        mHandler = new Handler(Looper.getMainLooper()){
            @SuppressLint("SetTextI18n")
            @Override
            public void handleMessage(Message msg){
                if(msg.what == MESSAGE_READ){
                    String readMessage;
                    readMessage = new String((byte[]) msg.obj, StandardCharsets.UTF_8);
                    System.out.println(readMessage);
                }

                if(msg.what == CONNECTING_STATUS){
                    if(msg.arg1 == 1)
                        mBluetoothStatus.setText("Connected to Device:" + msg.obj);

                    else
                        mBluetoothStatus.setText(R.string.fallando );
                }
            }
        };

        if (mBTArrayAdapter == null) {
            // Device does not support Bluetooth
            mBluetoothStatus.setText(R.string.notfont);
            Toast.makeText(getApplicationContext(),"Bluetooth device not found!",Toast.LENGTH_SHORT).show();
        }
        else {

            btn_on.setOnClickListener(
                    (View v) -> bluetoothOn()
            );

            btn_off.setOnClickListener(
                    (View v)-> bluetoothOff()

            );

            mDiscoverBtn.setOnClickListener(
                    (View v)-> discover()

            );

            mListPairedDevicesBtn.setOnClickListener(
                    (View v)->listPairedDevices()

            );
            btn_print1.setOnClickListener(
                    (View v)->{

                        if (mConnectedThread!= null){
                            ModalConfirmacion();
                        }else{
                            AlertDialog.Builder  builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Debes de Conecxtar una impresora")
                                    .setPositiveButton("si", (dialogInterface, i) -> Toast.makeText(MainActivity.this, " Conectar a una impresora", Toast.LENGTH_SHORT).show()).show();
                        }



                    });

            btn_print2.setOnClickListener(
                    (View v)->{


                        if (mConnectedThread!= null){
                            ModalConfirmacion2();
                        }else{
                            AlertDialog.Builder  builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Debes de Conecxtar una impresora")
                                    .setPositiveButton("si", (dialogInterface, i) -> Toast.makeText(MainActivity.this, " Conectar a una impresora", Toast.LENGTH_SHORT).show()).show();
                        }


                    });

            btn_print3.setOnClickListener(
                    (View v)->{
                        if (mConnectedThread!= null){
                            ModalConfirmacion3();
                        }else{
                            AlertDialog.Builder  builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Debes de Conecxtar una impresora")
                                    .setPositiveButton("si", (dialogInterface, i) -> Toast.makeText(MainActivity.this, " Conectar a una impresora", Toast.LENGTH_SHORT).show()).show();
                        }
                    }
            );


        }


    }



    private void ModalConfirmacion(){

        AlertDialog.Builder  builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Quieres Imprimir formato 1 ?")
                .setPositiveButton("si", (dialogInterface, i) -> {
                    loading.show();
                    Toast.makeText(MainActivity.this, "Imprimiendo....", Toast.LENGTH_SHORT).show();


                    try {
                        byte []ss = imp.Formato1(m);
                        if (mConnectedThread != null) //Primero verifique para asegurarse de que el subproceso se creó
                            mConnectedThread.mmOutStream.write(ss,0,ss.length);
                        mConnectedThread.mmOutStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    new Handler().postDelayed(() -> loading.dismiss(),3000);
                    /*

                     */
                })
                .setNegativeButton("No", (dialogInterface, i) -> Toast.makeText(MainActivity.this, " se cancelo la impricion", Toast.LENGTH_SHORT).show()).show();


    }

    private void ModalConfirmacion2(){

        AlertDialog.Builder  builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Quieres Imprimir el formato 2?")
                .setPositiveButton("si", (dialogInterface, i) -> {
                    Toast.makeText(MainActivity.this, "Imprimiendo....", Toast.LENGTH_SHORT).show();
                    loading.show();
                    try {


                        byte[] ss2 = imp.generarLogo(anImage);
                        if (mConnectedThread != null) //Primero verifique para asegurarse de que el subproceso se creó
                            mConnectedThread.mmOutStream.write(ss2,0,ss2.length);
                        mConnectedThread.mmOutStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    new Handler().postDelayed(() -> loading.dismiss(),3000);

                })
                .setNegativeButton("No", (dialogInterface, i) -> Toast.makeText(MainActivity.this, " se cancelo la impricion", Toast.LENGTH_SHORT).show()).show();


    }



    private void ModalConfirmacion3(){

        AlertDialog.Builder  builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Quieres Imprimir el formato 2?")
                .setPositiveButton("si", (dialogInterface, i) -> {

                    Toast.makeText(MainActivity.this, "Imprimiendo....", Toast.LENGTH_SHORT).show();


                    try {
                        byte[] ss2 = imp.generarLogo(ImgP);

                        if (mConnectedThread != null) //Primero verifique para asegurarse de que el subproceso se creó
                            mConnectedThread.mmOutStream.write(ss2,0,ss2.length);
                        mConnectedThread.mmOutStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    new Handler().postDelayed(() -> loading.dismiss(),3000);

                })
                .setNegativeButton("No", (dialogInterface, i) -> Toast.makeText(MainActivity.this, " se cancelo la impricion", Toast.LENGTH_SHORT).show()).show();


    }

    @SuppressLint("MissingPermission")
    private void bluetoothOn(){
        if (!mBTAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            mBluetoothStatus.setText(R.string.bluetoothapagado);
            Toast.makeText(getApplicationContext(),"Bluetooth turned on",Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(getApplicationContext(),"Bluetooth is already on", Toast.LENGTH_SHORT).show();
        }
    }

    // Enter here after user selects "yes" or "no" to enabling radio
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent Data) {
        // Check which request we're responding to
        super.onActivityResult(requestCode, resultCode, Data);
        if (requestCode == REQUEST_ENABLE_BT) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                mBluetoothStatus.setText(R.string.encendido);
            } else
                mBluetoothStatus.setText(R.string.apagado);
        }
    }

    @SuppressLint("MissingPermission")
    private void bluetoothOff(){
        mBTAdapter.disable(); // turn off
        mBluetoothStatus.setText(R.string.bluetoothStatus);
        Toast.makeText(getApplicationContext(),"Bluetooth turned Off", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("MissingPermission")
    private void discover(){
        // Check if the device is already discovering
        if(mBTAdapter.isDiscovering()){
            mBTAdapter.cancelDiscovery();
            Toast.makeText(getApplicationContext(),"Discovery stopped",Toast.LENGTH_SHORT).show();
        }
        else{
            if(mBTAdapter.isEnabled()) {
                mBTArrayAdapter.clear(); // clear items
                mBTAdapter.startDiscovery();
                Toast.makeText(getApplicationContext(), "Discovery started", Toast.LENGTH_SHORT).show();
                registerReceiver(blReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
            }
            else{
                Toast.makeText(getApplicationContext(), "Bluetooth not on", Toast.LENGTH_SHORT).show();
            }
        }
    }

    final BroadcastReceiver blReceiver = new BroadcastReceiver() {
        @SuppressLint("MissingPermission")
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // add the name to the list
                mBTArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                mBTArrayAdapter.notifyDataSetChanged();
            }
        }
    };

    @SuppressLint("MissingPermission")
    private void listPairedDevices(){
        mBTArrayAdapter.clear();
        mPairedDevices = mBTAdapter.getBondedDevices();
        if(mBTAdapter.isEnabled()) {
            // put it's one to the adapter
            for (BluetoothDevice device : mPairedDevices)
                mBTArrayAdapter.add(device.getName() + "\n" + device.getAddress());

            Toast.makeText(getApplicationContext(), "Show Paired Devices", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getApplicationContext(), "Bluetooth not on", Toast.LENGTH_SHORT).show();
    }

    private final AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if(!mBTAdapter.isEnabled()) {
                Toast.makeText(getBaseContext(), "Bluetooth not on", Toast.LENGTH_SHORT).show();
                return;
            }

            mBluetoothStatus.setText(R.string.conectando);
            // Get the device MAC address, which is the last 17 chars in the View
            String info = ((TextView) view).getText().toString();
            final String address = info.substring(info.length() - 17);
            final String name = info.substring(0,info.length() - 17);

            // Spawn a new thread to avoid blocking the GUI one
            new Thread()
            {
                @SuppressLint("MissingPermission")
                @Override
                public void run() {
                    boolean fail = false;

                    BluetoothDevice device = mBTAdapter.getRemoteDevice(address);

                    try {
                        mBTSocket = createBluetoothSocket(device);
                    } catch (IOException e) {
                        fail = true;
                        Toast.makeText(getBaseContext(), "Socket creation failed", Toast.LENGTH_SHORT).show();
                    }
                    // Establish the Bluetooth socket connection.
                    try {
                        mBTSocket.connect();
                    } catch (IOException e) {
                        try {
                            fail = true;
                            mBTSocket.close();
                            mHandler.obtainMessage(CONNECTING_STATUS, -1, -1)
                                    .sendToTarget();
                        } catch (IOException e2) {
                            //insert code to deal with this
                            Toast.makeText(getBaseContext(), "Socket creation failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(!fail) {
                        mConnectedThread = new ConnectedThread(mBTSocket, mHandler);
                        mConnectedThread.start();

                        mHandler.obtainMessage(CONNECTING_STATUS, 1, -1, name)
                                .sendToTarget();
                    }
                }
            }.start();
        }
    };

    @SuppressLint("MissingPermission")
    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        try {
            final Method m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", UUID.class);
            return (BluetoothSocket) m.invoke(device, BT_MODULE_UUID);
        } catch (Exception e) {
            Log.e(TAG, "Could not create Insecure RFComm Connection",e);
        }
        return  device.createRfcommSocketToServiceRecord(BT_MODULE_UUID);
    }



}