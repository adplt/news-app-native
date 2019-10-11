package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.newsapp.R;
import java.util.List;
import model.SourceModel;

public class CategoryAdapter extends ArrayAdapter<SourceModel> {

    private List<SourceModel> sourceList;
    private LayoutInflater li;

    public CategoryAdapter(Context context, int resource, List<SourceModel> sourceList) {
        super(context, resource, sourceList);
        this.sourceList=sourceList;
        li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView=li.inflate(R.layout.interface_category_list,null);

        TextView title = convertView.findViewById(R.id.title);
        title.setText(sourceList.get(position).getCategory());

        return convertView;
    }
}
