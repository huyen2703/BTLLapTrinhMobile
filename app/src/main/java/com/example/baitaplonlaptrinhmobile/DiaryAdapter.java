package com.example.baitaplonlaptrinhmobile;

import android.content.Intent;
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
        holder.titleTextView.setText(model.getTitle()); // Assuming there are getters for 'title', 'content', and 'timestamp' in the Diary class
        holder.contentTextView.setText(model.getContent());
        holder.timestampTextView.setText(Utility.timestampToString(model.getTimestamp()));

        holder.itemView.setOnClickListener((v) -> {
            Intent intent = new Intent(context, DiaryDetailsActivity.class);
            intent.putExtra("title", model.getTitle());
            intent.putExtra("content", model.getContent());
            String docId = this.getSnapshots().getSnapshot(position).getId();
            intent.putExtra("docId", docId);
            context.startActivity(intent);
        });
    }

    @NonNull
    @Override
    public DiaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_diary_item, parent, false);
        return new DiaryViewHolder(view);
    }

    class DiaryViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView, contentTextView, timestampTextView;

        public DiaryViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.diarys_title_text);
            contentTextView = itemView.findViewById(R.id.diary_content_text_view);
            timestampTextView = itemView.findViewById(R.id.diary_timestamp_text_view);
        }
    }
}