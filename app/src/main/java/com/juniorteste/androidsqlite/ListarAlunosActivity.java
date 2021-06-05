package com.juniorteste.androidsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class ListarAlunosActivity extends AppCompatActivity {

    private ListView listView;
    private AlunoDAO dao;
    private List<Aluno> alunos;
    private List<Aluno> alunosFiltrados = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_alunos);

        //vincular variavel "listView" com o id do objeto lista_alunos do layout criado
        listView = findViewById(R.id.lista_alunos);
        //vinculando o context da activity atual com o Data Acess Object
        dao = new AlunoDAO(this);

        // pegando todos os alunos
        alunos = dao.obterTodos();
        // pegando somente os alunos que foram consultados
        alunosFiltrados.addAll(alunos);

        // Usando adaptador do Android para adaptar
        // alunosFiltrados e colocar em listView (objeto generico do layout)
        // o array adapter sempre recebe o contexto, o formato de exibição e a lista
        ArrayAdapter<Aluno> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunosFiltrados);
        // setando a listView com o adaptador criado
        listView.setAdapter(adaptador);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflador = getMenuInflater();
        inflador.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public void cadastrar (MenuItem item){
        // A intenção recebe o que quer abrir e o local
        Intent tela = new Intent(this, CadastroAlunoActivity.class);
        startActivity(tela);
    }

    @Override
    public void onResume(){
        super.onResume();
        // a variavel do tipo lista recebe novamente todos os dados
        alunos = dao.obterTodos();
        // limpando a lista dos filtrados
        alunosFiltrados.clear();
        // alunosFiltrados recebe todos os dados novamente
        alunosFiltrados.addAll(alunos);
        // invalidando dados antigos da listview
        listView.invalidateViews();
    }


}