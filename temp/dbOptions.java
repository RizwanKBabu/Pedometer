package com.temp.temp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.stat.stat.AppVariable;

public class dbOptions extends SQLiteOpenHelper {

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "DISASTER";

	// Labels table name
	private static final String TABLE_LABELS = "paths";

	// Labels Table Columns names
	private static final String START = "start";
	private static final String STAMP = "stamp";
	private static final String WAY = "way";

	public dbOptions(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		String CREATE_CONTACTS_TABLE = "create table " + TABLE_LABELS + " ( "
				+ START + " VARCHAR ," + STAMP + " VARCHAR," + WAY
				+ " VARCHAR);";
		db.execSQL(CREATE_CONTACTS_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LABELS);

		// Create tables again
		onCreate(db);

	}

	public boolean insertway() {

		SQLiteDatabase writableDatabase = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(START, AppVariable.start);
		values.put(STAMP, AppVariable.stop);
		values.put(WAY, AppVariable.res);
		long insert = writableDatabase.insert(TABLE_LABELS, null, values);
		writableDatabase.close();
		if (insert > 0) {
			return true;
		} else {
			return false;
		}

	}

	public Contact getway(String startstop) {
		String[] split = startstop.split(" ");
		SQLiteDatabase db;
		Contact contact = null;
		db = this.getReadableDatabase();
		String sql = "SELECT " + "*" + " FROM " + TABLE_LABELS + " WHERE "
				+ START + "='" + split[0] + "' AND " + STAMP + "='" + split[2]
				+ "'";
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {
					contact = new Contact(cursor.getString(cursor
							.getColumnIndex(START)), cursor.getString(cursor
							.getColumnIndex(STAMP)), cursor.getString(cursor
							.getColumnIndex(WAY)));

				} while (cursor.moveToNext());
			}
		}

		// return contact
		return contact;

	}

	public boolean checking(String string, String string2) {
		// TODO Auto-generated method stub
		
		SQLiteDatabase db;
		db = this.getReadableDatabase();
		
		String sql = "SELECT " + "*" + " FROM " + TABLE_LABELS + " WHERE "
				+ START + "='" + string + "' AND " + STAMP + "='" + string2
				+ "'";
		
		Cursor cursor = db.rawQuery(sql, null);
		
		if (cursor.moveToFirst())
		{
			return true;
		}else{
			return false;
		}
	}

	public boolean deleting(String string, String string2) {
		// TODO Auto-generated method stub
		
		SQLiteDatabase db;
		db = this.getWritableDatabase();
		
long insert   =		db.delete(TABLE_LABELS, START +"='" + string +"' AND " + STAMP + "='" + string2 +"'",null);
		
	db.close();
	
	if (insert > 0) {
		return true;
	} else {
		return false;
	}
		
	}

}
