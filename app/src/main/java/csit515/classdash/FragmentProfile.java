package csit515.classdash;

import android.app.Fragment;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentProfile.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentProfile extends Fragment {
    private View root;
    private DBHandler mydb;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private Button buttonSave;

    private OnFragmentInteractionListener mListener;

    public FragmentProfile() {
        // Required empty public constructor
    }

    private void setupFrag() {
        mydb = new DBHandler(getActivity());
        firstName = (EditText) root.findViewById(R.id.editTextFirstName);
        lastName = (EditText) root.findViewById(R.id.editTextLastName);
        email = (EditText) root.findViewById(R.id.editTextEmail);
        password = (EditText) root.findViewById(R.id.editTextPassword);
        buttonSave = (Button) root.findViewById(R.id.buttonSave);
    }

    private void loadSQL() {
        mydb.insertUser("Nick", "Garcia", "nick@aol.com", "pass", "", 1);

        Cursor rs = mydb.getUser(1);
        rs.moveToFirst();
        firstName.setText(rs.getString(rs.getColumnIndex(DBHandler.USERS_C_FIRST_NAME)));
        lastName.setText(rs.getString(rs.getColumnIndex(DBHandler.USERS_C_LAST_NAME)));
        email.setText(rs.getString(rs.getColumnIndex(DBHandler.USERS_C_EMAIL)));
        password.setText(rs.getString(rs.getColumnIndex(DBHandler.USERS_C_PASSWORD)));
        if (!rs.isClosed())  {
            rs.close();
        }
    }

    private void onChangeListener() {
        buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mydb.updateUser(1,
                                firstName.getText().toString(),
                                lastName.getText().toString(),
                                email.getText().toString(),
                                password.getText().toString(),
                                "");
            }
        });
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentDashboard.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentProfile newInstance(String param1, String param2) {
        FragmentProfile fragment = new FragmentProfile();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //   mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment_profile, container, false);
        root = rootView;
        setupFrag();
        loadSQL();
        onChangeListener();
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}