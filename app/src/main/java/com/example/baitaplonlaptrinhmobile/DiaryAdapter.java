package com.example.baitaplonlaptrinhmobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class DiaryAdapter extends FirestoreRecyclerAdapter<Diary, DiaryAdapter.DiaryViewHolder> {

    private final MainActivity context;

    public DiaryAdapter(@NonNull FirestoreRecyclerOptions<Diary> options, MainActivity context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull DiaryViewHolder holder, int position, @NonNull Diary model) {
        holder.titleTextView.setText(model.getTitle()); // Giả sử có getter cho title và content trong lớp Diary
        holder.contentTextView.setText(model.getContent());
        holder.timestampTextView.setText(Utility.timestampToString(model.getTimestamp()));
    }


    @NonNull
    @Override
    public DiaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_diary_item,parent,false);
        return new DiaryViewHolder(view);
    }

    class DiaryViewHolder extends RecyclerView.ViewHolder{


        TextView titleTextView,contentTextView,timestampTextView;
        public DiaryViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.diarys_title_text);
            contentTextView = itemView.findViewById(R.id.diary_content_text_view);
            timestampTextView = itemView.findViewById(R.id.diarys_title_text);
        }
    }
}