package com.example.registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.registro.entidades.User;
import com.example.registro.model.UserDao;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private EditText etDocumento;
    private EditText etUsuario;
    private EditText etNombres;
    private EditText etApellidos;
    private EditText etContraseña;
    private ListView listUsers;

    SQLiteDatabase sqLiteDatabase;

    private Button btnGuardar;
    private Button btnListUser;

    private int documento;
    private String nombres;
    private String apellidos;
    private String usuario;
    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        begin();
        limpiarCampos();
        btnGuardar.setOnClickListener(this::createUser);
        btnListUser.setOnClickListener(this::listUserShow);
    }

    private void capDatos(){
        this.documento = Integer.parseInt(etDocumento.getText().toString());
        this.usuario = etUsuario.getText().toString();
        this.nombres = etNombres.getText().toString();
        this.apellidos = etApellidos.getText().toString();
        this.pass = etContraseña.getText().toString();
    }

    private  void listUserShow(View view){
        UserDao userDao = new UserDao(context, findViewById(R.id.listUsers));
        ArrayList<User> userList = userDao.getUserList();
        ArrayAdapter<User> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
        listUsers.setAdapter(adapter);
    }

    private void createUser(View view) {
        capDatos();
        User user = new User(this.documento, this.usuario, this.nombres, this.apellidos, this.pass);
        UserDao userDao = new UserDao(context,view);
        userDao.insertUser(user);

        //invocar al metodo de listar
        listUserShow(view);
    }

    public void limpiarCampos(){
        etDocumento.setText("");
        etContraseña.setText("");
        etNombres.setText("");
        etUsuario.setText("");
    }

    public void begin(){
        this.etDocumento = findViewById(R.id.etDocumento);
        this.etContraseña = findViewById(R.id.etContraseña);
        this.etNombres = findViewById(R.id.etNombres);
        this.etApellidos = findViewById(R.id.etApellidos);
        this.etUsuario = findViewById(R.id.etUsuario);
        this.listUsers = findViewById(R.id.listUsers);
        this.btnGuardar = findViewById(R.id.btnRegister);
        this.btnListUser = findViewById(R.id.btnListar);
    }
}