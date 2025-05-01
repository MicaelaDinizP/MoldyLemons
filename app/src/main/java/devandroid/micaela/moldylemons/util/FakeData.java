package devandroid.micaela.moldylemons.util;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import devandroid.micaela.moldylemons.data.local.AppDatabase;
import devandroid.micaela.moldylemons.data.model.Couple;
import devandroid.micaela.moldylemons.data.repository.CoupleRepository;

public class FakeData {

    public static void inserirCasaisDeTesteSeBancoEstiverVazio(Context context) {
        CoupleRepository repository = new CoupleRepository((Application) context.getApplicationContext());

        new Thread(() -> {
            List<Couple> list = AppDatabase.getInstance(context).coupleDao().getAllCouples();

            if (list == null || list.isEmpty()) {
                Log.d("FAKE_DATA", "Inserindo casais de teste...");

                List<Couple> testCouples = Arrays.asList(
                        new Couple("Alice", "Bob", new Date(), "alicebob1", "senha123"),
                        new Couple("Mario", "Luigi", new Date(), "marioluigi2", "luigi123"),
                        new Couple("Romeu", "Julieta", new Date(), "romeujulieta3", "amor1234"),
                        new Couple("Ash", "Misty", new Date(), "ashmisty1", "poke1234"),
                        new Couple("Um", "Dois", new Date(), "umdois345", "senha789")
                );

                for (Couple couple : testCouples) {
                    repository.insert(couple);
                }

            } else {
                Log.d("FAKE_DATA", "Casais estão no banco.");
            }
        }).start();
    }
}
