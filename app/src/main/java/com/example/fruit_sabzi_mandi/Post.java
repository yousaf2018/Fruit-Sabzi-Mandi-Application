package com.example.fruit_sabzi_mandi;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.fruit_sabzi_mandi.models.todayDealsModelClass;
import com.example.fruit_sabzi_mandi.models.UsersDataHolder;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class Post extends Fragment {
    View view;
    Button button;
    Button btnUrdu;
    Button btnEnglish;
    ImageView imageView;
    Button upload;
    Uri imageUri;
    public static final String SHRED_PREF = "sharedPrefs";
    public static final String Save_Email = "email";
    String email,shopName,Location,Contact;
    EditText Title;
    EditText Price;
    String Date;
    private static final int PICK_IMAGE = 1;
    private ProgressBar progressBar;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Deals_Record");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_post, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        button = view.findViewById(R.id.button);
        btnEnglish = view.findViewById(R.id.English);
        btnUrdu = view.findViewById(R.id.Urdu);
        upload = view.findViewById(R.id.upload);
        Title = view.findViewById(R.id.todayDealTitle);
        Price = view.findViewById(R.id.todayDealPrice);
        progressBar = view.findViewById(R.id.progressBar2);
        imageView = view.findViewById(R.id.imageView);
        Price = view.findViewById(R.id.todayDealPrice);
        Title = view.findViewById(R.id.todayDealTitle);
        progressBar.setVisibility(view.INVISIBLE);
        SharedPreferences pref = getActivity().getSharedPreferences(SHRED_PREF, getActivity().MODE_PRIVATE);
        email = pref.getString(Save_Email, "email");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery,"select pictures"),PICK_IMAGE);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery,"select pictures"),PICK_IMAGE);
            }
        });
        LanguageManager languageManager=new LanguageManager(getActivity());
        //When user click on Urdu Button for Language Change
        btnUrdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                languageManager.updateResourse("ur");
                getActivity().recreate();
            }
        });
        //When user click on English Button for Language Change
        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                languageManager.updateResourse("en");
                getActivity().recreate();
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageUri!=null){
                    getShopDetails();
                    UploadDataToFirebase(imageUri);
                }
                else{
                    Toast.makeText(getActivity(),"Please select image",Toast.LENGTH_LONG).show();
                }
            }

            private void UploadDataToFirebase(Uri uri) {
                StorageReference fileRef = reference.child(System.currentTimeMillis()+"."+getFileExtension(imageUri));
                fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                todayDealsModelClass model = new todayDealsModelClass(imageUri.toString(),Title.getText().toString(),Price.getText().toString(),
                                        Location.toString(),Date.toString(), Contact.toString());
                                String key = root.push().getKey();
                                root.child(Contact.toString()).child(key).setValue(model);
                                Toast.makeText(getActivity(),"Deal uploaded successfully",Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(view.INVISIBLE);

                            }
                        });

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        progressBar.setVisibility(view.VISIBLE);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(view.INVISIBLE);
                        Toast.makeText(getActivity(),"Error message "+e.getMessage(),Toast.LENGTH_LONG).show();
                        //Toast.makeText(getActivity(),"Uploading Failed: ShopName:>>"+shopName.toString()+" Location:>>"+Location.toString()+" " +
                               // " Title:>>"+Title.getText().toString()+" Price:>>"+Price.getText().toString()+" Date:>>"+Date.toString()+" Url:>>"+imageUri.toString(),Toast.LENGTH_LONG).show();
                    }
                });


            }

            //Getting shop details for post
            private void getShopDetails() {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

                reference.child(email.replace(".",",")).addListenerForSingleValueEvent(new ValueEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        UsersDataHolder usersDataHolder = dataSnapshot.getValue(UsersDataHolder.class);
                        Date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                        shopName = usersDataHolder.getShopName().toString();
                        Location = usersDataHolder.getLocation().toString();
                        Contact = usersDataHolder.getPhone().toString();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
            //Getting file file extension
            private String getFileExtension(Uri uri){
                ContentResolver cr= getActivity().getContentResolver();
                MimeTypeMap mime = MimeTypeMap.getSingleton();
                return  mime.getExtensionFromMimeType(cr.getType(imageUri));
            }
        });
        return  view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            imageUri = data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),imageUri);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}