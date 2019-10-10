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

public class NewsAdapter extends ArrayAdapter<NewsModel> {

    private List<NewsModel> news_list;
    private LayoutInflater li;
    private ProgressBar pb;

    public NewsAdapter(Context context, int resource, List<NewsModel> news_list){
        super(context, resource, news_list);
        this.news_list=news_list;
        li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        convertView=li.inflate(R.layout.interface_news_list,null);

        TextView title = convertView.findViewById(R.id.title);
        TextView url = convertView.findViewById(R.id.url);
        TextView desc = convertView.findViewById(R.id.desc);

        CircularImageView civ = convertView.findViewById(R.id.news_image);
        pb = (ProgressBar) convertView.findViewById(R.id.pg);

        title.setText(news_list.get(position).getTitle());
        url.setText(news_list.get(position).getUrl());
        desc.setText(news_list.get(position).getDescription());

        String urlImage;
        if (news_list.get(position).getDescription() == null) urlImage = "//mipmap:" + R.mipmap.ic_launcher;
        else urlImage = news_list.get(position).getUrlToImage();

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
