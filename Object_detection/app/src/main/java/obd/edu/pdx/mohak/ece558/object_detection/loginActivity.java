package obd.edu.pdx.mohak.ece558.object_detection;



import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static android.widget.Toast.makeText;


public class loginActivity extends AppCompatActivity {
    RelativeLayout rellay2;
    private EditText Email_id;
    private EditText Password;
    private Button Login_now;
    private Button Sign_Up;
    private int counter = 5;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        rellay2 = findViewById(R.id.rellay2);
        rellay2.setVisibility(View.VISIBLE);
        mAuth = FirebaseAuth.getInstance();

        Email_id = findViewById(R.id.email_id);
        Password = findViewById(R.id.Password);
        Login_now =findViewById(R.id.login_button);
        Sign_Up =findViewById(R.id.Signup);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        Login_now.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
               // validate(Email_id.getText().toString(), Password.getText().toString());
                userLogin();
            }
        });

        Sign_Up.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginActivity.this, signupActivity.class);
                startActivity(intent);

            }
        });
    }

    private void userLogin() {
        String email = Email_id.getText().toString().trim();
        String password = Password.getText().toString().trim();

        if (email.isEmpty()) {
            Email_id.setError("Email is required");
            Email_id.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Email_id.setError("Please enter a valid email");
            Email_id.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            Password.setError("Password is required");
            Password.requestFocus();
            return;
        }

        if (Password.length() < 6) {
            Password.setError("Minimum lenght of password should be 6");
            Password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    finish();
                    Intent intent = new Intent(loginActivity.this, CameraActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void validate(String userName, String userPassword){
        if((userName.equals("Admin")) && (userPassword.equals("1234"))){
            Intent intent = new Intent(loginActivity.this, CameraActivity.class);
            startActivity(intent);
        }else{
            counter--;

            if(counter == 0){
                Login_now.setEnabled(false);
                Toast toast = makeText(loginActivity.this, "Try after 5 min", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
                toast.show();
            }
            else{
                Toast toast = makeText(loginActivity.this, "Wrong username or password", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
                toast.show();
            }
        }
    }
}
