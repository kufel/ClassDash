package csit515.classdash;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentCourse.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentCourse#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCourse extends Fragment {
    private View root;
    private DBHandler mydb;
    private ListView listViewAssignments;
    private ListView listViewTutoring;

    private OnFragmentInteractionListener mListener;

    public FragmentCourse() {
        // Required empty public constructor
    }

    private void setupFrag() {
        mydb = new DBHandler(getActivity());
        listViewAssignments = (ListView) root.findViewById(R.id.listViewAssignments);
        listViewTutoring = (ListView) root.findViewById(R.id.listViewTutoring);
    }

    private void run() {
        TabHost mTabHost = (TabHost) root.findViewById(R.id.tabHost);
        mTabHost.setup();

        // Lets add the first Tab
        TabHost.TabSpec mSpec = mTabHost.newTabSpec("Assignments");
        mSpec.setContent(R.id.first_Tab);
        mSpec.setIndicator("Assignments");
        mTabHost.addTab(mSpec);

        // Lets add the second Tab
        mSpec = mTabHost.newTabSpec("Tutoring");
        mSpec.setContent(R.id.second_Tab);
        mSpec.setIndicator("Tutoring");
        mTabHost.addTab(mSpec);

        ArrayList<String> listAssigments = mydb.getAllClasses();
        String[] listItemsAssignments = new String[listAssigments.size()];
        for (int i = 0; i < listAssigments.size(); i++) {
            listItemsAssignments[i] = listAssigments.get(i);
        }
        ArrayAdapter adapterAssignments = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listItemsAssignments);
        listViewAssignments.setAdapter(adapterAssignments);

        ArrayList<String> listTutoring = mydb.getAllClasses();
        String[] listItemsTutoring = new String[listTutoring.size()];
        for (int i = 0; i < listTutoring.size(); i++) {
            listItemsTutoring[i] = listTutoring.get(i);
        }
        ArrayAdapter adapterTutoring = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listItemsTutoring);
        listViewTutoring.setAdapter(adapterTutoring);

        listViewAssignments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                FragmentCourseAssignment nextFrag = new FragmentCourseAssignment();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.mainFrame, nextFrag, null)
                        .addToBackStack(null)
                        .commit();
            }
        });

        listViewTutoring.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                FragmentCourseTutoring nextFrag = new FragmentCourseTutoring();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.mainFrame, nextFrag, null)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentDashboard.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCourse newInstance(String param1, String param2) {
        FragmentCourse fragment = new FragmentCourse();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment_course, container, false);
        root = rootView;
        setupFrag();
        run();
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