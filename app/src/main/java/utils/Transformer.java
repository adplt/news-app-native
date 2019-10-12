package utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import model.SourceModel;

public class Transformer {
    public List<SourceModel> filterByKeyword (List<SourceModel> sources, String keyword){
        List<SourceModel> newSource = new ArrayList<>();

        if (keyword.length() == 0) newSource = sources;
        else {
            for (SourceModel source : sources) {
                if (source.getName().toLowerCase().contains(keyword.toLowerCase())) {
                    newSource.add(source);
                }
            }
        }

        return newSource;
    }

    public List<SourceModel> filterByCategory (List<SourceModel> sources, String category){
        List<SourceModel> newSource = new ArrayList<>();
        for (SourceModel source : sources) {
            if (source.getCategory().toLowerCase().contains(category.toLowerCase())) {
                newSource.add(source);
            }
        }

        return newSource;
    }

    public List<SourceModel> filterCategory (List<SourceModel> sources){
        List<SourceModel> newSources = new ArrayList<>();
        for (int i=0; i < sources.size(); i++) {
            for (int j=0; j < newSources.size(); j++) {
                if (!newSources.get(i).getCategory().toLowerCase().equalsIgnoreCase(sources.get(i).getCategory().toLowerCase())) newSources.add(sources.get(i));
            }
        }

        return newSources;
    }
}


