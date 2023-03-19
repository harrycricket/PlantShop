package se160205.dao;

import se160205.dto.Account;
import se160205.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Huynh Van Phuot
 */
public class AccountDAO {

    //get an account by email and password
    public static Account getAccount(String email, String password) {
        Account acc = null;
        Connection conn = null;
        try {
            conn = DBUtils.makeConnection();

            //add Collate Latin1_GENERAL_CS_AS to make the search are case sensitive
            if (conn != null) {
                String sql = " SELECT accId, fullname, phone, status, role "
                        + "FROM Accounts "
                        + "WHERE status=? and email=? and password=? COLLATE Latin1_GENERAL_CS_AS";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setInt(1, 1);
                st.setString(2, email);
                st.setString(3, password);

                ResultSet rs = st.executeQuery();

                if (rs != null && rs.next()) {
                    int accId = rs.getInt("accID");
                    String fullName = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    int status = rs.getInt("status");
                    int role = rs.getInt("role");

                    acc = new Account(accId, email, password, fullName, status, phone, role);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return acc;
    }

    public static Account getAccount(String email) {
        Account acc = null;
        Connection conn = null;
        try {
            conn = DBUtils.makeConnection();

            //add Collate Latin1_GENERAL_CS_AS to make the search are case sensitive
            if (conn != null) {
                String sql = " SELECT accId, fullname, phone, status, role, password "
                        + "FROM Accounts "
                        + "WHERE status=? and email=? COLLATE Latin1_GENERAL_CS_AS";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setInt(1, 1);
                st.setString(2, email);

                ResultSet rs = st.executeQuery();

                if (rs != null && rs.next()) {
                    int accId = rs.getInt("accID");
                    String fullName = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    int status = rs.getInt("status");
                    int role = rs.getInt("role");
                    String password = rs.getString("password");

                    acc = new Account(accId, email, password, fullName, status, phone, role);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return acc;
    }

    public static Account getAccountByToken(String token) {
        Account acc = null;
        Connection conn = null;
        try {
            conn = DBUtils.makeConnection();

            //add Collate Latin1_GENERAL_CS_AS to make the search are case sensitive
            if (conn != null) {
                String sql = " SELECT accId, email, fullname, phone, status, role, password, token "
                        + "FROM Accounts "
                        + "WHERE status=? and token=? COLLATE Latin1_GENERAL_CS_AS";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setInt(1, 1);
                st.setString(2, token);

                ResultSet rs = st.executeQuery();

                if (rs != null && rs.next()) {
                    int accId = rs.getInt("accID");
                    String fullName = rs.getString("fullname");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    int status = rs.getInt("status");
                    int role = rs.getInt("role");
                    String password = rs.getString("password");

                    acc = new Account(accId, email, password, fullName, status, phone, role);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return acc;
    }

    //get All account
    public static List<Account> getAccounts() {
        List<Account> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT accId, email, password, fullname, phone, status, role "
                        + "FROM Accounts";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    int accId = rs.getInt("accID");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String fullName = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    int status = rs.getInt("status");
                    int role = rs.getInt("role");

                    Account acc = new Account(accId, email, password, fullName, status, phone, role);
                    list.add(acc);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return list;
    }

    //update account status
    public static boolean updateAccountStatus(int id, int status) {
        Connection conn = null;
        boolean result = true;

        try {
            conn = DBUtils.makeConnection();

            if (conn != null) {
                String sql = " UPDATE accounts"
                        + " SET status = ?"
                        + " WHERE accId = ?";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setInt(1, status);
                st.setInt(2, id);

                int rs = st.executeUpdate();
                if (rs == 0) {
                    result = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    //Update an account
    public static boolean updateAccount(String email, String newPassword, String newFullName, String newPhone) {
        Connection conn = null;
        boolean result = true;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "UPDATE accounts"
                        + " SET password = ?,"
                        + "	fullname = ?,"
                        + "	phone = ?"
                        + " WHERE email = ?";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, newPassword);
                st.setString(2, newFullName);
                st.setString(3, newPhone);
                st.setString(4, email);

                int rs = st.executeUpdate();
                if (rs == 0) {
                    result = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return result;
    }

    //insert an Account
    public static boolean insertAccount(String email, String password, String fullname, String phone, int status, int role) {
        Connection conn = null;
        boolean result = true;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = " INSERT INTO Accounts (email, password, fullname, phone, status, role)"
                        + " VALUES(?, ?, ?, ?, ?, ?)";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, email);
                st.setString(2, password);
                st.setString(3, fullname);
                st.setString(4, phone);
                st.setInt(5, status);
                st.setInt(6, role);

                st.executeUpdate();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            result = false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    result = false;
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    public static void updateToken(String email, String token) {
        Connection conn = null;
        try {
            conn = DBUtils.makeConnection();
            String sql = "UPDATE Accounts SET token = ? WHERE email = ?";
            PreparedStatement stm = conn.prepareStatement(sql);

            stm.setString(1, token);
            stm.setString(2, email);

            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
