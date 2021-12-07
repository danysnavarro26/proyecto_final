package com.example.proyecto_final;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class AcelerometroActivity extends AppCompatActivity {
    // variables del sensor
    SensorManager sensorManager; //Nos permite acceder al sensor del dispositivo
    Sensor sensor; // esta variable representara el sensor
    SensorEventListener sensorEventListener; // este evento nos avisará cuando movamos el telefono

    //variables de los componentes
    TextView txt_ani;
    ImageView img_ani;

    int c=0; //Contador
    boolean val=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acelerometro);
        txt_ani=findViewById(R.id.txt_animal);
        img_ani=findViewById(R.id.img_animal);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE); // creamos una instancia de la clase SensorManager
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); // especificamos el sensor que utilizamos
        if(sensor==null) // verificación para ver si existe el sensor
            finish();

        sensorEventListener= new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float x = sensorEvent.values[0]; //nos regresa el valor del movimiento en el eje x
                //txt_ani.setText("valor: "+x);
                if(x<-5){
                    if(val==true){
                        val=false;
                        if(c==0){
                            txt_ani.setText("Perro");
                            img_ani.setImageResource(R.drawable.perro);
                        }
                        else if(c==1){
                            txt_ani.setText("Gato");
                            img_ani.setImageResource(R.drawable.gato);
                        }
                        else{
                            txt_ani.setText("Loro");
                            img_ani.setImageResource(R.drawable.loro);
                            c=-1;
                        }
                        c++;
                    }

                }else{
                    val = true;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        start();
    }

    private void start(){
        //función para iniciar el sensor
        sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void stop(){
        //metodo para detener el sensor
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onPause() {
        stop();
        super.onPause();
    }

    @Override
    protected void onResume() {
        start();
        super.onResume();
    }
}