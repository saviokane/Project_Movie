package com.maxpayneman.project_movie.Model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class FilmeDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object {
        private const val DATABASE_NAME = "filmeapp.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "filmes"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NOME = "nomeFilme"
        private const val COLUMN_DESCRICAO = "descricaoFilme"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_NOME TEXT, $COLUMN_DESCRICAO TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun adicionarFilme(filme: Filme){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOME, filme.nome)
            put(COLUMN_DESCRICAO, filme.descricao)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }
}