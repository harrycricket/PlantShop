package se160205.dao;

import se160205.dto.Order;
import se160205.dto.OrderDetail;
import se160205.utils.DBUtils;
import se160205.utils.DateUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Huynh Van Phuot
 */
public class OrderDAO {

    public static List<Order> getOrders() {
        List<Order> list = new ArrayList<>();

        Connection conn = null;

        try {
            conn = DBUtils.makeConnection();

            if (conn != null) {

                String sql = "SELECT OrderID, OrdDate, shipdate, o.status, a.AccID "
                        + "FROM Orders o"
                        + " INNER JOIN Accounts a"
                        + " ON a.AccID = o.AccID";

                PreparedStatement stm = conn.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("OrderID");
                    Date orderDate = rs.getDate("OrdDate");
                    String orderDateString = DateUtils.format(orderDate);

                    Date shipDate = rs.getDate("shipdate");
                    String shipDateString = DateUtils.format(shipDate);
                    int accId = rs.getInt("AccID");
                    int status = rs.getInt("status");

                    Order order = new Order(id, orderDateString, shipDateString, status, accId);
                    list.add(order);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return list;
    }

    public static List<Order> getOrders(int accid) {
        ArrayList<Order> lists = null;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                lists = new ArrayList<>();
                String sql = "select OrderID, OrdDate,shipdate,[status],AccID \n"
                        + "	from Orders where AccID = ? ";

                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setQueryTimeout(5);
                pst.setInt(1, accid);
                ResultSet rs = pst.executeQuery();
                while (rs.next() && rs != null) {
                    int orderId = rs.getInt("OrderID");
                    Date ordDate = rs.getDate("OrdDate");
                    Date shipdate = rs.getDate("shipdate");
                    int status = rs.getInt("status");
                    int accId = rs.getInt("AccID");
                    Order or = new Order(orderId, ordDate.toString(), shipdate.toString(), status, accId);
                    lists.add(or);
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    public static List<Order> getOrders(Date dateFrom, Date dateTo) {
        List<Order> list = new ArrayList<>();

        Connection conn = null;

        try {
            conn = DBUtils.makeConnection();

            if (conn != null) {

                String sql = "SELECT OrderID, OrdDate, shipdate, o.status, a.AccID "
                        + "FROM Orders o"
                        + " INNER JOIN Accounts a"
                        + " ON a.AccID = o.AccID"
                        + " WHERE OrdDate >= ? and OrdDate <= ?";

                PreparedStatement stm = conn.prepareStatement(sql);

                stm.setDate(1, dateFrom);
                stm.setDate(2, dateTo);

                ResultSet rs = stm.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("OrderID");
                    Date orderDate = rs.getDate("OrdDate");
                    String orderDateString = DateUtils.format(orderDate);

                    Date shipDate = rs.getDate("shipdate");
                    String shipDateString = DateUtils.format(shipDate);
                    int accId = rs.getInt("AccID");
                    int statusDb = rs.getInt("status");

                    Order order = new Order(id, orderDateString, shipDateString, statusDb, accId);
                    list.add(order);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return list;
    }

    public static List<Order> getOrders(int accid, int status, Date dateFrom, Date dateTo) {
        List<Order> list = new ArrayList<>();

        Connection conn = null;

        try {
            conn = DBUtils.makeConnection();

            if (conn != null) {

                String sql = "SELECT OrderID, OrdDate, shipdate, o.status, a.AccID "
                        + "FROM Orders o"
                        + " INNER JOIN Accounts a"
                        + " ON a.AccID = o.AccID"
                        + " WHERE a.AccID = ? and OrdDate >= ? and OrdDate <= ?";

                if (status != 0) {
                    sql += " and o.status = ?";
                }
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1, accid);
                stm.setDate(2, dateFrom);
                stm.setDate(3, dateTo);

                if (status != 0) {
                    stm.setInt(4, status);
                }
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("OrderID");
                    Date orderDate = rs.getDate("OrdDate");
                    String orderDateString = DateUtils.format(orderDate);

                    Date shipDate = rs.getDate("shipdate");
                    String shipDateString = DateUtils.format(shipDate);
                    int accId = rs.getInt("AccID");
                    int statusDb = rs.getInt("status");

                    Order order = new Order(id, orderDateString, shipDateString, statusDb, accId);
                    list.add(order);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return list;
    }

    public static List<OrderDetail> getOrderDetails(int id) {

        List<OrderDetail> list = new ArrayList<>();
        Connection conn = null;

        try {
            conn = DBUtils.makeConnection();

            if (conn != null) {
                String sql = "SELECT [DetailId]\n"
                        + "      ,[OrderID]\n"
                        + "      ,[FID]\n"
                        + "	  ,[PName]\n"
                        + "      ,[price]\n"
                        + "      ,[imgPath]\n"
                        + "      ,[quantity]\n"
                        + "  FROM [dbo].[OrderDetails] join Plants on OrderDetails.FID = Plants.PID\n"
                        + "  where OrderDetails.OrderID = ? ";

                PreparedStatement stm = conn.prepareStatement(sql);

                stm.setInt(1, id);

                ResultSet rs = stm.executeQuery();

                while (rs.next()) {
                    int detailId = rs.getInt("DetailId");
                    int orderId = rs.getInt("OrderID");
                    int pId = rs.getInt("FID");
                    String plantName = rs.getString("PName");
                    int price = rs.getInt("price");
                    String imgPath = rs.getString("imgPath");
                    int quantity = rs.getInt("quantity");

                    OrderDetail orderDetail = new OrderDetail(id, orderId, pId, plantName, price, imgPath, quantity);

                    list.add(orderDetail);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return list;
    }

    public static boolean insertOrder(int accid, HashMap<String, Integer> cart) {
        Connection cn = null;
        boolean rs = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);//khong cho auto tren thang vao bang chinh
                String sql = "insert Orders (OrdDate,shipdate,[status],AccID) values(?,?,?,?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                Date d = new Date(System.currentTimeMillis());
                pst.setDate(1, d);
                pst.setDate(2, d);
                pst.setInt(3, 1); //1<>peding
                pst.setInt(4, accid);
                pst.executeUpdate();
                rs = true;
                if (rs) {
                    sql = "select top 1 OrderID from Orders order by OrderID desc";
                    pst = cn.prepareStatement(sql);
                    ResultSet rSet = pst.executeQuery();
                    if (rSet != null && rSet.next()) {
                        int orderid = rSet.getInt("OrderID");
                        if (cart != null && cart.size() > 0) {
                            for (String temp : cart.keySet()) {
                                int bookid = Integer.parseInt(temp);
                                int quantity = cart.get(temp);
                                sql = "insert OrderDetails (OrderID,FID,quantity) values(?,?,?)";
                                pst = cn.prepareStatement(sql);
                                pst.setInt(1, orderid);
                                pst.setInt(2, bookid);
                                pst.setInt(3, quantity);
                                pst.executeUpdate();
                                rs = true;
                            }
                        }
                    }
                }
                cn.commit();
                cn.setAutoCommit(true);
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static boolean changeStatus(int orderId, int status) {
        Connection cnn = null;
        boolean result = false;

        try {
            cnn = DBUtils.makeConnection();
            if (cnn != null) {

                String sql = "UPDATE Orders SET status = ? where OrderID=?";
                PreparedStatement pst = cnn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setInt(2, orderId);

                result = pst.executeUpdate() > 0;

            }
        } catch (Exception ex) {
            if (cnn != null) {
                try {
                    cnn.rollback();
                } catch (SQLException ex1) {
                    ex.printStackTrace();
                }
            }
            ex.printStackTrace();
            result = false;
        } finally {
            try {
                cnn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return result;
    }
}
