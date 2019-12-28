package com.grupocumb.petroastur.ui.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.grupocumb.petroastur.R;
import com.grupocumb.petroastur.ui.home.HomeViewModel;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    private HomeViewModel homeViewModel;
    private RadioButton radioButtonCombustPreferido;
    private RadioButton radioButtonOrdenacion;

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
//        final TextView textView = root.findViewById(R.id.text_tools);
//        toolsViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });




        return root;
    }

    @Override
    public void onPause(){
        super.onPause();
        /*
         * COGER EL VIEWMODEL DE HOME
         * */
        homeViewModel =
                ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
        /*
        * CAMBIAR LA LISTA DE HOMEVIEWMODEL CON LA NUEVA LISTA SEGÚN LOS AJUSTES
        * */

    }
}