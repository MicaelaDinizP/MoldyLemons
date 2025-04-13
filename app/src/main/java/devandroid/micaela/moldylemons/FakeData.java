package devandroid.micaela.moldylemons;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FakeData {

    public static void inserirCasaisDeTesteSeBancoEstiverVazio(Context context) {
        CoupleRepository repository = new CoupleRepository((Application) context.getApplicationContext());

        new Thread(() -> {
            List<Couple> list = AppDatabase.getInstance(context).coupleDao().getAllCouples();

            if (list == null || list.isEmpty()) {
                Log.d("FAKE_DATA", "Inserindo casais de teste...");

                List<Couple> testCouples = Arrays.asList(
                        new Couple("Alice", "Bob", new Date(), "alicebob", "1234"),
                        new Couple("Mario", "Luigi", new Date(), "marioluigi", "senha"),
                        new Couple("Romeu", "Julieta", new Date(), "romeujulieta", "amor"),
                        new Couple("Ash", "Misty", new Date(), "pokemon", "pikachu"),
                        new Couple("Um", "Dois", new Date(), "teste", "1234")
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