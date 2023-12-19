package com.example.baitaplonlaptrinhmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TrangChuActivity extends AppCompatActivity {
    Context context;
    String[] cungName;
    int[] image;

    LayoutInflater inflater;

    public TrangChuActivity (Context context, String[] flowerName, int[] image) {
        this.context = context;
        this.cungName = cungName;
        this.image = image;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);


    }

            public int getCount() {
                return cungName.length;
            }


            public Object getItem(int position) {
                return null;
            }


            public long getItemId(int position) {
                return 0;
            }


            public View getView(int position, View convertView, ViewGroup parent) {

                if (inflater == null)
                    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                if (convertView == null){

                    convertView = inflater.inflate(R.layout.activity_trang_chu,null);

                }

                ImageView imageView = convertView.findViewById(R.id.grid_image);
                TextView textView = convertView.findViewById(R.id.item_name);

                imageView.setImageResource(image[position]);
                textView.setText(cungName[position]);

                return convertView;
            }
        }

