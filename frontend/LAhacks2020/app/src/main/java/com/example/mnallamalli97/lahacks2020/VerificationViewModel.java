package com.example.mnallamalli97.lahacks2020;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mnallamalli97.lahacks2020.utilities.NetworkUtils;
import com.example.mnallamalli97.lahacks2020.utilities.VerificationService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerificationViewModel extends ViewModel {
    private MutableLiveData<Verification> verification;

    public VerificationViewModel(){
        verification = new MutableLiveData<Verification>();
    }

    public void verifyApple(int user_id, byte[] image){
        VerificationService service = NetworkUtils.getRetrofitInstance().create(VerificationService.class);
        Call<Verification> call = service.verifyApple(user_id, image);
        call.enqueue(new Callback<Verification>() {
            @Override
            public void onResponse(Call<Verification> call, Response<Verification> response) {
                verification.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Verification> call, Throwable t) {
                verification.setValue(null);
            }
        });
    }

    public void verifyVideo(int user_id, byte[] image){
        VerificationService service = NetworkUtils.getRetrofitInstance().create(VerificationService.class);
        Call<Verification> call = service.verifyVideo(user_id, image);
        call.enqueue(new Callback<Verification>() {
            @Override
            public void onResponse(Call<Verification> call, Response<Verification> response) {
                verification.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Verification> call, Throwable t) {
                verification.setValue(null);
            }
        });
    }

    public void verifyBook(int user_id, byte[] image){
        VerificationService service = NetworkUtils.getRetrofitInstance().create(VerificationService.class);
        Call<Verification> call = service.verifyBook(user_id, image);
        call.enqueue(new Callback<Verification>() {
            @Override
            public void onResponse(Call<Verification> call, Response<Verification> response) {
                verification.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Verification> call, Throwable t) {
                verification.setValue(null);
            }
        });
    }

    public void verifySunrise(int user_id, byte[] image){
        VerificationService service = NetworkUtils.getRetrofitInstance().create(VerificationService.class);
        Call<Verification> call = service.verifySunrise(user_id, image);
        call.enqueue(new Callback<Verification>() {
            @Override
            public void onResponse(Call<Verification> call, Response<Verification> response) {
                verification.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Verification> call, Throwable t) {
                verification.setValue(null);
            }
        });
    }
}
