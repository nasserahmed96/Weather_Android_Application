package com.example.nasser.weather;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataModel {
    private SQLiteDatabase db;
    private static String DB_NAME = "Weather_DB";
    private int DB_VERSION = 1;
    private static String CITY_TABLE = "City";
    private static String TEMPER_TABLE = "Temperature";
    private static String ID_COLUMN = "_id";
    private static String NAME_COLUMN = "name";
    private static String NAME_STATEMENT = " VARCHAR(50) NOT NULL UNIQ";
    private static String DATE_COLUMN = "date DATE NOT NULL, ";
    private static String TEMPER_COLUMN = "temperature";
    private static String PRIMARY_KEY_STATEMENT = " INTEGER PRIMARY KEY,";

    public void addCity(City city){
        String query = "INSERT INTO " + CITY_TABLE + "(" + NAME_COLUMN + ") VALUES (" + city.getCityName() + "));";
        db.execSQL(query);
    }

    public void addTemper(Temperature temper, City city){
        String query = "INSERT INTO TABLE " + TEMPER_TABLE + "(" + DATE_COLUMN + "city) VALUES (" + temper.getDate() + "," +
                "(SELECT " + ID_COLUMN + "FROM " + CITY_TABLE + " WHERE " + NAME_COLUMN + "=" + city.getCityName() + ")));";
        db.execSQL(query);
    }


    private class CustomSqLiteOpenHelper extends SQLiteOpenHelper {

        public CustomSqLiteOpenHelper(Context context){
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String newTableQueryString = "CREATE TABLE " + CITY_TABLE + "(" + ID_COLUMN + PRIMARY_KEY_STATEMENT + NAME_COLUMN+
                    NAME_STATEMENT + ");";
            db.execSQL(newTableQueryString);

            newTableQueryString = "CREATE TABLE " + TEMPER_TABLE + "(" + ID_COLUMN + PRIMARY_KEY_STATEMENT + DATE_COLUMN +
                    ", " + TEMPER_COLUMN + "DOUBLE NOT NULL, city INTEGER NOT NULL, FOREIGN KEY(city) REFERENCES " +
                    CITY_TABLE + "("+ ID_COLUMN + "));";
            db.execSQL(newTableQueryString);

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }



}
