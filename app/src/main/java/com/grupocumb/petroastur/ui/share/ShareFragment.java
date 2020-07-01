package com.grupocumb.petroastur.ui.share;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.grupocumb.petroastur.R;

public class ShareFragment extends Fragment {

    private String spam = "La mejor aplicación de gasolineras, petroastur!";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_share, container, false);
        Button buttonFacebook = root.findViewById(R.id.buttonFace);
        Button buttonTwitter = root.findViewById(R.id.buttonTw);
        Button buttonWhatsapp = root.findViewById(R.id.buttonWha);
        buttonFacebook.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, spam);
            intent.setPackage("com.facebook.katana");
            //startActivity(intent);
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(getActivity(), "Instala la aplicacion elegida", Toast.LENGTH_SHORT).show();
            }
        });
        buttonTwitter.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, spam);
            //Para especificar la red social especifica se le asigna en esta parte
            intent.setPackage("com.twitter.android");
            //startActivity(intent);
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(getActivity(), "Instala la aplicacion elegida", Toast.LENGTH_SHORT).show();
            }
        });
        buttonWhatsapp.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, spam);
            intent.setPackage("com.whatsapp");
            //startActivity(intent);
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(getActivity(), "Instala la aplicacion elegida", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
}