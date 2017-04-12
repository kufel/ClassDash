package csit515.classdash;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentDiscussions.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentDiscussions#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDiscussions extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View root;
    private DBHandler mydb;
    private ListView listView;
    ArrayList<String> keyList;

    private OnFragmentInteractionListener mListener;

    public FragmentDiscussions() {
        // Required empty public constructor
    }

    private void setupFrag() {
        mydb = new DBHandler(getActivity());
        listView = (ListView) root.findViewById(R.id.discussion_topics);
        keyList = new ArrayList<>();
    }

    private void loadSQL() {
        keyList.clear();
        HashMap<String, String> map = mydb.getAllForums();
        ArrayList<String> listItems = new ArrayList<>();
        for (String key : map.keySet()) {
            listItems.add(map.get(key));
            keyList.add(key);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
    }

    private void run() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
                FragmentForum nextFrag = FragmentForum.newInstance(keyList.get(pos));
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.mainFrame, nextFrag, null)
                        .addToBackStack(null)
                        .commit();
            }
        });
        Button newForumBtn = (Button) root.findViewById(R.id.new_forum_btn);
        newForumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View viewGrp = getActivity().getLayoutInflater().inflate(R.layout.new_forum_form, (ViewGroup) root.findViewById(R.id.discussion_frame), false);
                final View thisview = v;

                final EditText title = (EditText) viewGrp.findViewById(R.id.new_title);
                final EditText body = (EditText) viewGrp.findViewById(R.id.new_body);
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity())
                        .setTitle("New Topic").setView(viewGrp)
                        .setPositiveButton("Post", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Login.SHPR, Context.MODE_APPEND);
                                mydb.insertForum(sharedPreferences.getString(Login.CURRUSER, "user"), title.getText().toString(), body.getText().toString());
                                loadSQL();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Snackbar.make(thisview, "Topic Cancelled", Snackbar.LENGTH_SHORT).show();
                            }
                        });
                alertBuilder.show();
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
    public static FragmentDiscussions newInstance(String param1, String param2) {
        FragmentDiscussions fragment = new FragmentDiscussions();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment_discussions, container, false);
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