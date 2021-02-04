package com.example.sensoresf1;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.content.Context.SENSOR_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FrgAccelerometer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FrgAccelerometer extends Fragment implements SensorEventListener {
    TextView xa,ya,za;
    private SensorManager manejador;
    private Sensor acelerometro;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FrgAccelerometer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FrgAccelerometer.
     */
    // TODO: Rename and change types and number of parameters
    public static FrgAccelerometer newInstance(String param1, String param2) {
        FrgAccelerometer fragment = new FrgAccelerometer();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ///////
        // Se obtiene el servicio del sistema
        manejador = (SensorManager)getActivity().getSystemService(SENSOR_SERVICE);
        // Obtener el sensor
        acelerometro = manejador.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        ///////
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
//---------- Implementación de metodos del Servicio del sistema-------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_frg_accelerometer, container, false);
        xa = (TextView) vista.findViewById(R.id.txtXacelerometer);
        ya = (TextView) vista.findViewById(R.id.txtYacelerometer);
        za = (TextView) vista.findViewById(R.id.txtZacelerometer);


        return vista;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float valor_x = event.values[0];
        float valor_y = event.values[1];
        float valor_z = event.values[2];
        xa.setText(valor_x+" ");
        //System.out.println("hhhhhhhhh"+x);
        ya.setText(valor_y+" ");
        za.setText(valor_z+" ");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onResume() {
        super.onResume();
        manejador.registerListener(this,acelerometro, manejador.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        manejador.unregisterListener(this);
    }

}