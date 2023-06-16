package accounts;

import dbService.DBException;
import dbService.DBService;
import dbService.DBServiceImpl;
import dbService.dataSets.UsersDataSet;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;
    private final DBService dbService;

    public AccountService() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
        dbService = new DBServiceImpl();
        dbService.printConnectInfo();
    }

    public void addNewUser(UserProfile userProfile) {
//        loginToProfile.put(userProfile.getLogin(), userProfile);

        try {
            long userId = dbService.addUser(userProfile.getLogin(), userProfile.getPassword());
            System.out.println("Added user id: " + userId);

            UsersDataSet dataSet = dbService.getUser(userId);
            System.out.println("User data set: " + dataSet);

        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public UserProfile getUserByLogin(String login) {
//        return loginToProfile.get(login);

        UserProfile userProfile = null;
        try {
            UsersDataSet dataSet = dbService.getUserByLogin(login);
            System.out.println("User data set: " + dataSet);

            userProfile = new UserProfile(dataSet.getLogin(), dataSet.getPassword(), dataSet.getLogin());

        } catch (DBException e) {
            e.printStackTrace();
        }

        return userProfile;
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
