package bcs.p_line;

import java.util.List;

/**
 * Created by BrianCahSantai on 5/3/2015.
 */
public interface gettransaksi {
    public void onFetchCompletetrans(List<transaksi> data);
    public void onFetchFailuretrans(String msg);
}

