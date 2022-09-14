package fr.human.it.ig2i.todoappjava.presentation.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import fr.human.it.ig2i.todoappjava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Nullable
    private ActivityMainBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(getBinding().getRoot());
    }

    @NonNull
    public ActivityMainBinding getBinding() {
        return Objects.requireNonNull(binding);
    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().popBackStack();
    }
}