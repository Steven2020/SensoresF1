package com.example.sensoresf1;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static android.content.Context.SENSOR_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FrgProximity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FrgProximity extends Fragment implements SensorEventListener {
    SensorEventListener sensorEventListener;
    TextView xp;
    Button btnPlayProxi, btnStopProxi;
    private SensorManager manejador;
    private Sensor proximity;

    // TODO: Rename parameter arguments, choose names that match

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FrgProximity() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FrgProximity newInstance(String param1, String param2) {
        FrgProximity fragment = new FrgProximity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manejador = (SensorManager)getActivity().getSystemService(SENSOR_SERVICE);
        proximity = manejador.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista= inflater.inflate(R.layout.fragment_frg_proximity, container, false);
        xp = (TextView) vista.findViewById(R.id.txtXProximity);

        btnStopProxi = vista.findViewById(R.id.btnSproximidad);
        btnPlayProxi = vista.findViewById(R.id.btnPproximidad);

        btnPlayProxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResume();
            }
        });

        btnStopProxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPause();
            }
        });
        return vista;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float valor_x = event.values[0];
        xp.setText(valor_x+" ");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

   /* @Override
    public void onResume() {
        super.onResume();
        manejador.registerListener(this,proximity, manejador.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        manejador.unregisterListener(this);
    }
*/
   @Override
   public void onResume() {
       super.onResume();
       manejador.registerListener(this,proximity, manejador.SENSOR_DELAY_NORMAL);
   }

    @Override
    public void onPause() {
        super.onPause();
        manejador.unregisterListener(this);
    }

}