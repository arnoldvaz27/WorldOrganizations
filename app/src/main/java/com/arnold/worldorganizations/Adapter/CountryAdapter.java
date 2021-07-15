package com.arnold.worldorganizations.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arnold.worldorganizations.R;
import com.arnold.worldorganizations.JavaClasses.ImageFetch;
import com.arnold.worldorganizations.entites.Country;
import com.arnold.worldorganizations.listeners.CountryListeners;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    //declaration of variables
    private final List<Country> countries;
    private final CountryListeners countryListeners;

    //constructor
    public CountryAdapter(List<Country> countries, CountryListeners countryListeners) {
        this.countries = countries;
        this.countryListeners = countryListeners;

    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountryViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_note, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, final int position) {
        holder.setCountry(countries.get(position));

        //onclick listener on the view
        holder.layoutNote.setOnClickListener(v -> countryListeners.onItemClicked(countries.get(position), position));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class CountryViewHolder extends RecyclerView.ViewHolder {

        //declaration
        LinearLayout layoutNote;
        ImageView imageView;
        TextView textView;
        String population,languages;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutNote = itemView.findViewById(R.id.layoutCountry);
            imageView = itemView.findViewById(R.id.imageNote);
            textView = itemView.findViewById(R.id.textTitle);

        }

        //setting the data in the views
        @SuppressLint("SetTextI18n")
        void setCountry(Country country) {

            ImageFetch.fetchSvg(imageView.getContext(), country.getFlagPath(), imageView);

            population = addCommasToNumericString(country.getPopulation());
            languages = country.getLanguages();


            textView.setText("Country Name: "+country.getName() + "\n\nCapital: "+country.getCapital() + "\n\nPopulation: "+population
            +"\n\nRegion: "+country.getRegion()+"\n\nSub Region: "+country.getSubRegion()+"\n\nLanguages spoken (Name : Native Name) : \n"+country.getLanguages()+"\n\nBorders shared with: \n"+country.getBorders());
        }
    }

    //to add comas in the population numbers
    public static String addCommasToNumericString(String digits) {
        StringBuilder result = new StringBuilder();
        for (int i=1; i <= digits.length(); ++i) {
            char ch = digits.charAt(digits.length() - i);
            if (i % 3 == 1 && i > 1) {
                result.insert(0, ",");
            }
            result.insert(0, ch);
        }
        return result.toString();
    }
}


