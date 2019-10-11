package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.newsapp.R;
import java.util.List;
import model.SourceModel;

public class SourceAdapter extends ArrayAdapter<SourceModel> {

    private List<SourceModel> sourceList;
    private LayoutInflater li;

    public SourceAdapter(Context context, int resource, List<SourceModel> source_list) {
        super(context, resource, source_list);
        this.sourceList=source_list;
        li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        convertView=li.inflate(R.layout.interface_source_list,null);

        if (sourceList.size() != 0) {
            TextView title = convertView.findViewById(R.id.title);
            TextView desc = convertView.findViewById(R.id.desc);

            title.setText(sourceList.get(position).getName());
            desc.setText(sourceList.get(position).getCategory());
        }

        return convertView;
    }

    public void filterList (List<SourceModel> newSource) {
        sourceList = newSource;
        notifyDataSetChanged();
    }
}
