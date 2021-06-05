package com.juniorteste.androidsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// classe que auxilia na conexao com bd
public class Conexao extends SQLiteOpenHelper {

    private static final String name = "banco.db";
    private static final int version = 1;


    public Conexao (Context context){
        super(context,name, null, version);
    }

    @Override //metodo cria banco e tabelas
    public void onCreate(SQLiteDatabase db) {
        // inserindo comando sql na criação
        db.execSQL("create table aluno(id integer primary key autoincrement," +
                "nome varchar(50), cpf varchar(50), telefone varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
