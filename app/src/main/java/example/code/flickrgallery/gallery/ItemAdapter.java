package example.code.flickrgallery.gallery;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import example.code.flickrgallery.R;
import example.code.flickrgallery.network.model.Item;
import example.code.flickrgallery.utils.Utils;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> implements Filterable{

    private List<Item> itemList;
    private List<Item> itemListFiltered;

    public ItemAdapter(List itemList) {
        this.itemList = itemList;
        this.itemListFiltered = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.gallery_row, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int i) {
        final Item currentItem = itemListFiltered.get(i);
        Picasso.get().load(currentItem.getMedia().getM()).into(holder.getMedia());
        holder.getTitle().setText(currentItem.getTitle());
        holder.getAuthor().setText(currentItem.getAuthor());
        holder.getDate().setText(Utils.parseDate(currentItem.getDateTaken()));
    }

    @Override
    public int getItemCount() {
        return itemListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    itemListFiltered = itemList;
                } else {
                    List<Item> filteredList = new ArrayList<>();
                    for (Item row : itemList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTags().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    itemListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = itemListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                itemListFiltered = (ArrayList<Item>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
