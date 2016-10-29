package cn.com.ihealthlabs.cordova.plugins;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;

import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

public class ResponsiveWebView extends CordovaPlugin{

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        DisplayMetrics dm = new DisplayMetrics();
        cordova.getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        final float density = dm.density;
        final CordovaWebView appView = webView;
        final View rootView = cordova.getActivity().getWindow().getDecorView().findViewById(android.R.id.content).getRootView();

        OnGlobalLayoutListener list = new OnGlobalLayoutListener() {
            int previousHeightDiff = 0;

            @Override
            public void onGlobalLayout() {
               Rect r = new Rect();
               rootView.getWindowVisibleDisplayFrame(r);
               int heightDiff = rootView.getRootView().getHeight() - (r.bottom);
               int pixelHeightDiff = (int)(heightDiff / density);
               if ((( previousHeightDiff - pixelHeightDiff ) > 100 || pixelHeightDiff > 100) && pixelHeightDiff != previousHeightDiff) {
                   LayoutParams lp = appView.getView().getLayoutParams();
                   lp.height = r.bottom;
                   appView.getView().setLayoutParams(lp);
               }
               previousHeightDiff = pixelHeightDiff;
            }
        };
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(list);
    }
}
