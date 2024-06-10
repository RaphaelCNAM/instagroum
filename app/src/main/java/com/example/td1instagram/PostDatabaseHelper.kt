package com.example.td1instagram

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PostDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "posts.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "posts"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USER_ID = "userId"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_BODY = "body"
        private const val COLUMN_IMAGE_URL = "imageUrl"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_NAME ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_USER_ID INTEGER, "
                + "$COLUMN_TITLE TEXT, "
                + "$COLUMN_BODY TEXT, "
                + "$COLUMN_IMAGE_URL TEXT)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addPost(post: Posts): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_USER_ID, post.userId)
        contentValues.put(COLUMN_TITLE, post.title)
        contentValues.put(COLUMN_BODY, post.body)
        contentValues.put(COLUMN_IMAGE_URL, post.imageUrl)
        return db.insert(TABLE_NAME, null, contentValues)
    }

    fun getAllPosts(): List<Posts> {
        val postList = ArrayList<Posts>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val post = Posts(
                    userId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_ID)),
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)),
                    body = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BODY)),
                    imageUrl = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE_URL))
                )
                postList.add(post)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return postList
    }

    fun updatePost(post: Posts): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_USER_ID, post.userId)
            put(COLUMN_TITLE, post.title)
            put(COLUMN_BODY, post.body)
            put(COLUMN_IMAGE_URL, post.imageUrl)
        }
        return db.update(TABLE_NAME, contentValues, "$COLUMN_ID = ?", arrayOf(post.id.toString()))
    }

    fun deletePost(postId: Int): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(postId.toString()))
    }
}