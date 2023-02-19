package com.example.nestedfragmentspractise;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

public class ChildFragment extends Fragment {
    private OnChildFragmentToActivityInteractionListener mActivityListener;
    private OnChildFragmentInteractionListener mParentListener;
    private AppCompatButton messageActivityButton;
    private AppCompatButton messageParentFragmentButton;

    private void settingOnClickListeners() {
        messageActivityButton.setOnClickListener(v -> {
            if (mActivityListener != null) {
                mActivityListener.messageFromChildFragmentToActivity("Child Fragment -> Activity");
            }
        });
        messageParentFragmentButton.setOnClickListener(v -> {
            if (mParentListener != null) {
                mParentListener.messageFromChildToParent("Child Fragment -> Parent Fragment");
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child, container, false);

        messageActivityButton = view.findViewById(R.id.message_activity_child_fragment_button);
        messageParentFragmentButton = view.findViewById(R.id.message_parent_fragment_child_fragment_button);

        settingOnClickListeners();

        return view;
    }


    public interface OnChildFragmentToActivityInteractionListener {
        void messageFromChildFragmentToActivity(String message);
    }

    public interface OnChildFragmentInteractionListener {
        void messageFromChildToParent(String message);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        // Check if the Activity implements the listener
        if (context instanceof OnChildFragmentToActivityInteractionListener) {
            mActivityListener = (OnChildFragmentToActivityInteractionListener) context;
        } else {
            throw new RuntimeException(context + " must implement OnChildFragmentToActivityInteractionListener");
        }

        // Check if Parent Fragment implements the listener
        if (getParentFragment() instanceof OnChildFragmentInteractionListener) {
            mParentListener = (OnChildFragmentInteractionListener) getParentFragment();
        } else {
            throw new RuntimeException("The parent fragment must implement OnChildFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivityListener = null;
        mParentListener = null;
    }
}