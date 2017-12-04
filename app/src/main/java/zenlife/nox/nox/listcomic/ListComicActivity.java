package zenlife.nox.nox.listcomic;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import zenlife.nox.nox.R;
import zenlife.nox.nox.util.ItemListcomicRVAdapter;

public class ListComicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_comic);

        Toolbar toolbarListComic = (Toolbar) findViewById(R.id.toolbarListComic);
        setSupportActionBar(toolbarListComic);
        ImageView imgBackListComic = (ImageView) toolbarListComic.findViewById(R.id.imgBackListComic);
        imgBackListComic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //set Up List
        RecyclerView listComicRecyclerView = (RecyclerView) findViewById(R.id.listComicRecyclerView);
        listComicRecyclerView.setLayoutManager(new GridLayoutManager(this, 3,  LinearLayoutManager.VERTICAL, false));
        listComicRecyclerView.setAdapter(new ItemListcomicRVAdapter(this));

        final TextView txtComictoobar = (TextView) findViewById(R.id.txtListcomicToolbar);
        final TextView txtListComicBanner = (TextView) findViewById(R.id.txtListComicBanner);
        AppBarLayout appBarListComic = (AppBarLayout) findViewById(R.id.appBarListComic);
        appBarListComic.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange() == 0)
                {
                    //  Collapsed
                    txtComictoobar.setVisibility(View.VISIBLE);
                    txtListComicBanner.setVisibility(View.GONE);
                }
                else
                {
                    //Expanded
                    txtComictoobar.setVisibility(View.GONE);
                    txtListComicBanner.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
