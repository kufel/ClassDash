package csit515.classdash;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rixoro on 4/11/2017.
 */

public class ForumPostAdapter extends ArrayAdapter<ForumPost> {
    public ForumPostAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ForumPost> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.forum_post, parent, false);

        TextView body = (TextView) convertView.findViewById(R.id.fp_body);
        TextView author = (TextView) convertView.findViewById(R.id.fp_author);

        ForumPost fp = getItem(position);
        body.setText(fp.getBody());
        author.setText(fp.getAuthor());

        return convertView;
    }
}
