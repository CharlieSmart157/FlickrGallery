package example.code.flickrgallery.gallery;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import example.code.flickrgallery.R;
import example.code.flickrgallery.network.ApiClient;
import example.code.flickrgallery.network.ApiService;

public class GalleryActivity extends AppCompatActivity {

    private GalleryPresenter galleryPresenter;
    private ApiService apiService;
    private SearchView searchView;
    private GalleryFragment galleryViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity);

        apiService =
                ApiClient.getClient(this).
                        create(ApiService.class);

        galleryViewFragment = (GalleryFragment) getSupportFragmentManager().findFragmentById(R.id.galleryViewFragment);
        galleryPresenter = new GalleryPresenter(galleryViewFragment, apiService);

        galleryViewFragment.setPresenter(galleryPresenter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gallery_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                galleryViewFragment.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                galleryViewFragment.filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
