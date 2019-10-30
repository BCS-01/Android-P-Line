package bcs.p_line;


public interface Listener {

	public void onSuccessful(String message);
	public void onError(String message);
    public void onUser(String message);


}
