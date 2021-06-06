package com.example.todolist

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
<<<<<<< HEAD
=======
import io.realm.Realm
import io.realm.Sort
import io.realm.kotlin.where
>>>>>>> 7c273e51c6eb266230b07fa0f7482f4751e4ffbf
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    val realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
<<<<<<< HEAD

        // 새 할일 추가
        fab.setOnClickListener {
            startActivity<EditActivity>()
        }
=======
        fab.setOnClickListener {
            startActivity<EditActivity>()
        }

        val realmResult = realm.where<Todo>()
            .findAll()
            .sort("date", Sort.DESCENDING)
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
>>>>>>> 7c273e51c6eb266230b07fa0f7482f4751e4ffbf
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}