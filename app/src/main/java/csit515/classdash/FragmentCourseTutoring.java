package csit515.classdash;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import csit515.classdash.dto.Item;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentCourseTutoring.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentCourseTutoring#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCourseTutoring extends Fragment {
    private View root;
    private DBHandler mydb;
    private int tutoringId;
    private TextView textViewTutoringId;
    private ListView listViewFile;
    ArrayList<Item> listFiles;

    private OnFragmentInteractionListener mListener;

    public FragmentCourseTutoring() {
        // Required empty public constructor
    }

    private void setupFrag() {
        mydb = new DBHandler(getActivity());
        textViewTutoringId = (TextView) root.findViewById(R.id.textViewTutoringId);
        listViewFile = (ListView) root.findViewById(R.id.listViewFile);
    }

    private void loadSQL() {
    }

    private void run() {
        textViewTutoringId.setText("Tutorial: " + tutoringId);

        listFiles = mydb.getAllFilesByTutoringId(tutoringId);
        String[] listItemsFiles = new String[listFiles.size()];
        for (int i = 0; i < listFiles.size(); i++) {
            listItemsFiles[i] = listFiles.get(i).getValue();
        }
        ArrayAdapter adapterAssignments = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listItemsFiles);
        listViewFile.setAdapter(adapterAssignments);

        listViewFile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
                Toast.makeText(root.getContext().getApplicationContext(), "Loading...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void debug() {
        //Toast.makeText(root.getContext().getApplicationContext(),"ID: " + tutoringId, Toast.LENGTH_LONG).show();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param tutoringId Parameter 1.
     * @return A new instance of fragment FragmentDashboard.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCourseTutoring newInstance(int tutoringId) {
        FragmentCourseTutoring fragment = new FragmentCourseTutoring();
        Bundle args = new Bundle();
        args.putInt("TUTORING_ID", tutoringId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tutoringId = getArguments().getInt("TUTORING_ID");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment_tutoring, container, false);
        root = rootView;
        setupFrag();
        loadSQL();
        run();
        //debug();
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