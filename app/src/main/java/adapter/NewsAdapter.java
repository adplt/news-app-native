package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.newsapp.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import java.util.List;
import model.NewsModel;
import variables.CommonVariable;

public class NewsAdapter extends ArrayAdapter<NewsModel> {

    private List<NewsModel> newsList;
    private LayoutInflater li;
    private ProgressBar pb;

    public NewsAdapter(Context context, int resource, List<NewsModel> newsList){
        super(context, resource, newsList);
        this.newsList=newsList;
        li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        convertView=li.inflate(R.layout.interface_news_list,null);

        TextView title = convertView.findViewById(R.id.title);
        TextView url = convertView.findViewById(R.id.url);
        TextView desc = convertView.findViewById(R.id.desc);

        CircularImageView civ = convertView.findViewById(R.id.news_image);
        pb = convertView.findViewById(R.id.pg);

        title.setText(newsList.get(position).getTitle());
        url.setText(newsList.get(position).getUrl());
        desc.setText(newsList.get(position).getDescription());

        String urlImage = CommonVariable.DEFAULT_IMAGE;
        if (!newsList.get(position).getUrlToImage().equalsIgnoreCase("null")) urlImage = newsList.get(position).getUrlToImage();

        ImageLoader.getInstance().displayImage(urlImage, civ, new ImageLoadingListener() {

            @Override
            public void onLoadingStarted(String imageUri, View view) {
                pb.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                pb.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                pb.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                pb.setVisibility(View.GONE);
            }

        });

        return convertView;
    }
}
