package com.example.nestedfragmentspractise;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class ParentFragment extends Fragment implements ChildFragment.OnChildFragmentInteractionListener {
    private Context context;
    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parent, container, false);

        AppCompatButton parentFragmentButton = view.findViewById(R.id.parent_fragment_button);
        parentFragmentButton.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.messageFromParentFragmentToActivity("Parent Fragment -> Activity");
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        Fragment childFragment = new ChildFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.child_fragment_container, childFragment).commit();
    }

    @Override
    public void messageFromChildToParent(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public interface OnFragmentInteractionListener {
        void messageFromParentFragmentToActivity(String message);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context + " must implement OnFragmentInteractionListener");
        }
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}