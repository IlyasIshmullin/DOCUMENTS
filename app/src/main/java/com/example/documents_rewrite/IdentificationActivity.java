package com.example.documents_rewrite;

import static com.example.documents_rewrite.SplashActivity.APP_PREFERENCES_NAME;
import static com.example.documents_rewrite.SplashActivity.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.documents_rewrite.databinding.ActivityIdentificateBinding;
import com.example.documents_rewrite.mainApplication.app.MainActivity;

public class IdentificationActivity extends AppCompatActivity {
    private String pin = "";
    ActivityIdentificateBinding binding;

    @Override
    @SuppressLint("ResourceAsColor")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIdentificateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        class PinEditor{
            ActivityIdentificateBinding binding;
            public PinEditor(ActivityIdentificateBinding binding){
                this.binding = binding;
            }
            @SuppressLint("ResourceAsColor")
            void getPinSymbol(int buttonNumber){
                pin = pin + buttonNumber;
                int pinLength = pin.length();
                switch (pinLength){
                    case 1:
                        binding.firstSymbol.setTextColor(R.color.md_theme_dark_shadow);
                        break;
                    case 2:
                        binding.secondSymbol.setTextColor(R.color.md_theme_dark_shadow);
                        break;
                    case 3:
                        binding.thirdSymbol.setTextColor(R.color.md_theme_dark_shadow);
                        break;
                    case 4:
                        binding.fourthSymbol.setTextColor(R.color.md_theme_dark_shadow);
                        if(isCorrectPin(pin))
                            pinIsCorrect();
                        else
                            try {
                                pinIsNotCorrect();
                            } catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        break;
                }
            }

            private Boolean isCorrectPin(String pin){
                return pin.equals("5089");
            }
            private void pinIsCorrect(){
                pin = "";
                startActivity(new Intent(IdentificationActivity.this, MainActivity.class));
                finish();
            }
            @SuppressLint("ResourceAsColor")
            private void pinIsNotCorrect() throws InterruptedException{
                    binding.firstSymbol.setTextColor(R.color.md_theme_dark_onError);
                    binding.secondSymbol.setTextColor(R.color.md_theme_dark_onError);
                    binding.thirdSymbol.setTextColor(R.color.md_theme_dark_onError);
                    binding.fourthSymbol.setTextColor(R.color.md_theme_dark_onError);
                    pin = "";
                    Thread.sleep(1000);
                    binding.firstSymbol.setTextColor(R.color.md_theme_dark_outline);
                    binding.secondSymbol.setTextColor(R.color.md_theme_dark_outline);
                    binding.thirdSymbol.setTextColor(R.color.md_theme_dark_outline);
                    binding.fourthSymbol.setTextColor(R.color.md_theme_dark_outline);
            }
        }

        PinEditor pinEditor = new PinEditor(binding);

        binding.tvName.setText(profile.getString(APP_PREFERENCES_NAME, "Your name"));
        binding.buttonNumber0.setOnClickListener(view -> {
            pinEditor.getPinSymbol(0);
            binding.buttonNumber0.setTextColor(R.color.md_theme_dark_outline);
            binding.buttonNumber0.setTextColor(R.color.md_theme_dark_onPrimary);
        });
        binding.buttonNumber1.setOnClickListener(view -> {
            pinEditor.getPinSymbol(1);
            binding.buttonNumber1.setTextColor(R.color.md_theme_dark_outline);
            binding.buttonNumber1.setTextColor(R.color.md_theme_dark_onPrimary);
        });
        binding.buttonNumber2.setOnClickListener(view -> {
            pinEditor.getPinSymbol(2);
            binding.buttonNumber2.setTextColor(R.color.md_theme_dark_outline);
            binding.buttonNumber2.setTextColor(R.color.md_theme_dark_onPrimary);
        });
        binding.buttonNumber3.setOnClickListener(view -> {
            pinEditor.getPinSymbol(3);
            binding.buttonNumber3.setTextColor(R.color.md_theme_dark_outline);
            binding.buttonNumber3.setTextColor(R.color.md_theme_dark_onPrimary);
        });
        binding.buttonNumber4.setOnClickListener(view -> {
            pinEditor.getPinSymbol(4);
            binding.buttonNumber4.setTextColor(R.color.md_theme_dark_outline);
            binding.buttonNumber4.setTextColor(R.color.md_theme_dark_onPrimary);
        });
        binding.buttonNumber5.setOnClickListener(view -> {
            pinEditor.getPinSymbol(5);
            binding.buttonNumber5.setTextColor(R.color.md_theme_dark_outline);
            binding.buttonNumber5.setTextColor(R.color.md_theme_dark_onPrimary);
        });
        binding.buttonNumber6.setOnClickListener(view -> {
            pinEditor.getPinSymbol(6);
            binding.buttonNumber6.setTextColor(R.color.md_theme_dark_outline);
            binding.buttonNumber6.setTextColor(R.color.md_theme_dark_onPrimary);
        });
        binding.buttonNumber7.setOnClickListener(view -> {
            pinEditor.getPinSymbol(7);
            binding.buttonNumber7.setTextColor(R.color.md_theme_dark_outline);
            binding.buttonNumber7.setTextColor(R.color.md_theme_dark_onPrimary);
        });
        binding.buttonNumber8.setOnClickListener(view -> {
            pinEditor.getPinSymbol(8);
            binding.buttonNumber8.setTextColor(R.color.md_theme_dark_outline);
            binding.buttonNumber8.setTextColor(R.color.md_theme_dark_onPrimary);
        });
        binding.buttonNumber9.setOnClickListener(view -> {
            pinEditor.getPinSymbol(9);
            binding.buttonNumber9.setTextColor(R.color.md_theme_dark_outline);
            binding.buttonNumber9.setTextColor(R.color.md_theme_dark_onPrimary);
        });

    }
}