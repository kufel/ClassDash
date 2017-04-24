package csit515.classdash;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import csit515.classdash.dto.Item;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentCourseAssignment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentCourseAssignment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCourseAssignment extends Fragment {
    private View root;
    private DBHandler mydb;
    private int assignmentId;
    private String assignmentVaue;
    private TextView textViewAssignmentId;
    private TextView textViewAssignment;
    private Item item;

    private OnFragmentInteractionListener mListener;

    public FragmentCourseAssignment() {
        // Required empty public constructor
    }

    private void setupFrag() {
        mydb = new DBHandler(getActivity());
        textViewAssignmentId = (TextView) root.findViewById(R.id.textViewAssignmentId);
        textViewAssignment = (TextView) root.findViewById(R.id.textViewAssignment);
    }

    private void loadSQL() {
        //item = mydb.getAssignmentByAssignmentId(assignmentId);
    }

    private void run() {
        textViewAssignmentId.setText("Assignemnt: " + assignmentId);
        textViewAssignment.setText(assignmentVaue + "");
    }

    private void debug() {
        Toast.makeText(root.getContext().getApplicationContext(),"ID: " + assignmentId, Toast.LENGTH_LONG).show();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param assignmentId Parameter 1.
     * @return A new instance of fragment FragmentDashboard.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCourseAssignment newInstance(int assignmentId, String value) {
        FragmentCourseAssignment fragment = new FragmentCourseAssignment();
        Bundle args = new Bundle();
        args.putInt("ASSIGNMENT_ID", assignmentId);
        args.putString("ASSIGNMENT_VALUE", value);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            assignmentId = getArguments().getInt("ASSIGNMENT_ID");
            assignmentVaue = getArguments().getString("ASSIGNMENT_VALUE");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment_assignment, container, false);
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