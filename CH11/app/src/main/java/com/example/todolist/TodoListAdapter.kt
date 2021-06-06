package com.example.todolist

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection

class TodoListAdapter(realmResult: OrderedRealmCollection<Todo>): RealmBaseAdapter<Todo>(realmResult){


}

open class RealmBaseAdapter<T>(realmResult: OrderedRealmCollection<T>) {

}
