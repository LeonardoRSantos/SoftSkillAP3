package com.example.soft.activity;



import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.soft.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class CadastrarActivity extends AppCompatActivity {

    private EditText inputEmail, inputSenha, inputCelular, inputNome;
    private Button btnEntrar, btnCadastrar, btnResetarSenha;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    public void onBackPressed() {
        Toast.makeText(CadastrarActivity.this, "Botão voltar pressionado!", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnEntrar = findViewById(R.id.sign_in_button);
        btnCadastrar = findViewById(R.id.sign_up_button);
        inputEmail = findViewById(R.id.editText);
        inputSenha = findViewById(R.id.editText2);
        inputCelular = findViewById(R.id.editText3);
        inputNome = findViewById(R.id.name);
        progressBar = findViewById(R.id.progressBar);
        btnResetarSenha = findViewById(R.id.btn_reset_password);

        btnResetarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CadastrarActivity.this, RedefinirSenhaActivity.class));
            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CadastrarActivity.this,LoginActivity.class));
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = inputNome.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String senha = inputSenha.getText().toString().trim();
                String celular = inputCelular.getText().toString().trim();

                if(celular.length()!=10)
                {
                    Toast.makeText(getApplicationContext(),"Informar um número valido",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(nome))
                {
                    Toast.makeText(getApplicationContext(),"Informe o seu nome!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Informe o seu E-mail!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(senha)) {
                    Toast.makeText(getApplicationContext(), "Digite a senha!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(celular))
                {
                    Toast.makeText(getApplicationContext(),"Informe seu número",Toast.LENGTH_SHORT).show();
                }

                if (senha.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Senha curta, digite no mínimo 6 caracteres!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, senha)
                        .addOnCompleteListener(CadastrarActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(CadastrarActivity.this, "E-mail criado com sucesso!" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                                if (!task.isSuccessful()) {
                                    Toast.makeText(CadastrarActivity.this, "Falha ao se cadastrar!" + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(CadastrarActivity.this, LoginActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

}