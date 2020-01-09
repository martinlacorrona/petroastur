package com.grupocumb.petroastur.ui.home;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.grupocumb.petroastur.R;
import com.grupocumb.petroastur.controller.impl.DataControllerImpl;
import com.grupocumb.petroastur.model.EstacionServicio;
import com.grupocumb.petroastur.ui.detallada.DetalladaFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private EstacionServicioAdapter mAdapter;
    private List<EstacionServicio> esta=new ArrayList<EstacionServicio>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
//        EstacionServicio e=new EstacionServicio();
//        e.setLatitud("43.3602905");
//        e.setLongitudWGS84("-5.8447599");
//        e.setId("Inés");
//        esta.add(e);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView=(RecyclerView)root.findViewById(R.id.recycler);
        RecyclerView.LayoutManager mLayoutManager=
                new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));

        mAdapter=new EstacionServicioAdapter(esta);
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                DetalladaFragment fr=new DetalladaFragment(esta.get(position));
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment,fr)
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onLongClick(View view, final int position) {
                Vibrator v = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    v.vibrate(50);
                }
            }
        }));

        homeViewModel.getText().observe(this, new Observer<List<EstacionServicio>>() {
            @Override
            public void onChanged(@Nullable List<EstacionServicio> s) {
                mAdapter=new EstacionServicioAdapter(homeViewModel.getText().getValue());
                recyclerView.setAdapter(mAdapter);
            }
        });
        return root;
    }
}