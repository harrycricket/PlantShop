package se160205.dao;

import se160205.dto.Plant;
import se160205.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Huynh Van Phuot
 */
public class PlantDAO {

    public static List<Plant> getAllPlants() {
        List<Plant> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBUtils.makeConnection();

            String sql = "select PID, PName, price, imgPath, description, status, p.CateID, CateName "
                    + "                             from Plants p "
                    + "                             inner join Categories c on p.cateId = c.cateId where status = 1";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("PId");
                String name = rs.getString("PName");
                int price = rs.getInt("price");
                String imgPath = rs.getString("imgPath");
                String desciption = rs.getString("description");
                int status = rs.getInt("status");
                int cateId = rs.getInt("cateId");
                String cateName = rs.getString("cateName");

                Plant plant = new Plant(id, name, price, imgPath, desciption, status, cateId, cateName);
                list.add(plant);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PlantDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return list;
    }

    public static List<Plant> findPlants(String keyword, String searchBy) {
        List<Plant> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null && searchBy != null) {
                String sql = "select PID, PName, price, imgPath, description, status, p.CateID, CateName "
                        + "                             from Plants p "
                        + "                             inner join Categories c on p.cateId = c.cateId where status = 1 and ";
                if (searchBy.equals("byname")) {
                    sql += "PName like ?";
                } else {
                    sql += "CateName like ?";
                }

                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, "%" + keyword + "%");
                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("PId");
                    String name = rs.getString("PName");
                    int price = rs.getInt("price");
                    String imgPath = rs.getString("imgPath");
                    String desciption = rs.getString("description");
                    int status = rs.getInt("status");
                    int cateId = rs.getInt("cateId");
                    String cateName = rs.getString("cateName");

                    Plant plant = new Plant(id, name, price, imgPath, desciption, status, cateId, cateName);
                    list.add(plant);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PlantDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return list;
    }

    public static Plant findPlant(int id) {
        List<Plant> list = new ArrayList<>();
        Connection conn = null;
        Plant plant = null;
        try {
            conn = DBUtils.makeConnection();
            String sql = "select PID, PName, price, imgPath, description, status, p.CateID, CateName "
                    + "                             from Plants p "
                    + "                             inner join Categories c on p.cateId = c.cateId where status = 1 and PID = ?";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String name = rs.getString("PName");
                int price = rs.getInt("price");
                int cateId = rs.getInt("cateId");
                String imgPath = rs.getString("imgPath");
                String description = rs.getString("description");
                String cateName = rs.getString("cateName");

                plant = new Plant();
                plant.setId(id);
                plant.setName(name);
                plant.setImgPath(imgPath);
                plant.setPrice(price);
                plant.setDescription(description);
                plant.setCateId(cateId);
                plant.setCateName(cateName);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PlantDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return plant;
    }

    public static boolean savePlant(Plant plant) {
        List<Plant> list = new ArrayList<>();
        Connection conn = null;
        boolean result = true;
        try {
            conn = DBUtils.makeConnection();
            conn.setAutoCommit(false);
            String sql = "";
            if (plant.getId() == 0) {
                sql = "INSERT INTO Plants(PName, price, imgPath, description, status, cateId) "
                        + " VALUES (?, ?, ?, ?, ?, ?)";
            } else {
                sql = "UPDATE Plants SET PName = ?, price = ?, imgPath = ?, description = ?, status = ?, cateId = ?"
                        + " WHERE PID = ?";
            }

            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, plant.getName());
            st.setInt(2, plant.getPrice());
            st.setString(3, plant.getImgPath());
            st.setString(4, plant.getDescription());
            st.setInt(5, 1);
            st.setInt(6, plant.getCateId());

            if (plant.getId() != 0) {
                st.setInt(7, plant.getId());
            }

            int rs = st.executeUpdate();

            if (rs == 0) {
                result = false;
            } else {
                conn.commit();
            }

        } catch (Exception ex) {
            try {
                ex.printStackTrace();
                conn.rollback();
                result = false;
            } catch (SQLException ex1) {
                Logger.getLogger(PlantDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PlantDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return result;
    }

    public static boolean deletePlant(int id) {
        Connection conn = null;
        boolean result = true;
        try {
            conn = DBUtils.makeConnection();
            String sql = "DELETE FROM Plants WHERE PID = ?";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);

            int rs = st.executeUpdate();

            if (rs == 0) {
                result = false;
            }
        } catch (Exception ex) {
            result = false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PlantDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    public static List<Plant> getBestSeller() {
        Connection conn = null;
        List<Plant> list = new ArrayList();
        try {
            conn = DBUtils.makeConnection();
            String sql = "select top 3 p.PID, PName, imgPath,  sum(quantity) as total from orderDetails o "
                    + "	left join plants p "
                    + "	on o.PID = p.PID "
                    + "	Group by p.PID, Pname, imgPath "
                    + "	order by total desc";

            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("PID");
                String pName = rs.getString("PName");
                String imgPath = rs.getString("imgPath");

                Plant plant = new Plant();
                plant.setId(id);
                plant.setName(pName);
                plant.setImgPath(imgPath);

                list.add(plant);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(PlantDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;
    }

}
