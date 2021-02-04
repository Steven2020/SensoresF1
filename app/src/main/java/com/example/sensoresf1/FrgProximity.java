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
import android.widget.TextView;

import static android.content.Context.SENSOR_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FrgProximity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FrgProximity extends Fragment implements SensorEventListener {
    TextView xp;
    private SensorManager manejador;
    private Sensor proximity;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
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
        ///////
        // Se obtiene el servicio del sistema
        manejador = (SensorManager)getActivity().getSystemService(SENSOR_SERVICE);
        // Obtener el sensor
        proximity = manejador.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        ///////
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista= inflater.inflate(R.layout.fragment_frg_proximity, container, false);
        xp = (TextView) vista.findViewById(R.id.txtXProximity);

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

    @Override
    public void onResume() {
        super.onResume();
        manejador.registerListener(this,proximity, manejador.SENSOR_DELAY_NORMAL);
    }
}