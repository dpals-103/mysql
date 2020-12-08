package test2.session;

public class Session {

	public int isLoginedMemberId;
	public int isSelectedBoardId;

	public Session() {
		isLoginedMemberId = 0;
		isSelectedBoardId = 0; 
	}
	
	public void login(int id) {
		isLoginedMemberId = id;
	}

	public boolean isLogined() {
		return isLoginedMemberId > 0;
	}

	public void logout() {
		isLoginedMemberId = 0; 
	}

	public void selectBoard(int id) {
		isSelectedBoardId = id;
	}
}
