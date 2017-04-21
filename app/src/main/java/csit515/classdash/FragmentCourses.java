package csit515.classdash;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Map;

import csit515.classdash.dto.Item;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentCourses.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentCourses#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCourses extends Fragment {
    private View root;
    private DBHandler mydb;
    private ListView listView;
    ArrayList<Item> list;

    private OnFragmentInteractionListener mListener;

    public FragmentCourses() {
        // Required empty public constructor
    }

    private void setupFrag() {
        mydb = new DBHandler(getActivity());
        listView = (ListView) root.findViewById(R.id.listViewCourses);
    }

    private void loadSQL() {
        list = mydb.getAllClasses();
        String[] listItems = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            listItems[i] = list.get(i).getValue();
        }
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
    }

    private void run() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
                int val = list.get(pos).getId();
                FragmentCourse nextFrag = FragmentCourse.newInstance(val);
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
    public static FragmentCourses newInstance(String param1, String param2) {
        FragmentCourses fragment = new FragmentCourses();
        Bundle args = new Bundle();
        args.putString("COURSE_ID", param1);
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
        View rootView = inflater.inflate(R.layout.fragment_fragment_courses, container, false);
        root = rootView;
        setupFrag();
        loadSQL();
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