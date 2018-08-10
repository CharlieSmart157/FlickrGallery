package example.code.flickrgallery.gallery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import example.code.flickrgallery.R;
import example.code.flickrgallery.network.model.Item;


public class GalleryFragment extends Fragment implements GalleryContract.View {

    private GalleryContract.Presenter presenter;
    private RecyclerView recyclerView;
    private LinearLayout itemsLayout;
    private LinearLayout noItemsLayout;
    private ScrollChildSwipeRefreshLayout scrollChildSwipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.gallery_fragment, container, false);

        recyclerView = root.findViewById(R.id.galleryView);

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        itemsLayout = root.findViewById(R.id.itemsLayout);
        noItemsLayout = root.findViewById(R.id.noItemsLayout);

        scrollChildSwipeRefreshLayout = root.findViewById(R.id.refreshLayout);
        scrollChildSwipeRefreshLayout.setScrollUpChild(recyclerView);

        scrollChildSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadGallery(true);
            }
        });

        return root;
    }

    @Override
    public void setPresenter(@NonNull GalleryContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        scrollChildSwipeRefreshLayout.setRefreshing(active);
    }

    @Override
    public void showImages(List<Item> items) {
        recyclerView.setAdapter(new ItemAdapter(items));

        itemsLayout.setVisibility(View.VISIBLE);
        noItemsLayout.setVisibility(View.GONE);
    }

    public void filter(String query) {
        ((ItemAdapter)recyclerView.getAdapter()).getFilter().filter(query);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
