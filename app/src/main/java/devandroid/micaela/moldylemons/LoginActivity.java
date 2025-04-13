package devandroid.micaela.moldylemons;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private EditText editLogin, editPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FakeData.inserirCasaisDeTesteSeBancoEstiverVazio(this);

        editLogin = findViewById(R.id.edit_login);
        editPassword = findViewById(R.id.edit_password);
        btnLogin = findViewById(R.id.btn_login);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        btnLogin.setOnClickListener(v -> {
            String login = editLogin.getText().toString().trim();
            String password = editPassword.getText().toString().trim();
            loginViewModel.login(login, password);
        });


        loginViewModel.getLoginResult().observe(this, couple -> {
            if (couple != null) {
                Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Login ou senha inválidos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
