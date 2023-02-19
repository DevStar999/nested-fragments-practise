package com.example.nestedfragmentspractise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ParentFragment.OnFragmentInteractionListener,
        ChildFragment.OnChildFragmentToActivityInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.parent_fragment_container, new ParentFragment());
        transaction.commit();
    }

    @Override
    public void messageFromParentFragmentToActivity(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void messageFromChildFragmentToActivity(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}