package zenlife.nox.nox.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.util.Random;

/**
 * Created by Nhan on 11/12/2017.
 */

public class ImageFromResource {
    private Context context;
    public ImageFromResource(Context context){
        this.context = context;
    }
    public String getRandomImage(){
        String[] listImages = getListImage();
        Random rand = new Random();
        int spot = rand.nextInt(10);
        return listImages[spot];
    }

    private String[] getListImage() {
        String[] listImages = null;
        try {
            AssetManager am = context.getAssets();
            listImages = am.list("images/popular");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listImages;
    }
}
