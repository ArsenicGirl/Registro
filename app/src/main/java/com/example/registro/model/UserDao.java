package com.example.registro.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

import com.example.registro.entidades.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class UserDao {
    private ManagerDataBase managerDataBase;
    Context context;
    View view;
    User user;

    public UserDao(Context context, View view) {
        this.context = context;
        this.view = view;
        this.managerDataBase = new ManagerDataBase(this.context);
    }

    //Subir usuarios
    public void insertUser(User user){
        try{
            SQLiteDatabase db = managerDataBase.getWritableDatabase();
            if(db != null){
                ContentValues values = new ContentValues();
                values.put("use_document",user.getDocument());
                values.put("use_user",user.getUser());
                values.put("use_name",user.getName());
                values.put("use_lastName",user.getLastName());
                values.put("use_pass",user.getPassword());
                values.put("use_Status","1");
                long cod = db.insert("users",null,values);
                Snackbar.make(this.view,"Se ha registrado el usuario"+cod,Snackbar.LENGTH_LONG).show();
            }else{
                Snackbar.make(this.view,"no se puede registrar el usuario",Snackbar.LENGTH_LONG).show();
            }
        }catch (SQLException e) {
            Log.i("Hubo un error en la base de datos", ""+e);
        }
    }

    //Editar usuarios
    public void UpdateUser(User user){

        try{
            SQLiteDatabase db = managerDataBase.getWritableDatabase();
            if(db != null){
                ContentValues values = new ContentValues();
                values.put("use_document",user.getDocument());
                values.put("use_user",user.getUser());
                values.put("use_name",user.getName());
                values.put("use_lastName",user.getLastName());
                values.put("use_pass",user.getPassword());
                values.put("use_Status","1");

                String selection = "use_document LIKE ?";
                String[] selectionArgs = {user.getDocument().toString()};
                int cod = db.update(
                        ManagerDataBase.TABLE_USERS,
                        values,
                        selection,
                        selectionArgs);
                Snackbar.make(this.view,"Se ha cambiado el usuario con exito"+cod,Snackbar.LENGTH_LONG).show();
            }else{
                Snackbar.make(this.view,"no se puede cambiar el usuario",Snackbar.LENGTH_LONG).show();
            }
        }catch (SQLException e) {
            Log.i("Hubo un error en la base de datos", ""+e);
        }
    }

    //Borrar usuarios
    public void DeleteUser(User user){
        try{
            SQLiteDatabase db = managerDataBase.getWritableDatabase();
            if(db != null){
                ContentValues values = new ContentValues();
                values.put("use_status","0");

                String selection = "use_document LIKE ?";
                String[] selectionArgs = {user.getDocument().toString()};

                int cod = db.delete(ManagerDataBase.TABLE_USERS, selection, selectionArgs);
                Snackbar.make(this.view,"Se ha cambiado el usuario con exito"+cod,Snackbar.LENGTH_LONG).show();
            }else{
                Snackbar.make(this.view,"no se puede cambiar el usuario",Snackbar.LENGTH_LONG).show();
            }
        }catch (SQLException e) {
            Log.i("Hubo un error en la base de datos", ""+e);
        }
    }

    //Busqueda de usuarios
    public ArrayList<User> getUserList(){
        SQLiteDatabase db = managerDataBase.getReadableDatabase();
        String query = "SELECT * FROM users WHERE use_status = 1;";
        ArrayList<User> listaUsuarios = new ArrayList<>();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
           do {
                User user1 = new User();
                user1.setDocument(cursor.getInt(0));
                user1.setUser(cursor.getString(1));
                user1.setName(cursor.getString(2));
                user1.setLastName(cursor.getString(3));
                user1.setPassword(cursor.getString(4));
                listaUsuarios.add(user1);
           }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listaUsuarios;
    }

    public ArrayList<User> getUserFilterDocument(User user){
        SQLiteDatabase db = managerDataBase.getReadableDatabase();
        String query = "SELECT * FROM users WHERE use_status = 1 AND use_document =" + user.getDocument();
        ArrayList<User> listaUsuarios = new ArrayList<>();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                User user1 = new User();
                user1.setDocument(cursor.getInt(0));
                user1.setUser(cursor.getString(1));
                user1.setName(cursor.getString(2));
                user1.setLastName(cursor.getString(3));
                user1.setPassword(cursor.getString(4));
                listaUsuarios.add(user1);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listaUsuarios;
    }

    public ArrayList<User> getUserFilterUser(User user){
        SQLiteDatabase db = managerDataBase.getReadableDatabase();
        String query = "SELECT * FROM users WHERE use_status = 1 AND use_user =" + user.getUser();
        ArrayList<User> listaUsuarios = new ArrayList<>();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                User user1 = new User();
                user1.setDocument(cursor.getInt(0));
                user1.setUser(cursor.getString(1));
                user1.setName(cursor.getString(2));
                user1.setLastName(cursor.getString(3));
                user1.setPassword(cursor.getString(4));
                listaUsuarios.add(user1);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listaUsuarios;
    }
}
