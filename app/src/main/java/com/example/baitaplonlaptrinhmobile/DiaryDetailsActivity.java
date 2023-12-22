package com.example.baitaplonlaptrinhmobile;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Date;



public class DiaryDetailsActivity extends AppCompatActivity {
    EditText titleEditText,contentEditText;
    ImageButton saveDiaryBtn;
    TextView pageTitleTextView;
    String title,content,docId;
    boolean isEditMode = false;
    TextView deleteDiaryTextViewBtn;
    private View deleteDairyTextViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_details);

        titleEditText = findViewById(R.id.diarys_title_text);
        contentEditText = findViewById(R.id.diarys_content_text);
        saveDiaryBtn = findViewById(R.id.save_diary_btn);
        pageTitleTextView = findViewById(R.id.page_title);
        deleteDiaryTextViewBtn  = findViewById(R.id.delete_diary_text_view_btn);

        //receive data
        title = getIntent().getStringExtra("title");
        content= getIntent().getStringExtra("content");
        docId = getIntent().getStringExtra("docId");

        if(docId!=null && !docId.isEmpty()){
            isEditMode = true;
        }

        titleEditText.setText(title);
        contentEditText.setText(content);
        if(isEditMode){
            pageTitleTextView.setText("Edit your diary");
            deleteDiaryTextViewBtn.setVisibility(View.VISIBLE);
        }

        saveDiaryBtn.setOnClickListener( (v)-> saveDiary());
        deleteDairyTextViewBtn.setOnClickListener((v)->deleteDairyFromFirebase());


    }



    void saveDiary(){
        String diaryTitle = titleEditText.getText().toString();
        String diaryContent = contentEditText.getText().toString();
        if(diaryTitle==null || diaryTitle.isEmpty() ){
            titleEditText.setError("Title is required");
            return;
        }
        Diary  diary = new Diary();
        diary.setTitle(diaryTitle);
        diary.setContent(diaryContent);
        diary.setTimestamp(new Timestamp(new Date()));
        saveDiaryToFirebase(diary);



    }

    private void saveDiaryToFirebase(Diary diary) {

        DocumentReference documentReference;
        if(isEditMode){
            //update the diary
            documentReference = Utility.getCollectionReferenceForDiarys().document(docId);
        }else{
            //create new diary
            documentReference = Utility.getCollectionReferenceForDiarys().document();
        }



        documentReference.set(diary).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    //Diary is added
                    Utility.showToast(DiaryDetailsActivity.this,"Diary added successfully");
                    finish();
                }else{
                    Utility.showToast(DiaryDetailsActivity.this,"Failed while adding diary");
                }
            }
        });

    }

    private void deleteDairyFromFirebase() {

        DocumentReference documentReference;
        documentReference = Utility.getCollectionReferenceForDiarys().document(docId);
        documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    // is deleted
                    Utility.showToast(DiaryDetailsActivity.this,"Diary deleted successfully");
                    finish();
                }else{
                    Utility.showToast(DiaryDetailsActivity.this,"Failed while deleting diary");
                }
            }
        });
    }

}