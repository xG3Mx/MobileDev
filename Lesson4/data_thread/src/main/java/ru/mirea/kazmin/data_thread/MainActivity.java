package ru.mirea.kazmin.data_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import ru.mirea.kazmin.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Runnable runn1 = new Runnable() {
            public void run() {
                binding.textViewInfo.setText("runn1");
            }
        };
        final Runnable runn2 = new Runnable() {
            public void run() {
                binding.textViewInfo.setText("runn2");
            }
        };
        final Runnable runn3 = new Runnable() {
            public void run() {
                binding.textViewInfo.setText("runn3");
            }
        };
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    TimeUnit.SECONDS.sleep(1);
                    binding.textViewInfo.postDelayed(runn3, 2000);
                    binding.textViewInfo.post(runn2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        binding.textView.setText("Последовательность запуска процессов: \n runn1 -> runn2 -> runn3 \n Различие между методами «runOnUiThread» и «postDelayed» состоит в том, что метод «runOnUiThread» выполняет код в основном потоке, тогда как метод «postDelayed» позволяет отложить выполнение кода на определенный промежуток времени, но не блокирует основной поток.  В последовательности запуска этих методов нет четкой зависимости. Метод runOnUiThread может быть использован внутри метода post, чтобы обновить UI в основном потоке. Метод postDelayed может быть использован после вызова runOnUiThread, чтобы отложить обновление элементов пользовательского интерфейса на некоторое время.");
    }
}