/*
 *  Copyright © 2025 CGS IT Solutions GmbH.
 *  All rights reserved.
 *
 *  This source code is proprietary intellectual property
 *  of CGS IT Solutions GmbH and is provided solely for use
 *  within licensed training programs.
 *
 *   Any copying, redistribution, modification, or use
 *   beyond the permitted scope is strictly prohibited.
 */

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
