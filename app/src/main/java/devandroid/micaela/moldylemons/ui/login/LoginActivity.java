package devandroid.micaela.moldylemons.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import devandroid.micaela.moldylemons.MainActivity;
import devandroid.micaela.moldylemons.R;
import devandroid.micaela.moldylemons.util.SessionManager;
import devandroid.micaela.moldylemons.util.FakeData;


public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private EditText editLogin, editPassword;
    private Button btnLogin;
    private TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FakeData.inserirCasaisDeTesteSeBancoEstiverVazio(this);

        this.editLogin = findViewById(R.id.edit_login);
        this.editPassword = findViewById(R.id.edit_password);
        this.btnLogin = findViewById(R.id.btn_login);
        this.errorMessage = findViewById(R.id.error_message);

        this.loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        this.btnLogin.setOnClickListener(v -> {
            String login = editLogin.getText().toString().trim();
            String password = editPassword.getText().toString().trim();
            loginViewModel.login(login, password);
        });


        this.loginViewModel.getLoginResult().observe(this, couple -> {
            if (couple != null) {
                errorMessage.setText("Login feito com sucesso!");
                errorMessage.setTextColor(getResources().getColor(R.color.green));
                errorMessage.setVisibility(View.VISIBLE);
                SessionManager sessionManager = new SessionManager(this);
                sessionManager.saveSession(couple.getId(), couple.getLogin());

                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                errorMessage.setVisibility(View.VISIBLE);
            }
        });
    }
}
