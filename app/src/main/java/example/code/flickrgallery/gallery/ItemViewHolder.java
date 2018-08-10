package example.code.flickrgallery.gallery;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import example.code.flickrgallery.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private ImageView media;
    private TextView title;
    private TextView date;
    private TextView author;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        media = itemView.findViewById(R.id.mediaImage);
        title = itemView.findViewById(R.id.rowTitle);
        date = itemView.findViewById(R.id.rowDate);
        author = itemView.findViewById(R.id.rowAuthor);
    }

    public ImageView getMedia() {
        return media;
    }

    public void setMedia(ImageView media) {
        this.media = media;
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getDate() {
        return date;
    }

    public void setDate(TextView date) {
        this.date = date;
    }

    public TextView getAuthor() {
        return author;
    }

    public void setAuthor(TextView author) {
        this.author = author;
    }
}
