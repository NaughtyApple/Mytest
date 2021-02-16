package com.example.littletest.sortTest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.littletest.R;

public class SortTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_test);

        int[] arr = new int[]{5, 2, 3, 1, 4};
        int[] resutl = MaoPaoSort(arr);

        for(int i = 0; i< resutl.length; i++){
            Log.i("ldld",i + " 位置的...." + resutl[i] );
        }

    }

    public int[] MaoPaoSort(int[] arr){
        for(int i=0; i< arr.length; i++){
            boolean flag = true;
            for(int j = 0; j< arr.length-1-i; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    flag = false;
                }
            }
            if(flag){
                Log.i("ldld", " 冒泡排序就次跳出...."  );
                break;
            }
        }
        return arr;
    }

    public int[] QuickSort(int[] arr){

        return arr;
    }
}