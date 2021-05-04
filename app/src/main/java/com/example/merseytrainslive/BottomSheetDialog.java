package com.example.merseytrainslive;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class BottomSheetDialog extends BottomSheetDialogFragment {
    private BottomSheetListener mListener;
    private String title;

    public BottomSheetDialog() {

    }

    static BottomSheetDialog newInstance(String title) {
        BottomSheetDialog f = new BottomSheetDialog();

        // Supply str input as an argument.
        Bundle args = new Bundle();
        args.putString("title", title);
        f.setArguments(args);

        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);
        TextView titleText = v.findViewById(R.id.title);

        title = getArguments().getString("title");
        titleText.setText(title);

        return v;
    }

    public interface BottomSheetListener {
        void onButtonClicked(String text);
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        mListener = (BottomSheetListener) context;
    }


}
