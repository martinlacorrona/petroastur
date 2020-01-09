package com.grupocumb.petroastur.ui.send;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.grupocumb.petroastur.R;

public class SendFragment extends Fragment {

    private SendViewModel sendViewModel;
    private Button botonEnviar;
    private TextView text;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);
        botonEnviar = (Button) root.findViewById(R.id.buttonEnviar);
        text = (TextView) root.findViewById(R.id.textoParaEnviar);
        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text.getText().length() == 0) {
                    Toast.makeText(getActivity(), "No hay ninguna opinión en el texto", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "¡Gracias por darnos tu opinión!", Toast.LENGTH_SHORT).show();
                    text.setText("");
                }
            }
        });
        return root;
    }
}