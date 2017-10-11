package app.kit.inv.easyads.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import app.kit.inv.easyads.MainActivity;
import app.kit.inv.easyads.R;
import app.kit.inv.easyads.utility.MyAlert;

/**
 * Created by user on 10/10/2560.
 */

public class RegisterFragment extends Fragment {

    // Explicit
    private String nameString, uscrString, passwordString;
    private Uri uri;
    private ImageView imageView;
    private boolean aBoolean = true;
    private String tag = "11octV1";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        return view;
    } // coCreateView

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Toolbar Contoller
        toolbarContoller();

//        Human controller
        humanController();

    } // Main Method

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== getActivity().RESULT_OK) {



            Log.d(tag, "Result OK");
            aBoolean = false;

            try {
                uri = data.getData();
                Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri));
                imageView.setImageBitmap(bitmap);

            } catch (Exception e) {

                Log.d(tag, "e==>" + e.toString());
            }


        } // if


    } // onActivityResult

    private void humanController() {
         imageView = (ImageView) getView().findViewById(R.id.imvHumen);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent.createChooser(intent, "Please Choose App"), 1);


            } // On Click
        });
    }

    private void toolbarContoller() {
        // Config Toolbar
        Toolbar toolbar = (Toolbar) getView().findViewById(R.id.toolbarRegister);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        // Setup Title

        ((MainActivity) getActivity()).setTitle(getResources().getString(R.string.new_register));

        // Back Controller
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        // Save Controller
        ImageView imageView = (ImageView) getView().findViewById(R.id.imvSave);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Get Value From EditText
//
                EditText nameEditText = (EditText) getView().findViewById(R.id.edtName);
                EditText userEditText = (EditText) getView().findViewById(R.id.edtUser);
                EditText passwordEditText = (EditText) getView().findViewById(R.id.edtPassword);
//                  Change Data type
//
                nameString = nameEditText.getText().toString().trim();
                uscrString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

//                 Check space
                if (nameString.equals("") || uscrString.equals("") || passwordString.equals("")) {

                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog("Have Space", "Please Fill All Every Blank");

                } else if (aBoolean) {
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog("No Image","Please Choose Image");
                } else {

                    upLoadValueToServer();
                }


            }  // onClick
        });


    }

    private void upLoadValueToServer() {
        // Find Path of Image
        String strPathImage = "";
        String[] strings= new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri,strings,
                null,null,null);
        if (cursor!= null) {
            cursor.moveToFirst();
            int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            strPathImage = cursor.getString(index);


        } else {
            strPathImage = uri.getPath();
        }
        Log.d(tag, "Path of Image ==>" + strPathImage);

    }  // upload

} // Main Class
