package bcs.p_line;

import java.util.List;

    public interface Fetchlistener {
        public void onFetchComplete(List<namapesanan> data);
        public void onFetchComplete1(List<setsaldo> data);
        public void onFetchFailure(String msg);
    }

