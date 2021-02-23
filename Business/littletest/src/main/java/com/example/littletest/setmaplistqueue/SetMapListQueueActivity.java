package com.example.littletest.setmaplistqueue;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.littletest.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeSet;

public class SetMapListQueueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_map_list_queue);

        //HashSet
        HashSet set = new HashSet();
        for(int i = 0; i<10; i++){
            set.add("HashSet "+ String.valueOf(i));
        }

        Iterator iterator = set.iterator();
        while ( iterator.hasNext()){
            Log.i("ldld", "set iterator...."+  (String) iterator.next() );
        }

        //TreeSet
        TreeSet treeSet = new TreeSet();
        for(int i = 15; i>0; i--){
            treeSet.add("TreeSet "+ String.valueOf(i));
        }

        Iterator treeSetiterator = treeSet.iterator();
        while ( treeSetiterator.hasNext()){
            Log.i("ldld", "treeSetiterator iterator...."+  (String) treeSetiterator.next() );
        }

        //HashMap
        HashMap map = new HashMap();
        LinkedHashMap linkedHashMap = new LinkedHashMap();

        //List
        ArrayList arrayList = new ArrayList();
        LinkedList linkedList = new LinkedList();

        //这里还有一个优先级队列...
        ArrayList<Integer> arraglistPriorityQueue = new ArrayList<Integer>();
        Queue<Integer> priorityQueue = new PriorityQueue<Integer>();
        priorityQueue.add(3);
        priorityQueue.add(1);
        priorityQueue.add(2);
        priorityQueue.add(5);
        priorityQueue.add(4);

        Iterator iterator1 = priorityQueue.iterator();
        while (iterator1.hasNext()){
            Log.i("ldld","priotity content:"+ iterator1.next() );
        }

        //!!!!!!!!!!!!!!!!! 这里iterator读出来还不是有序的，要poll出来才是。。。
        while (!priorityQueue.isEmpty()){
            arraglistPriorityQueue.add(priorityQueue.poll());
        }

        for(int i=0;i<arraglistPriorityQueue.size();i++){
            Log.i("ldld","arraglistPriorityQueue priotity content:"+ arraglistPriorityQueue.get(i) );
        }

        //Queue
        Queue<Integer> queue = new Queue<Integer>() {
            @Override
            public boolean add(Integer integer) {
                return false;
            }

            @Override
            public boolean offer(Integer integer) {
                return false;
            }

            @Override
            public Integer remove() {
                return null;
            }

            @Nullable
            @Override
            public Integer poll() {
                return null;
            }

            @Override
            public Integer element() {
                return null;
            }

            @Nullable
            @Override
            public Integer peek() {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(@Nullable Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<Integer> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] a) {
                return null;
            }

            @Override
            public boolean remove(@Nullable Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends Integer> c) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };

        findViewById(R.id.exe_calculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


}