package com.project.colorpicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

import com.project.colorpicker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{
    private ActivityMainBinding binding;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.redSeekbar.setOnSeekBarChangeListener(this);
        binding.blueSeekbar.setOnSeekBarChangeListener(this);
        binding.greenSeekbar.setOnSeekBarChangeListener(this);
        binding.aplhaSeekBar.setOnSeekBarChangeListener(this);

        binding.button.setOnClickListener(v -> {
            String hex;
            try {
                hex = binding.hexEditText.getText().toString();
                binding.imageView.setBackgroundColor(Color.parseColor(hex));

                int c = Color.parseColor(hex);
                Color color = Color.valueOf(c);
                float r = color.red();
                float g = color.green();
                float b = color.blue();
                float a = color.alpha();

                int a_new = (int) (255*a/1);
                int r_new = (int) (255*r/1);
                int g_new = (int) (255*g/1);
                int b_new = (int) (255*b/1);

                binding.r.setText(String.valueOf(r_new));
                binding.g.setText(String.valueOf(g_new));
                binding.b.setText(String.valueOf(b_new));

                binding.redSeekbar.setProgress(r_new);
                binding.greenSeekbar.setProgress(g_new);
                binding.blueSeekbar.setProgress(b_new);
                binding.aplhaSeekBar.setProgress(a_new);
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "Color not found", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        int a = binding.aplhaSeekBar.getProgress();
        int r = binding.redSeekbar.getProgress();
        int g = binding.greenSeekbar.getProgress();
        int bl = binding.blueSeekbar.getProgress();

        binding.imageView.setBackgroundColor(Color.argb(a, r, g, bl));
        binding.r.setText(String.valueOf("R: " + r));
        binding.g.setText(String.valueOf("G: " + g));
        binding.b.setText(String.valueOf("B: " + bl));

        String hex = String.format("#%02x%02x%02x", r, g, bl);
        binding.hexEditText.setText(hex);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}