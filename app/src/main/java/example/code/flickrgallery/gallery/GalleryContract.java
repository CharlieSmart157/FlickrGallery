package example.code.flickrgallery.gallery;

import android.support.annotation.NonNull;

import java.util.List;

import example.code.flickrgallery.BasePresenter;
import example.code.flickrgallery.BaseView;
import example.code.flickrgallery.network.ApiService;
import example.code.flickrgallery.network.model.Item;
import example.code.flickrgallery.network.model.JsonFlickrFeed;

public interface GalleryContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showImages(List<Item> items);

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void result(JsonFlickrFeed jsonFlickrFeed);

        void loadGallery(boolean forceUpdate);

//        void setFiltering(TasksFilterType requestType);
//
//        TasksFilterType getFiltering();
    }
}
