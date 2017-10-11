package app.kit.inv.easyads.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
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
                    myAlert.myDialog("Have Space","Please Fill All Every Blank");

                }

                }  // onClick
        });


    }

} // Main Class
