package csit515.classdash;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentForum.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentForum#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentForum extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String FORUM_ID = "fid";

    // TODO: Rename and change types of parameters
    private String fid;
    private View root;
    private DBHandler mydb;
    private ListView listView;
    private ArrayList<ForumPost> posts;
    private ForumPostAdapter fpAdapter;
    private String username;
    Button postbtn;
    EditText postbox;

    private OnFragmentInteractionListener mListener;

    public FragmentForum() {
        // Required empty public constructor
    }

    private void setupFrag() {
        mydb = new DBHandler(getActivity());
        listView = (ListView) root.findViewById(R.id.fp_list);
        posts = new ArrayList<>();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Login.SHPR, Context.MODE_APPEND);
        username = sharedPreferences.getString(Login.CURRUSER, "user");
        postbox = (EditText) root.findViewById(R.id.msg_box);
        postbtn = (Button) root.findViewById(R.id.post_btn);
        postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(postbox.getText().toString().length()>0){
                    mydb.insertPost(Integer.parseInt(fid), posts.size(), username, postbox.getText().toString());
                    posts.add(new ForumPost(0, Integer.parseInt(fid), postbox.getText().toString(), username, posts.size()));
                    postbox.setText("");
                }
            }
        });
    }

    private void loadSQL() {
        Cursor f = mydb.getForum(Integer.parseInt(fid));
        f.moveToFirst();
        posts.add(new ForumPost(f.getInt(f.getColumnIndex(DBHandler.FORUMS_C_ID)),
                f.getInt(f.getColumnIndex(DBHandler.FORUMS_C_ID)),
                f.getString(f.getColumnIndex(DBHandler.FORUMS_C_BODY)),
                f.getString(f.getColumnIndex(DBHandler.FORUMS_C_AUTHOR)),
                0));
        TextView title = (TextView) root.findViewById(R.id.forum_title);
        title.setText(f.getString(f.getColumnIndex(DBHandler.FORUMS_C_TITLE)));

        Cursor rs = mydb.getAllPostsofForum(fid);
        rs.moveToFirst();

        while(!rs.isAfterLast()){
            posts.add(new ForumPost(rs.getInt(rs.getColumnIndex(DBHandler.POSTS_C_ID)),
                    rs.getInt(rs.getColumnIndex(DBHandler.POSTS_C_FID)),
                    rs.getString(rs.getColumnIndex(DBHandler.POSTS_C_BODY)),
                    rs.getString(rs.getColumnIndex(DBHandler.POSTS_C_AUTHOR)),
                    rs.getInt(rs.getColumnIndex(DBHandler.POSTS_C_ORDER))));
            rs.moveToNext();
        }

        fpAdapter = new ForumPostAdapter(getActivity(), R.layout.forum_post, posts);
        listView.setAdapter(fpAdapter);
    }

    private void run(){

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment FragmentForum.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentForum newInstance(String param1) {
        FragmentForum fragment = new FragmentForum();
        Bundle args = new Bundle();
        args.putString(FORUM_ID, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fid = getArguments().getString(FORUM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment_forum, container, false);
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
