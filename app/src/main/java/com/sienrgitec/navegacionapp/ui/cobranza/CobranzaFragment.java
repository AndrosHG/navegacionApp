package com.sienrgitec.navegacionapp.ui.cobranza;

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
import com.sienrgitec.navegacionapp.ui.gallery.GalleryViewModel;

public class CobranzaFragment extends Fragment {

    private CobranzaViewModel cobranzaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cobranzaViewModel =
                ViewModelProviders.of(this).get(CobranzaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slidecob, container, false);
        final TextView textView = root.findViewById(R.id.text_cob);
        cobranzaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}