package com.sienrgitec.navegacionapp.ui.terminados;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sienrgitec.navegacionapp.R;
import com.sienrgitec.navegacionapp.ui.cobranza.CobranzaViewModel;

public class TerminadosFragment extends Fragment {

    private TerminadosViewModel terminadosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        terminadosViewModel =
                ViewModelProviders.of(this).get(TerminadosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_terminados, container, false);
        final TextView textView = root.findViewById(R.id.text_fin);
        terminadosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}