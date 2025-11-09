package at.cgsit.train.java.chatsys.server;

import at.cgsit.train.java.chatsys.model.ChatUser;

import java.util.List;

public interface UserManagement {

  /**
   * add a new user to the chatserver after login
   * @param user
   */
  void addUser(ChatUser user);

  /**
   * get all users sorted by user name
   * @return
   */
  List<ChatUser> getSortedUsersByName();
}
