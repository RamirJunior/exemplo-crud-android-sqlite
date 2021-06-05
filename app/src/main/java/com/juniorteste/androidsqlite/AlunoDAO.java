package com.juniorteste.androidsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

// classe responsavel por fazer alterações no bd
public class AlunoDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    //metodo de conexao com bd
    public AlunoDAO (Context context){
        conexao = new Conexao(context); //recebendo a conexao
        banco = conexao.getWritableDatabase(); //recebendo o bd da conexao
    }

    // metodo para inserir no banco
    public long inserir(Aluno aluno){
        ContentValues values = new ContentValues();
        values.put("nome",aluno.getNome()); //associando o nome do objeto recebido com a coluna nome do bd
        values.put("cpf", aluno.getCpf());
        values.put("telefone", aluno.getTelefone());

        //metodo insert recebe a tabela, recebe um valor p/ colunas nulas
        // e recebe os valores que serão inseridos no banco
        // metodo insert retorna id do aluno inserido
        return banco.insert("aluno",null, values);
    }

    public List<Aluno> obterTodos(){
        List<Aluno> alunos = new ArrayList<>();
        Cursor cursor = banco.query("aluno", new String[]{"id","nome","cpf","telefone"},
                null, null, null, null, null);
        while(cursor.moveToNext()){
            Aluno a = new Aluno();
            a.setId(cursor.getInt(0));
            a.setNome(cursor.getString(1));
            a.setCpf(cursor.getString(2));
            a.setTelefone(cursor.getString(3));
            alunos.add(a);
        }
        return alunos;
    }

}
