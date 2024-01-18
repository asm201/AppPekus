package com.example.apppekus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String CALCULO_TABLE = "CALCULO_TABLE";
    public static final String COLUNA_VALOR_1 = "VALOR_1";
    public static final String COLUNA_OPERACAO = "OPERACAO";
    public static final String COLUNA_VALOR_2 = "VALOR_2";
    public static final String COLUNA_RESULTADO = "RESULTADO";
    public static final String COLUNA_DATA = "DATA";
    public static final String COLUNA_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "calculo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String criarTabela = "CREATE TABLE " + CALCULO_TABLE + " (" + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUNA_VALOR_1 + " TEXT, " + COLUNA_OPERACAO + " TEXT, " + COLUNA_VALOR_2 + " TEXT, " + COLUNA_RESULTADO + " TEXT, " + COLUNA_DATA + " TEXT)";

        db.execSQL(criarTabela);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(CalculoModel calculoModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUNA_VALOR_1, calculoModel.getValor1());
        cv.put(COLUNA_OPERACAO, calculoModel.getOperacao());
        cv.put(COLUNA_VALOR_2, calculoModel.getValor2());
        cv.put(COLUNA_RESULTADO, calculoModel.getResultado());
        cv.put(COLUNA_DATA, calculoModel.getData());

        long insert = db.insert(CALCULO_TABLE, null, cv);

        if (insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public List<CalculoModel> getAll(){
       List<CalculoModel> retornaLista = new ArrayList<>();

       String queryString = "SELECT * FROM " + CALCULO_TABLE;

       SQLiteDatabase db = this.getReadableDatabase();

       Cursor cursor = db.rawQuery(queryString, null);

       if(cursor.moveToFirst()){
           do{
               int calculoId = cursor.getInt(0);
               String calculoValor1 = cursor.getString(1);
               String calculoOperacao = cursor.getString(2);
               String calculoValor2 = cursor.getString(3);
               String calculoResultado = cursor.getString(4);
               String calculoData = cursor.getString(5);

               CalculoModel newCalculo = new CalculoModel(calculoId,calculoValor1, calculoOperacao, calculoValor2, calculoResultado,calculoData);
               retornaLista.add(newCalculo);
           } while (cursor.moveToNext());

       }else{

       }

       cursor.close();
       db.close();
       return retornaLista;
    }
}
