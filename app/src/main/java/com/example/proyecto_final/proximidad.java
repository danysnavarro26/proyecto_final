package com.example.proyecto_final;


import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

public class proximidad extends AppCompatActivity {
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        //seleccionamos el tipo de sensor que utilizaremos en este caso es de proxymidad
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        //verificamos si el EL DISPOSITIVO si no tienen sensor vamos a finalizar el activity
        if(sensor==null)
            finish();

        //agregamos nuestro evento
        sensorEventListener= new SensorEventListener() {
            //este metodo detecta cuando ls valores del sensor han cambiado
            //cuando nos alejamos o nos acercamos
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                //con este if cerificamos que la distancia al sensor sea menor al rango maximo
                if(sensorEvent.values[0]<sensor.getMaximumRange()){
                    // si secumple quiere decir que estamos dentro del rango dl sensor
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                }else{
                    getWindow().getDecorView().setBackgroundColor(Color.CYAN);

                }
            }
            // e ste metodo se utiliza cuando la preciosion de un sensor ha cambiado
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        start();


    }
    public void start(){
        //llamamos nuestro sensor manage
        sensorManager.registerListener(sensorEventListener, sensor, 2000*1000);
    }
    public void stop(){
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